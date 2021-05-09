package ir.gymbrain.core.context;

import java.util.HashMap;
import java.util.Map;

public class ControllerContext {

    private static Map<String, Class<?>> contexts = new HashMap<>();

    public static void put(String name, Class<?> cls) {
        contexts.putIfAbsent(name, cls);
    }

    public static Class<?> getContext(String name) {
        return contexts.get(name);
    }
}
