package com.gysoft.utils.exception;

import com.gysoft.bean.utils.TypeFunction;
import com.gysoft.utils.util.EmptyUtils;
import io.swagger.annotations.ApiModelProperty;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 异常工具类</br>
 *
 * @author 周宁
 * @Date 2019-03-01 11:32
 */
public class ThrowExceptionUtil {

    private static final Predicate PREDICATE_OBJ_NULL = object -> object == null;
    private static final Predicate PREDICATE_OBJ_NOT_NULL = object -> object != null;
    private static final Predicate<List> PREDICATE_COLLECTION_EMPTY = list -> EmptyUtils.isEmpty(list);
    private static final Predicate<List> PREDICATE_COLLECTION_NOT_EMPTY = list -> EmptyUtils.isEmpty(list);
    private static final Predicate<String> PREDICATE_STRING_EMPTY = string -> EmptyUtils.isEmpty(string);
    private static final Predicate<String> PREDICATE_STRING_NOT_EMPTY = string -> EmptyUtils.isNotEmpty(string);

    /**
     * 校验参数异常
     *
     * @param obj          受检对象
     * @param typeFunction 根据TypeException描述字段信息
     * @throws ParamInvalidException
     */
    public static <T, R> void validAndThrowParamInvalidException(Object obj, TypeFunction<T, R> typeFunction) throws ApplicationException {
        validAndThrowException(obj, null, null, ParamInvalidException.class, typeFunction);
    }

    /**
     * @param obj       受检对象
     * @param predicate 异常条件
     * @param desc      抛出异常描述
     * @throws ApplicationException
     */
    public static void validAndThrowParamInvalidException(Object obj, Predicate predicate, String desc) throws ApplicationException {
        validAndThrowException(obj, predicate, desc, ParamInvalidException.class, null);
    }


    public static void validAndThrowParamInvalidException(Object obj, String desc) throws ApplicationException {
        validAndThrowException(obj, chooseNullPredicate(obj), desc, ParamInvalidException.class, null);
    }

    /**
     * 数据不存在异常
     * @param obj 受检对象
     * @param desc 抛出异常描述
     * @throws ApplicationException
     */
    public static void validAndThrowDataNotFoundException(Object obj, String desc) throws ApplicationException {
        validAndThrowException(obj, chooseNullPredicate(obj), desc, DataNotFoundException.class, null);
    }

    /**
     * 数据已存在异常
     * @param obj 受检对象
     * @param desc 抛出异常描述
     * @throws ApplicationException
     */
    public static void validAndThrowAlreadyExistException(Object obj, String desc) throws ApplicationException {
        validAndThrowException(obj, chooseNotNullPredicate(obj), desc, AlreadyExistException.class, null);
    }

    private static <T, R, E extends ApplicationException> void validAndThrowException(Object obj, Predicate predicate, String desc, Class<E> eClass, TypeFunction<T, R> typeFunction) throws ApplicationException {
        ApplicationException exception = null;
        if (null != typeFunction) {
            try {
                Method method = typeFunction.getClass().getDeclaredMethod("writeReplace");
                method.setAccessible(Boolean.TRUE);
                SerializedLambda serializedLambda = (SerializedLambda) method.invoke(typeFunction);
                Class cls = Class.forName(serializedLambda.getImplClass().replace("/", "."));
                String getter = serializedLambda.getImplMethodName();
                String fieldName = Introspector.decapitalize(getter.replace("get", ""));
                Field[] fields = cls.getDeclaredFields();
                for (Field f : fields) {
                    ApiModelProperty anno = f.getDeclaredAnnotation(ApiModelProperty.class);
                    if (anno != null && fieldName.equals(f.getName())) {
                        if (obj instanceof String) {
                            if (EmptyUtils.isEmpty((String) obj)) {
                                exception = newExceptionByEClss(eClass, anno.value() + Optional.ofNullable(desc).orElse("不能为空或者null"));
                            }
                        } else if (obj instanceof Collection) {
                            if (EmptyUtils.isEmpty((Collection) obj)) {
                                exception = newExceptionByEClss(eClass, anno.value() + Optional.ofNullable(desc).orElse("不能为空或者null"));
                            }
                        } else {
                            if (null == obj) {
                                exception = newExceptionByEClss(eClass, anno.value() + Optional.ofNullable(desc).orElse("不能为null"));
                            }
                        }
                    }
                }
            } catch (Exception e) {

            } finally {
                if (null != exception) {
                    throw exception;
                }
            }
        } else {
            if (predicate.test(obj)) {
                throw newExceptionByEClss(eClass, desc);
            }

        }

    }


    /**
     * 根据elcss类型创建异常对象
     *
     * @param eClss
     * @param message
     * @param <E>
     * @return
     */
    private static <E extends ApplicationException> ApplicationException newExceptionByEClss(Class<E> eClss, String message) {
        if (ParamInvalidException.class.equals(eClss)) {
            return new ParamInvalidException(message);
        }
        if (DataNotFoundException.class.equals(eClss)) {
            return new DataNotFoundException(message);
        }
        if (AlreadyExistException.class.equals(eClss)) {
            return new AlreadyExistException(message);
        }
        return null;
    }

    private static Predicate chooseNotNullPredicate(Object obj) {
        if (obj instanceof Collection) {
            return PREDICATE_COLLECTION_NOT_EMPTY;
        } else if (obj instanceof String) {
            return PREDICATE_STRING_NOT_EMPTY;
        } else {
            return PREDICATE_OBJ_NOT_NULL;
        }
    }

    private static Predicate chooseNullPredicate(Object obj) {
        if (obj instanceof Collection) {
            return PREDICATE_COLLECTION_EMPTY;
        } else if (obj instanceof String) {
            return PREDICATE_STRING_EMPTY;
        } else {
            return PREDICATE_OBJ_NULL;
        }
    }

}
