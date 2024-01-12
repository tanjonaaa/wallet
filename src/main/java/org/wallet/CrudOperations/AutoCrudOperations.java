package org.wallet.CrudOperations;



import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;
import org.wallet.ConnectionDB.ConnectionDB;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AutoCrudOperations<T> implements CrudOperations<T>{

    private static final String FIND_ALL_QUERY = "select {columns} from \"%s\"";
    private static final String SAVE_QUERY = "insert into \"%s\" ({columns}) values ({questionMarks}) returning *";
    private final Connection connection;

    public AutoCrudOperations() {
        this.connection = ConnectionDB.getConnection();
    }

    //Get the class of the generic parameter <T>
    private Class<?> toClass(){
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    //Get the name of the corresponding Table with the value of Model annotation
    // Model(table = ...)
    private String getTableName(){
        return this.toClass().getAnnotation(Model.class).table();
    }

    //Get all columns with the @Column annotation
    private List<String> getColumns(boolean withId) {
        Class<?> clazz = this.toClass();
        List<Field> fields = List.of(clazz.getDeclaredFields());

        return fields.stream()
                .filter(field -> withId || !field.isAnnotationPresent(Id.class))
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .toList();
    }
    
    private String formatQuery(String query, String tableName, List<String> columns, boolean isPrepared){
        String normalQuery = String.format(query, this.getTableName()).replace(
                "{columns}", String.join(", ", columns)
        );

        String preparedQuery = String.format(query, this.getTableName()).replace(
                "{columns}", String.join(", ", columns)
        ).replace(
                "{questionMarks}", String.join(", ", columns.stream()
                        .map(column -> "?").toList()
                )
        );

        return (isPrepared) ? preparedQuery : normalQuery;
    }

    @Override
    public List<T> findAll() {
        /*
         * Get the table name
         * Get the columns list
         * */
        String query = formatQuery(FIND_ALL_QUERY, this.getTableName(), this.getColumns(true), false);
        List<T> results = new ArrayList<>();
        try {
            Statement statement = this.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                results.add(this.mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    @Override
    public T save(T t){
        T saved = null;
        String query = formatQuery(SAVE_QUERY, this.getTableName(), this.getColumns(false), true);
        List<String> columns = this.getColumns(false);
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);

            for (int i = 0; i < columns.size(); i++) {
                String columnName = columns.get(i);
                Field field = this.getAnnotatedField(columnName);
                Method getter = this.getMethod(columnName, "getter");

                statement.setObject(i+1,
                        field.isAnnotationPresent(CustomType.class)
                        ? getter.invoke(t).toString() : getter.invoke(t)
                );
            }

            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            saved = mapResultSet(resultSet);

        } catch (SQLException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return saved;
    }

    public Connection getConnection() {
        return connection;
    }

    protected T mapResultSet(ResultSet resultSet){
        Object modelObject = null;
        List<String> columns = this.getColumns(true);
        try {
            modelObject = this.toClass().getDeclaredConstructor().newInstance();
            for (String column : columns) {
                Field field = this.getAnnotatedField(column);
                Method setter = this.getMethod(column, "setter");


                if (field.isAnnotationPresent(CustomType.class)){
                    Class<?> type = Class.forName(
                            "org.wallet.Models.Types."+
                                    field.getAnnotation(CustomType.class).type_class()
                    );

                    setter.invoke(modelObject, Enum.valueOf((Class<Enum>) (type), resultSet.getString(column)));
                }else {

                    setter.invoke(modelObject, resultSet.getObject(column));
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return (T) modelObject;
    }

    private Method getMethod(String columnName, String methodType){

        Field field = this.getAnnotatedField(columnName);

        Class<?> parameterType = field.getType();

        try {
            return  methodType.equals("setter") ?
                    this.toClass().getMethod("set"+this.ucFirst(field.getName()), parameterType)
                    : this.toClass().getMethod("get"+this.ucFirst(field.getName()))
                    ;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private String ucFirst(String entry){
        return entry.substring(0, 1).toUpperCase() + entry.substring(1);
    }

    private Field getAnnotatedField(String columnName){
        List<Field> fields = List.of(this.toClass().getDeclaredFields());

        return  fields.stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).name().equals(columnName))
                .toList().get(0);
    }
}
