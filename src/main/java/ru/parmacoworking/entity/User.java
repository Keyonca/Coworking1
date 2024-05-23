package ru.parmacoworking.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "user", schema = "coworking")
@NoArgsConstructor
@Getter
@Setter
public class User {

    public static String TYPE_NAME = "Пользователь";



    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phone_number;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "form_of_work", nullable = false)
    private String form_of_work;


    public User(Long id, String surname, String first_name, String last_name, String email, String password, String phone_number, String photo, String form_of_work) {
        this.id = id;
        this.surname = surname;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.photo = photo;
        this.form_of_work = form_of_work;
    }

    @Transient // Эта аннотация указывает, что поле не должно быть сохранено в базе данных
    private Collection<? extends GrantedAuthority> authorities;

    public User(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.authorities =  authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getForm_of_work() {
        return form_of_work;
    }

    public void setForm_of_work(String form_of_work) {
        this.form_of_work = form_of_work;
    }


}
