package com.example.demo.entity;

import javax.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "User")
@Table(name ="user_account")
public class User {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Size(min = 3)
    @Column(name = "user_login")
    private String login;

    @Email
    @Size(min = 6, max = 80)
    @NotBlank(message = "Invalid registration}")
    @Column(name = "user_email", unique = true)
    private String email;

    @Column(name = "name")
    @NotEmpty(message = "Please provide your first name")
    private String name;

    @Column(name = "user_location")
    private String location;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime dateOfCreation;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Book> books;

    @OneToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<PostOfBook> postOfBooks;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    public User() {
    }

    public User(String login, String email, String name,
                String location, String password, Role role,
                LocalDateTime dateOfCreation, Boolean isActive) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.login = login;
        this.email = email;
        this.name = name;
        this.location = location;
        this.password = passwordEncoder.encode(password);
        this.role = role;
        this.dateOfCreation = dateOfCreation;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Set<PostOfBook> getPostOfBooks() {
        return postOfBooks;
    }

    public void setPostOfBooks(Set<PostOfBook> postOfBooks) {
        this.postOfBooks = postOfBooks;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
/*@OneToMany(mappedBy = "userFrom", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Request> requestsFrom;

    @OneToMany(mappedBy = "userTo", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private List<Request> requestsTo;*/
}
