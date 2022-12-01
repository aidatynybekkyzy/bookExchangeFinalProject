package com.example.demo.repository;

import com.example.demo.domain.PostOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostOfBookRepository extends JpaRepository<PostOfBook,Long> {


    List<PostOfBook> findPostOfBookByUser_UsernameIgnoreCaseOrderByDatePosted(@Param("username")String username);


}
