package com.example.demo.mappers;

import com.example.demo.dto.BookCreationDto;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookCreationMapper {
    BookCreationMapper INSTANCE = Mappers.getMapper(BookCreationMapper.class);

    @Mapping(source = "author", target = "author")
    BookCreationDto toDto(Book book);

    Book toEntity(BookCreationDto bookCreationDto);

}
