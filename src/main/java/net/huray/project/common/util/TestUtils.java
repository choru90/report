package net.huray.project.common.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestUtils {

    /**
     * Class파일로 새로운 객체 생성
     * @param tClass : 생성하고자 하는 객체
     * @return T : 생성된 객체
     * @param <T>
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> T getNewInstance(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor<T> declaredConstructor = tClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance();
    }

    /**
     * 객체에 필드 값 설정
     * @param obj : 필드값을 설정는 객체
     * @param fieldName : 필드명
     * @param value : 설정 값
     * @param <T>
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static <T> void setField(Object obj, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        if(Objects.isNull(value)){
            value = null;
        }
        Field declaredField = null;
        try {
            declaredField = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException nsfe) {
            declaredField = obj.getClass().getSuperclass().getDeclaredField(fieldName);
        }
        declaredField.setAccessible(true);
        declaredField.set(obj, value);
    }

    /**
     * Map의 필드명과 설정값을 설정하여 객체 생성
     * @param tClass : 생성 클래스
     * @param param : 필드명(key), 설정값(value) Map
     * @return
     * @param <T>
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */

    public static <T> T getFieldValueObject(Class<T> tClass, Map<String, Object> param) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        T newInstance = getNewInstance(tClass);

        for (String fieldName : param.keySet()) {
            setField(newInstance, fieldName, param.get(fieldName));
        }

        return newInstance;
    }

    /**
     * Map의 필드명과 설정값을 설정하여 객체 목록 생성
     * @param tClass : 생성 클래스
     * @param paramList : 필드명(key), 설정값(value) Map 목록
     * @return
     * @param <T>
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static <T> List<T> getFieldValueObjectList(Class<T> tClass, List<Map<String, Object>> paramList) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<T> newInstanceList = new ArrayList<>();
        for (Map<String, Object> param : paramList) {
            newInstanceList.add(getFieldValueObject(tClass, param));
        }

        return newInstanceList;
    }
}