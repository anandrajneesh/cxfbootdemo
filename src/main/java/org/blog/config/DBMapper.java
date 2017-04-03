package org.blog.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anand_Rajneesh on 3/27/2017.
 */
@Component("dbMapper")
public class DBMapper {

    public String getUserMapping(){
        return "user";
    }
}
