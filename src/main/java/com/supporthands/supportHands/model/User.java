package com.supporthands.supportHands.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@JsonPropertyOrder({"userMail", "userPass"})
@JsonIgnoreProperties(value = {"userId"}, allowGetters = true)
public class User extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    @JsonIgnore
    private int userId;

    @NotNull
    @Email
    @Size(max = 100)
    @Pattern(regexp=".+@.+\\..+", message="Wrong email!")
    @Column(name = "email_address", unique = true)
    @JsonProperty("Mail Id")
    private String emailAddress;

    @NotNull
    @Size(min=8, message="Password should have atleast 8 characters")
    @Column(name = "password")
    @JsonProperty("Password")
    private String password;


    @NotNull
    @Column(name = "first_name")
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @Column(name = "surname")
    @JsonProperty("surname")
    private String surname;

    @NotNull
    @Column(name = "mobile_number")
    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @NotNull
    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @NotNull
    @Column(name = "username")
    @JsonProperty("username")
    private String username;



}
