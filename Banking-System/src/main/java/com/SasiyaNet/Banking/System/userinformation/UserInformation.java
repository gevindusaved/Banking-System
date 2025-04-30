package com.SasiyaNet.Banking.System.userinformation;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "userinformations")
public class UserInformation {
    @Id
    private ObjectId id;
    @Field("user_information_id")
    private String userInformationId;
    @Field("first_name")
    private String firstName;

    @Field("middle_name")
    private String middleName;

    @Field("last_name")
    private String lastName;

    private LocalDate date_of_birth;
    private String gender;
    private String nationality;
    private String marital_status;
    private String occupation;
    private String employer;
    private String mobile;
    private String alternate_phone;
    private String email;
    private String address;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String mailing_address;
    private String id_type;
    private String id_number;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate id_issue_date;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate id_expiry_date;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field("createdAt")
    private LocalDateTime createdAt;


    
    public UserInformation() {}

    public UserInformation(
        ObjectId id,
        String userInformationId,
        String firstName,
        String middleName,
        String lastName,
        LocalDate date_of_birth,
        String gender,
        String nationality,
        String marital_status,
        String occupation,
        String employer,
        String mobile,
        String alternate_phone,
        String email,
        String address,
        String street,
        String city,
        String state,
        String zip,
        String country,
        String mailing_address,
        String id_type,
        String id_number,
        LocalDate id_issue_date,
        LocalDate id_expiry_date,
        LocalDateTime createdAt
    ) {
        this.id = id;
        this.userInformationId = userInformationId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.nationality = nationality;
        this.marital_status = marital_status;
        this.occupation = occupation;
        this.employer = employer;
        this.mobile = mobile;
        this.alternate_phone = alternate_phone;
        this.email = email;
        this.address = address;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.mailing_address = mailing_address;
        this.id_type = id_type;
        this.id_number = id_number;
        this.id_issue_date = id_issue_date;
        this.id_expiry_date = id_expiry_date;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(String userInformationId) {
        this.userInformationId = userInformationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAlternate_phone() {
        return alternate_phone;
    }

    public void setAlternate_phone(String alternate_phone) {
        this.alternate_phone = alternate_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public LocalDate getId_issue_date() {
        return id_issue_date;
    }

    public void setId_issue_date(LocalDate id_issue_date) {
        this.id_issue_date = id_issue_date;
    }

    public LocalDate getId_expiry_date() {
        return id_expiry_date;
    }

    public void setId_expiry_date(LocalDate id_expiry_date) {
        this.id_expiry_date = id_expiry_date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
