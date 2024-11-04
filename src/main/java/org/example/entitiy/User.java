package org.example.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    private String userId;

    private String userName;

    private String userPassword;

    private String image;

    private String role;

    private String provider;

    private String providerId;

    private String introduce;
}
