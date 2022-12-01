package com.example.demo.domain;

import javax.persistence.*;

import javax.validation.constraints.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotBlank
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    @Email
    @Size(min = 6, max = 80)
    @NotBlank(message = "Invalid registration}")
    @Pattern(regexp = ".+@.+\\..+")
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "name")
    @NotEmpty(message = "Please provide your first name")
    private String name;
    @Column(name = "user_location")
    private String location;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime dateOfCreation;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_book",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<PostOfBook> postOfBooks;

    public User() {
    }

    public User(String username, String password, String email, String name, String location) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.location = location;
    }

    public User(String username, String password, String email,
                String name, String location, Collection<Role> roles,
                LocalDateTime dateOfCreation, Boolean isActive, Set<Book> books,
                Set<PostOfBook> postOfBooks) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.location = location;
        this.roles = roles;
        this.dateOfCreation = dateOfCreation;
        this.isActive = isActive;
        this.books = books;
        this.postOfBooks = postOfBooks;
    }
}
