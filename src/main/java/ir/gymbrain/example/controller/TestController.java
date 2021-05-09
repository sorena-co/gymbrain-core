package ir.gymbrain.example.controller;

import ir.gymbrain.core.annotation.Controller;
import ir.gymbrain.core.annotation.Request;

@Controller(prefixPath = "/base")
public class TestController {

    @Request
    public String get() {
        return "hello";
    }
}
