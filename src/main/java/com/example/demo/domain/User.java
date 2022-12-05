package com.example.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "z_user")
public class User implements UserDetails {
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
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please provide your first name")
    private String lastName;
    @Pattern(regexp = ".+@.+\\..+")
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank
    @Size(min = 3)
    private String password;

    @Column(name = "user_location")
    private String location;
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime dateOfCreation;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<Book> books;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<PostOfBook> postOfBooks;

    public User(String firstName, String lastName, String password, String email, String location,
                Role role, LocalDateTime dateOfCreation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.location = location;
        this.role = role;
        this.dateOfCreation = dateOfCreation;
    }

    public User(String firstName, String lastName, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
