package ru.efive.start.rhino.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_generator")
    @Column(name="user_id")
    private long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String hashedPassword;

    public User() {

    }

    public User(String email, String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException, CloneNotSupportedException {
        setEmail(email);
        setHashedPassword(hashPassword(password));
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException, CloneNotSupportedException {
        return password;
    }

    public long getId() {
        return id;
    }

}
