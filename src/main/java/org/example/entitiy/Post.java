package org.example.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "post")
@Getter
@Setter
@Access(AccessType.FIELD)
public class Post {
    @Id
    private String postId = UUID.randomUUID().toString();

    private String title;

    private String content;

    private Date postAt;

    private int views = 0;

    private int interests = 0;

    private int applicants = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
