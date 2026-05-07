package com.thomasmylonas.petstore_api_app.entities;

import com.thomasmylonas.petstore_api_app.enums.UserStatus;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity(name = "User")
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_Sequence_Generator")
    @SequenceGenerator(name = "Users_Sequence_Generator", sequenceName = "Users_Sequence", initialValue = 1, allocationSize = 1)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "First_Name")
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "Phone")
    private String phone;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "User_Status")
    private UserStatus userStatus;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(phone, user.phone) && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email, password, phone, userStatus);
    }
}

/*
{
    "username": "lorak",
    "first_name": "Karolina Miroslavina",
    "last_name": "Kuek",
    "email": "lorak@mail.com",
    "password": "lorak",
    "phone": "696969699",
    "user_status": 1
}
-----------------------------------------------------------------
Swagger model:
------------------
User{
    id	        integer($int64)
    username	string
    firstName	string
    lastName	string
    email	    string
    password	string
    phone	    string
    userStatus	integer($int32) // User Status
}
*/
