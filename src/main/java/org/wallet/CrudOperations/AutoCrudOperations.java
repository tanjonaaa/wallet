package org.wallet.CrudOperations;

import lombok.Getter;
import org.wallet.Annotations.Column;
import org.wallet.Annotations.CustomType;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.LocalDateTime;
import org.wallet.ConnectionDB.ConnectionDB;
import org.wallet.Utilities.AnnotationUtility;
import org.wallet.Utilities.Enums.PgQuery;
import org.wallet.Utilities.QueryFormatterUtility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AutoCrudOperations<T> implements CrudOperations<T>{
    private final Connection connection;
    public AutoCrudOperations() {
        this.connection = ConnectionDB.getConnection();
    }

    //Get the class of the generic parameter <T>
    private Class<?> parameterToClass(){
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll() {
        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                AnnotationUtility.getColumns(this.parameterToClass())
        );

        String query = queryFormatter.formatQuery(PgQuery.SELECT);

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
        List<String> columns = AnnotationUtility.getColumns(this.parameterToClass());

        QueryFormatterUtility queryFormatter = QueryFormatterUtility.getInstance(
                AnnotationUtility.getTableName(this.parameterToClass()),
                columns
        );

        Field id = AnnotationUtility.getField(
          this.parameterToClass(),
          Id.class
        );
        Method getId = AnnotationUtility.getMethod(
                t.getClass(),
                id.getAnnotation(Column.class).name(),
                "getter"
        );

        try {
            String query = getId.invoke(t) == null ? queryFormatter.formatQuery(PgQuery.INSERT)
                    : queryFormatter.formatQuery(PgQuery.UPDATE);

            PreparedStatement statement = this.connection.prepareStatement(query);

            for (int i = 1; i < columns.size(); i++) {
                String columnName = columns.get(i);
                Field field = AnnotationUtility.getAnnotatedField(
                        this.parameterToClass(),
                        columnName);
                Method getter = AnnotationUtility.getMethod(
                        this.parameterToClass(),
                        columnName ,
                        "getter");

                statement.setObject(i,
                        field.isAnnotationPresent(CustomType.class)
                        ? getter.invoke(t).toString() : getter.invoke(t)
                );
            }

            if(getId.invoke(t) != null){
                statement.setObject(columns.size(),getId.invoke(t));
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

    protected T mapResultSet(ResultSet resultSet){
        Object modelObject = null;
        List<String> columns = AnnotationUtility.getColumns(this.parameterToClass());
        try {
            modelObject = this.parameterToClass().getDeclaredConstructor().newInstance();
            for (String column : columns) {
                Field field = AnnotationUtility.getAnnotatedField(
                        this.parameterToClass(),
                        column);
                Method setter = AnnotationUtility.getMethod(
                        this.parameterToClass(),
                        column,
                        "setter");


                if (field.isAnnotationPresent(CustomType.class)){
                    Class<?> type = Class.forName(
                            "org.wallet.Models.Types."+
                                    field.getAnnotation(CustomType.class).type_class()
                    );

                    setter.invoke(modelObject, Enum.valueOf((Class<Enum>) (type), resultSet.getString(column)));
                }else {
                    if(field.isAnnotationPresent(LocalDateTime.class))
                    {
                        setter.invoke(modelObject, ((Timestamp) resultSet.getObject(column)).toLocalDateTime());
                    }else{
                        setter.invoke(modelObject, resultSet.getObject(column));
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException |
                 SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return (T) modelObject;
    }

}
