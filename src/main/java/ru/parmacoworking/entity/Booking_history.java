package ru.parmacoworking.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "booking_history", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class Booking_history {

    public static String TYPE_NAME = "История бронирования";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date_and_time", nullable = false)
    private Date start_date_and_time;

    @Column(name = "end_date_and_time", nullable = false)
    private Date end_date_and_time;


    public Booking_history(Long id, Date start_date_and_time, Date end_date_and_time) {
        this.id = id;
        this.start_date_and_time = start_date_and_time;
        this.end_date_and_time = end_date_and_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart_date_and_time() {
        return start_date_and_time;
    }

    public void setStart_date_and_time(Date start_date_and_time) {
        this.start_date_and_time = start_date_and_time;
    }

    public Date getEnd_date_and_time() {
        return end_date_and_time;
    }

    public void setEnd_date_and_time(Date end_date_and_time) {
        this.end_date_and_time = end_date_and_time;
    }
}
