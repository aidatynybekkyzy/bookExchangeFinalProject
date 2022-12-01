package com.example.demo.repository;


import com.example.demo.domain.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookImageRepository extends JpaRepository <BookImage,Long> {
}
