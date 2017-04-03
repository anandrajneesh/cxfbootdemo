package org.blog.utils;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
public class Validator {

    public static <T> boolean notNull(T obj){
        return Optional.ofNullable(obj).isPresent();
    }

}
