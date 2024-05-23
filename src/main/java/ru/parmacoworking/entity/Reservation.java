package ru.parmacoworking.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservation", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    public static String TYPE_NAME = "Бронирование";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "workplace_id")
    private Long workplace_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "start_date_and_time")
    private Date start_date_and_time;

    @Column(name = "end_date_and_time")
    private Date end_date_and_time;

    public Reservation(Long id, Long workplace_id, Long user_id, Date start_date_and_time, Date end_date_and_time) {
        this.id = id;
        this.workplace_id = workplace_id;
        this.user_id = user_id;
        this.start_date_and_time = start_date_and_time;
        this.end_date_and_time = end_date_and_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkplace_id() {
        return workplace_id;
    }

    public void setWorkplace_id(Long workplace_id) {
        this.workplace_id = workplace_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
