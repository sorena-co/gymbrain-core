package ir.gymbrain.core.context;

import ir.gymbrain.core.enumuration.RequestMethod;

import java.lang.reflect.Method;

public class RequestMethodDetail {
    private String url;
    private RequestMethod requestMethod;
    private Method method;


    public String getUrl() {
        return url;
    }

    public RequestMethodDetail setUrl(String url) {
        this.url = url;
        return this;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public RequestMethodDetail setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public Method getMethod() {
        return method;
    }

    public RequestMethodDetail setMethod(Method method) {
        this.method = method;
        return this;
    }
}
