package ru.parmacoworking.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "booking_history_ref", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class Booking_history_ref {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workplace_id")
    private Long workplace_id;

    @Column(name = "booking_history_id")
    private Long booking_history_id;

}
