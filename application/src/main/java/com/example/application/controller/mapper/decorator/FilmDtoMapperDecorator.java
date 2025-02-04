package com.example.application.controller.mapper.decorator;

import com.example.api.model.FilmDto;
import com.example.application.controller.mapper.FilmDtoMapper;
import com.example.application.domain.Film;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

@Setter(onMethod_ = @Autowired)
public abstract class FilmDtoMapperDecorator implements FilmDtoMapper {

    private FilmDtoMapper delegate;

    @Override
    public Film toDomain(FilmDto filmDto) {
        Film film = delegate.toDomain(filmDto);
        Film.FilmBuilder filmBuilder = film.toBuilder();

        if (film.genres() == null) {
            filmBuilder.genres(Collections.emptySet());
        }
        return filmBuilder.build();
    }
}
