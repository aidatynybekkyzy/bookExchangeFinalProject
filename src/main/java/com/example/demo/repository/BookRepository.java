package com.example.demo.repository;


import com.example.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBookByNameIgnoreCase(@Param("bookName") String name);
    List<Book> getBooksByISBN(@Param("isbn") String isbn);


    List<Book> findBooksByUserId(Long userId);
}
