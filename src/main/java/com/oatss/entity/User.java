package com.oatss.entity;
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String idCard;
    private String telephone;
    private String role;
    public User() {

    }
    public User(Integer id, String username, String password, String name, String idCard, String telephone, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.idCard = idCard;
        this.telephone = telephone;
        this.role = role;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}