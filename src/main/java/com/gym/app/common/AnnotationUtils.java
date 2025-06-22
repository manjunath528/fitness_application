package com.gym.app.common;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by Manjunath Reddy
 */
public class AnnotationUtils {

    public static <T extends Annotation> Optional<T> findAnnotation(HandlerMethod handlerMethod, Class<T> annotationClass) {
        Supplier<T> getFromMethod = () -> handlerMethod.getMethodAnnotation(annotationClass);
        Supplier<T> getFromClass = () -> handlerMethod.getBeanType().getAnnotation(annotationClass);

        return Stream.of(getFromMethod, getFromClass)
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .findFirst();
    }
}
