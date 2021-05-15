package ir.gymbrain.example.controller;

import ir.gymbrain.core.annotation.Controller;
import ir.gymbrain.core.annotation.Request;
import ir.gymbrain.core.enumuration.RequestMethod;

@Controller(name = "/hello")
public class HelloController {

    @Request(url = "/api/hello", requestMethod = RequestMethod.GET)
    public String get() {
        return "hello v2";
    }
}
