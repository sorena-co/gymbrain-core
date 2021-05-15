package ir.gymbrain.core.context;

import ir.gymbrain.core.annotation.Request;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControllerContext {

    private static Map<String, Class<?>> controllers = new HashMap<>();
    private static Map<Class<?>, List<Method>> methods = new HashMap<>();
    private static List<RequestMethodDetail> requestMethodDetails = new ArrayList<>();

    public static void put(String name, Class<?> cls) {
        controllers.putIfAbsent(name, cls);
    }

    public static void putMethod(Class<?> cls, List<Method> requestMethods) {
        methods.putIfAbsent(cls, requestMethods);
        requestMethodDetails.addAll(mapMethodToDetail(requestMethods));
        System.out.println(requestMethodDetails);
    }

    public static Class<?> getController(String name) {
        return controllers.get(name);
    }

    public static List<Method> getMethods(Class<?> cls) {
        return methods.get(cls);
    }

    private static RequestMethodDetail mapMethodToDetail(Method method) {
        RequestMethodDetail detail = new RequestMethodDetail();
        detail.setMethod(method);
        detail.setUrl(method.getDeclaredAnnotation(Request.class).url());
        detail.setRequestMethod(method.getDeclaredAnnotation(Request.class).requestMethod());
        return detail;
    }

    private static List<RequestMethodDetail> mapMethodToDetail(List<Method> methods) {
        List<RequestMethodDetail> details = new ArrayList<>();
        methods.parallelStream().forEach(method -> {
            details.add(mapMethodToDetail(method));
        });
        return details;
    }

}
