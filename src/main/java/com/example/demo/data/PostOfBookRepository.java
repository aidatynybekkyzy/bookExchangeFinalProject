package com.example.demo.data;

import com.example.demo.entity.PostOfBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostOfBookRepository extends JpaRepository<PostOfBook,Long> {
    @Query("select p from PostOfBook p " +
            "where p.login = coalesce(cast(:login as text), p.login)")
    List<PostOfBook> getPostsOfUser(@Param("login")String userLogin);

    @Query("select p from PostOfBook p join fetch p.book b " +
            "where lower(b.author) like coalesce (lower(cast(concat('%',:author,'%')as text)),lower(b.author)) and" +
            " lower(b.name) like coalesce (lower(cast(concat('%',:name,'%')as text)),lower(b.name))")
    List <PostOfBook> getPostsByBookNameAuthor(@Param("author")String author, @Param("name")String bookName);

    @Query("SELECT b FROM book_posts b where user_id = ?1 ")
    List<PostOfBook> findPostByUserId(Long userId);


}
