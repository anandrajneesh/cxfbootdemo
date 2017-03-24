package org.blog.models;

/**
 * Created by Anand_Rajneesh on 3/23/2017.
 */
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
