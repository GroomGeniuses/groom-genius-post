package org.example.entitiy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="survey")
@Getter
@Setter
public class Survey {

    @Id
    private String surveyId;

    private String surveyTitle;

    private String question;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}