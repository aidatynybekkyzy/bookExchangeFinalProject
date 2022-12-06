package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//TODO MAKE A CLASS OF GENRE
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "book_genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;


}
