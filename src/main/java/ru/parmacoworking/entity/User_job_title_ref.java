package ru.parmacoworking.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_job_title_ref", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class User_job_title_ref {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "job_title_id")
    private Long job_title_id;

    @Column(name = "is_actual", nullable = false)
    private boolean is_actual;

}
