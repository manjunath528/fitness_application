package com.gym.app.controller.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Manjunath Reddy
 */
@java.lang.annotation.Target({ElementType.METHOD, ElementType.TYPE})
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
public @interface PrivateApiAuthenticated {
}
