package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "z_user")
public class User {
    @SequenceGenerator(
            name = "z_user_sequence",
            sequenceName = "z_user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "z_user_sequence"
    )
    private Long id;
    @NotBlank
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your first name")
    private String username;
    @Pattern(regexp = ".+@.+\\..+")
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "user_network")
    private String contactLink;
    @NotBlank
    @Size(min = 3)
    private String password;
    @Column(name = "user_location")
    private String location;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime dateOfCreation;
    @Column(name = "locked")
    private Boolean locked ;

    @Column(name = "enabled")
    private Boolean enabled;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<Book> books;


    public User(String firstName, String lastName, String password, String email, String networkPage, String location,
                List<Role> roles, LocalDateTime dateOfCreation) {
        this.name = firstName;
        this.username = lastName;
        this.password = password;
        this.email = email;
        this.contactLink = networkPage;
        this.location = location;
        this.roles = roles;
        this.dateOfCreation = dateOfCreation;
    }


}
