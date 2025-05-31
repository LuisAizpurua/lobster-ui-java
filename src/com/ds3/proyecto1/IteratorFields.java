package com.ds3.proyecto1;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public interface IteratorFields extends Cloneable{

    List<String> listFields() ;
    Consumer<Field> accessible = field -> field.setAccessible(true);

    default List<String> streamFields(Object thisClass){
        Field[] fields = thisClass.getClass().getDeclaredFields();
        return Arrays.stream(fields)
                .peek(accessible)
                .map(field -> returnValue(field,thisClass))
                .collect(Collectors.toList());
    }

    private static String returnValue(Field field, Object Tclass){
        try {
            return (String) field.get(Tclass);
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
