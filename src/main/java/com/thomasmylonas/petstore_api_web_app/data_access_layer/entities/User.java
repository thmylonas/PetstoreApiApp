package com.thomasmylonas.petstore_api_web_app.data_access_layer.entities;

import com.thomasmylonas.petstore_api_web_app.service_layer.models.enums.UserStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Generator")
    @SequenceGenerator(name = "User_Generator", sequenceName = "User_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "User_Id")
    private int id;

    @Column(name = "Username")
    private String username;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "User_Status")
    @Enumerated(value = EnumType.ORDINAL)
    private UserStatusEnum userStatus;
}

/*
User{
    id	integer($int64)
    username	string
    firstName	string
    lastName	string
    email	string
    password	string
    phone	string
    userStatus	integer($int32) // User Status
}
*/
