package ir.gymbrain.example;

import ir.gymbrain.core.GymbrainApplication;
import ir.gymbrain.core.annotation.Controller;
import org.reflections.Reflections;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

public class Hello {
    public static void main(String[] args) throws IOException, URISyntaxException {
        new GymbrainApplication(Hello.class, args).start();
    }
}
