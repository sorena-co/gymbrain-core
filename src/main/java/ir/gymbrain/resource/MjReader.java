package ir.gymbrain.resource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class MjReader {
    public String getStringByKey(String key) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("application.mj");
        File file = new File(resource.toURI());
        List<String> lines;
        lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        String result = lines.stream()
                .filter(s ->
                {
                    return s.split("=")[0].equals(key);
                })
                .map(s ->
                {
                    return s.split("=")[1].replace(";", "");
                }).findFirst().orElse(null);
        return result;
    }
    public Integer getIntegerByKey(String key) throws URISyntaxException, IOException {
        URL resource = getClass().getClassLoader().getResource("application.mj");
        File file = new File(resource.toURI());
        List<String> lines;
        lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        String result = lines.stream()
                .filter(s ->
                {
                    return s.split("=")[0].equals(key);
                })
                .map(s ->
                {
                    return s.split("=")[1].replace(";", "");
                }).findFirst().orElse(null);
        return Integer.valueOf(result);
    }
}
