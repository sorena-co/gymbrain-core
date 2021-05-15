package ir.gymbrain.core.annotation;


import com.sun.istack.internal.NotNull;
import ir.gymbrain.core.enumuration.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Request {
    @NotNull
    String url();

    @NotNull
    RequestMethod requestMethod();
}
