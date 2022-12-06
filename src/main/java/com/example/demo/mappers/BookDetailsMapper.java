package com.example.demo.mappers;

import com.example.demo.dto.BookDetailsDto;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookDetailsMapper {
    @Mapping(source = "author", target = "author")
    BookDetailsDto toDto(Book book);

    Book toEntity(BookDetailsDto bookDetailsDto);

}
