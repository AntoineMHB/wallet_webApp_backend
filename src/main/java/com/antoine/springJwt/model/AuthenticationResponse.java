package com.antoine.springJwt.model;

public class AuthenticationResponse {

    private String token;
    private Integer userId;
    private String firstname;
    private String lastname;
    private String email;

    public AuthenticationResponse(String token, Integer userId, String firstname, String lastname, String email) {
        this.token = token;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    


   

  
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }



}
