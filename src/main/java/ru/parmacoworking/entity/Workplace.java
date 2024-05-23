package ru.parmacoworking.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "workplace", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class Workplace {

    public static String TYPE_NAME = "Рабочее место";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_anyone_sitting_here", nullable = false)
    private boolean is_anyone_sitting_here;

    public Workplace(Long id, String name, boolean is_anyone_sitting_here) {
        this.id = id;
        this.name = name;
        this.is_anyone_sitting_here = is_anyone_sitting_here;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIs_anyone_sitting_here() {
        return is_anyone_sitting_here;
    }

    public void setIs_anyone_sitting_here(boolean is_anyone_sitting_here) {
        this.is_anyone_sitting_here = is_anyone_sitting_here;
    }
}
