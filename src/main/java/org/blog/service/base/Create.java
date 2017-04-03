package org.blog.service.base;

import java.util.function.Function;

/**
 * Created by Anand_Rajneesh on 3/30/2017.
 */
public class Create<T> {


    public T create(Function<T, T> operation, T t){
        return operation.apply(t);
    }

}
