package com.recruitment.crud;

import java.util.Date;

public class Client {

    private Integer id;
    private Date creationDate;
    private String firstName;
    private String lastName;
    private Integer phoneNumber;
    private String comment;

    public Client(Client client){
        this.id = client.id;
        this.creationDate = client.creationDate;
        this.firstName = client.firstName;
        this.lastName = client.lastName;
        this.phoneNumber = client.phoneNumber;
        this.comment = client.comment;
    }
    
    private Client(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
        this.comment = builder.comment;
    }

    public static class Builder {

        private String firstName;
        private String lastName;
        private Integer phoneNumber;
        private String comment = "";

        public Builder(Client client) {
            this.firstName = client.firstName;
            this.lastName = client.lastName;
            this.phoneNumber = client.phoneNumber;
            this.comment = client.comment;
        }

        public Builder(String firstName, String lastName, Integer phoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
