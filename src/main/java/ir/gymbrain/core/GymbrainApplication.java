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
import java.util.Arrays;
import java.util.Set;

public class GymbrainApplication {
    private Class<?> mainClass;

    private String[] args;

    private GLog log;

    public GymbrainApplication(Class<?> main, String... args) {
        mainClass = main;
        log = new GLog(main);
        this.args = args;
    }

    public void start() throws IOException, URISyntaxException {
        log.log("application starting ...");
        MjReader mjReader = new MjReader();

        Set<Class<?>> typesAnnotatedWith1 = new Reflections().getTypesAnnotatedWith(Controller.class);

        typesAnnotatedWith1.stream().parallel().forEach(aClass -> {
            Controller annotation = aClass.getAnnotation(Controller.class);
            ControllerContext.put(annotation.prefixPath(), aClass);
        });
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().setUrls(
                        ClasspathHelper.forPackage("ir")).setScanners(
                        new MethodAnnotationsScanner()));

        Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(Request.class);

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

}
