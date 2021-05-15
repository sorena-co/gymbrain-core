package ir.gymbrain.core.http;

import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    private Map<String, String> requestParameters = new HashMap<>();


    public RequestUtil parseContent(String request) {
        String[] lines = request.split("\r\n");
        requestParameters.putIfAbsent("method", lines[0].split("/")[0]);
        for (int i = 1; i < lines.length; i++) {
            String[] keyValues = lines[i].split(": ");
            if (keyValues.length == 2)
                requestParameters.putIfAbsent(keyValues[0].toLowerCase(), keyValues[1]);
        }
        return this;
    }

    public String getRequestParameters(String key) {
        return requestParameters.get(key.toLowerCase());
    }

    public Map<String, String> getAllRequestParameter() {
        return requestParameters;
    }

}
