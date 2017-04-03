package org.blog.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * Created by Anand_Rajneesh on 4/3/2017.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ValidatorTest {

    @Test
    public void testNotNull_NotNullObject(){
        assertTrue("#notNull(T obj) should return true for not null object", Validator.notNull(new Object()));
    }

    @Test
    public void testNotNull_NullObject(){
        assertFalse("#notNull(T obj) should return false for null object", Validator.notNull(null));
    }

}
