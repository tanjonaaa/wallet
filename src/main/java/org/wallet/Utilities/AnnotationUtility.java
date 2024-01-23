package org.wallet.Utilities;

import org.wallet.Annotations.Column;
import org.wallet.Annotations.Id;
import org.wallet.Annotations.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationUtility {
    public static boolean isAnnotatedWith(Class<?> element, Class<? extends Annotation> annotation){
        return element.isAnnotationPresent(annotation);
    }

    public static String getTableName(Class<?> clazz){
        return clazz.getAnnotation(Model.class).table();
    }

    //Get all columns with the @Column annotation
    public static List<String> getColumns(Class<?> clazz) {
        List<Field> fields = List.of(clazz.getDeclaredFields());

        return fields.stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .toList();
    }

    public static Method getMethod(Class<?> clazz, String columnName, String methodType){

        Field field = getAnnotatedField(clazz, columnName);

        Class<?> parameterType = field.getType();

        try {
            return  methodType.equals("setter") ?
                    clazz.getMethod("set"+ucFirst(field.getName()), parameterType)
                    : clazz.getMethod("get"+ucFirst(field.getName()))
                    ;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static String ucFirst(String entry){
        return entry.substring(0, 1).toUpperCase() + entry.substring(1);
    }

    public static Field getAnnotatedField(Class<?> clazz, String columnName){
        List<Field> fields = List.of(clazz.getDeclaredFields());

        return  fields.stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).name().equals(columnName))
                .toList().get(0);
    }

    public static Field getField(Class<?> clazz, Class<? extends Annotation> annotationClass){
        List<Field> fields = List.of(clazz.getDeclaredFields());

        return  fields.stream()
                .filter(field -> field.isAnnotationPresent(annotationClass))
                .toList().get(0);
    }
}
