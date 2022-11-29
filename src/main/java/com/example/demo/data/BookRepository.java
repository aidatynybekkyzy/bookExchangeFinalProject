package com.example.demo.data;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE lower(b.name) LIKE coalesce(lower(cast(CONCAT('%',:name,'%') as text)),lower(b.name)) " +
            "and lower(b.author) LIKE coalesce(lower(cast(CONCAT('%',:author,'%') as text)),lower(b.author))")
    List<Book> findBooksByNameAndAuthor(@Param("name") String name, @Param("author") String author);


    @Query("SELECT b FROM Book b where user_id = ?1 ")
    Book findBookByUserId(@Param("user_id") Long userId);


}
