package com.example.demo.mappers;

import com.example.demo.dto.BookMainInfoDto;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMainInfoMapper {
    BookMainInfoDto toDto(Book book);

    Book toEntity(BookMainInfoDto bookMainInfoDto);
}
