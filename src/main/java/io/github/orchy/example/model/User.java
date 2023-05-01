package io.github.orchy.example.model;

public class User {
    private String userId;

    private String name;

    private int age;

    private String email;

    private String phone;

    private String gender;

    private Address address;

    public User(String userId, String name, int age, String email, String phone, String gender, Address address) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.address =address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
