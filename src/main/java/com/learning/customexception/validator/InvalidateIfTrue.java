package com.learning.customexception.validator;

import com.learning.customexception.exception.ApiException;

import java.util.function.Supplier;

public class InvalidateIfTrue {
    public static void invalidate(Supplier<Boolean> supplier, String message) {
        if (supplier.get()) {
            throw new ApiException(message);
        }


    }

    public static void invalidate(Supplier<Boolean> supplier, ApiException ex) {
        if (supplier.get()) {
            throw ex;
        }
    }

}
