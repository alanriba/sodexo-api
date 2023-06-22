package cl.sodexo.service;


import cl.sodexo.service.dto.FavoriteArticleDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface FavoriteArticleService {
    List<FavoriteArticleDto> findByTitle(String titulo, int page, int size);

    List<FavoriteArticleDto> findAll(Sort sort, Pageable pageable, int page, int size) throws InstantiationException, IllegalAccessException;

    void deleteById(Long id);

    FavoriteArticleDto create(FavoriteArticleDto favoriteArticle);
}
