package com.example.demo.dto;

import com.example.demo.entity.Book;
import com.example.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String contactLink;
    private String location;
    private List<Role> role;
    private Set<Book> books;

}
