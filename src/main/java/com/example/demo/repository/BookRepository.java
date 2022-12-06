package com.example.demo.repository;


import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByUserId(Long id);

    List<Book> findBooksByUserId(Long userId);

    @Query(nativeQuery = true, value = "select * from a_book b join book_image bi on b.book_id = bi.book_id" +
            " order by b.book_id desc limit 10")
    List<Book> getLastAddedBooks(int count);
}
