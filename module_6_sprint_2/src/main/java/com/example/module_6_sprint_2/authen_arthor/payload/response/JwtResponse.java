package com.example.module_6_sprint_2.authen_arthor.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> listRoles;

    public JwtResponse() {
    }

    public JwtResponse(String token, String username, List<String> listRoles) {
        this.token = token;
        this.username = username;
        this.listRoles = listRoles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
