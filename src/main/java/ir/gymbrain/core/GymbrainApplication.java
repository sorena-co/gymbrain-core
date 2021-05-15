package ir.gymbrain.core;

import ir.gymbrain.core.annotation.Controller;
import ir.gymbrain.core.annotation.Request;
import ir.gymbrain.core.context.ControllerContext;
import ir.gymbrain.log.GLog;
import ir.gymbrain.resource.MjReader;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GymbrainApplication {
    private Class<?> mainClass;

    private String[] args;

    private GLog log;

    public GymbrainApplication(Class<?> main, String... args) {
        mainClass = main;
        log = new GLog(main);
        this.args = args;
    }

    public void start(GymbrainApplicationConfiguration configuration) throws IOException, URISyntaxException {
        log.log("application starting ...");
        MjReader mjReader = new MjReader();

        List<Reflections> reflections = new ArrayList<>();

        configuration.getControllerBasePackages().parallelStream()
                .forEach(basePackage -> {
                    reflections.add(new Reflections(
                            new ConfigurationBuilder().setUrls(
                                    ClasspathHelper.forPackage("ir")).setScanners(
                                    new MethodAnnotationsScanner())));
                });

        loadControllers(reflections);

        Integer port = mjReader.getIntegerByKey("port");
        try (ServerSocket server = new ServerSocket(port)) {
            log.log("Server started in port : " + port);
            server.setSoTimeout(1000);
            while (true) {
                try (Socket socket = server.accept()) {
                    try (InputStream input = socket.getInputStream();
                         OutputStream output = socket.getOutputStream()) {
                        byte[] buffer = new byte[10000];
                        int total = input.read(buffer);
                        String request = new String(Arrays.copyOfRange(buffer, 0, total));
                        String response = "HTTP/1.1 200 OK\r\n\r\nir.gymbrain.example.Hello, world!";
                        output.write(response.getBytes());
                    }
                } catch (SocketTimeoutException ex) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
            }
        }

    }

    private void loadControllers(List<Reflections> reflections) {
        log.log("start scanning controller class...");
        Set<Class<?>> classAnnotatedWith = new Reflections().getTypesAnnotatedWith(Controller.class);
        log.log("find " + classAnnotatedWith.size() + " controller class.");
        log.log("start scanning method class...");
        List<Method> requestMethods = new ArrayList<>();
        reflections.parallelStream().forEach(ref -> {
            Set<Method> methods = ref.getMethodsAnnotatedWith(Request.class);
            requestMethods.addAll(methods);
        });
        log.log("find " + requestMethods.size() + " request method.");

        classAnnotatedWith.parallelStream().forEach(aClass -> {
            Controller annotation = aClass.getAnnotation(Controller.class);
            ControllerContext.put(annotation.name(), aClass);

            List<Method> methodsByClass = requestMethods.parallelStream()
                    .filter(method -> method.getDeclaringClass().equals(aClass))
                    .collect(Collectors.toList());

            ControllerContext.putMethod(aClass, methodsByClass);
        });


    }

}
