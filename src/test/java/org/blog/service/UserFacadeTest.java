package org.blog.service;

import org.blog.dao.UserRepository;
import org.blog.exceptions.ResourceAlreadyExistsException;
import org.blog.exceptions.ResourceNotFoundException;
import org.blog.exceptions.ValidationException;
import org.blog.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 * Created by Anand_Rajneesh on 4/3/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserFacadeTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserFacade testObject;

    private User user;

    private String id;

    @Before
    public void setUp(){
        id = "1";
        user = new User();
        user.setEmail("dummyemail@email.com");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setId(id);
    }

    @Test
    public void create_PositiveScenario(){
        user.setId(null);
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).then(invocationOnMock -> {
            user.setId(id);
            return user;
        });
        User savedUser = testObject.create(user);
        assertNotNull("#create() should return user object after successful save",
                savedUser);
        assertNotNull("user object returned from #create() should have non null id",
                savedUser.getId());
        testUserPropertiesEquality(user, savedUser);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    /**FIXME - Modify lambdas for early processing and fail fast to
     * FIXME avoid null pointer exceptions on subsequent lambda invocations
     *
     * TODO - Failing Junit
     **/
    @Test(expected = ValidationException.class)
    public void create_UserIsNull(){
        user = null;
        try {
            testObject.create(user);
        }catch (Exception e) {
            verify(userRepository, never()).findByEmail(anyString());
            verify(userRepository, never()).save(any(User.class));
            throw e;
        }
    }

    @Test(expected = ValidationException.class)
    public void create_UserEmailIsNull(){
        user.setEmail(null);
        try {
            testObject.create(user);
        }catch (Exception e) {
            verify(userRepository, never()).findByEmail(anyString());
            verify(userRepository, never()).save(any(User.class));
            throw e;
        }
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void create_UserAlreadyExists(){
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        try {
            testObject.create(user);
        }catch (Exception e) {
            verify(userRepository, times(1)).findByEmail(anyString());
            verify(userRepository, never()).save(any(User.class));
            throw e;
        }
    }

    @Test
    public void get_AllPositive(){
        when(userRepository.findOne(anyString())).thenReturn(user);
        User fetchedUser = testObject.get(id);
        assertNotNull("#get() should return non null user object if found in db",
                fetchedUser);
        assertEquals("id of fetched user object should be same as input parameter",
                fetchedUser.getId(),id);
        testUserPropertiesEquality(fetchedUser, user);
    }

    @Test(expected = ValidationException.class)
    public void get_NullId(){
        try{
            testObject.get(null);
        }catch(Exception e){
            verify(userRepository, never()).findOne(anyString());
            throw e;
        }
    }

    @Test(expected = ResourceNotFoundException.class)
    public void get_NoUserInDb(){
        when(userRepository.findOne(anyString())).thenReturn(null);
        try{
            testObject.get("1");
        }catch(Exception e){
            verify(userRepository, times(1)).findOne(anyString());
            throw e;
        }
    }

    @Test(expected = ValidationException.class)
    public void getUserByEmail_NullEmail(){
        String email = null;
        try{
            testObject.getByEmail(email);
        }catch (Exception e){
            verify(userRepository, never()).findByEmail(anyString());
            throw e;
        }
    }

    @Test
    public void getUserByEmail_ExistingRecord(){
        String email = user.getEmail();
        when(userRepository.findByEmail(email)).thenReturn(user);
        testUserPropertiesEquality(testObject.getByEmail(email), user);
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    public void getUserByEmail_NotExistingRecord(){
        String email = "notexistent@null.com";
        when(userRepository.findByEmail(email)).thenReturn(null);
        assertNull(testObject.getByEmail(email));
    }

    public void testUserPropertiesEquality(User u1, User u2){
        assertEquals(u1.getEmail(), u2.getEmail());
        assertEquals(u1.getFirstName(), u2.getFirstName());
        assertEquals(u1.getLastName(), u2.getLastName());
    }



}
