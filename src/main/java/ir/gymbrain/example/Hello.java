package ir.gymbrain.example;

import ir.gymbrain.core.GymbrainApplication;
import ir.gymbrain.core.GymbrainApplicationConfiguration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Hello {
    public static void main(String[] args) throws IOException, URISyntaxException {
        GymbrainApplicationConfiguration gymbrainApplicationConfiguration = new GymbrainApplicationConfiguration();
        gymbrainApplicationConfiguration.setControllerBasePackages(new ArrayList<String>() {{
            add("");
        }});
        new GymbrainApplication(Hello.class, args).start(gymbrainApplicationConfiguration);
    }
}
