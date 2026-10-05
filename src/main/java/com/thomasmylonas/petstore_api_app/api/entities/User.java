package com.thomasmylonas.petstore_api_app.api.entities;

import com.thomasmylonas.petstore_api_app.api.enums.UserStatus;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_Id_Seq_Generator")
    @SequenceGenerator(name = "Users_Id_Seq_Generator", sequenceName = "Users_Id_Seq", initialValue = 1, allocationSize = 1)
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
        return Objects.equals(username, user.username)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(phone, user.phone)
                && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, email, password, phone, userStatus);
    }
}
