package ir.gymbrain.example.controller;

import ir.gymbrain.core.annotation.Controller;
import ir.gymbrain.core.annotation.Request;
import ir.gymbrain.core.enumuration.RequestMethod;

@Controller(name = "/base")
public class TestController {

    @Request(url = "/api/base", requestMethod = RequestMethod.GET)
    public String get() {
        return "hello";
    }
}
