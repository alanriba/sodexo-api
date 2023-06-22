package cl.sodexo.service;

import cl.sodexo.config.exception.NotFoundException;
import cl.sodexo.jpa.entity.FavoriteArticleEntity;
import cl.sodexo.jpa.repository.FavoriteArticleRepository;
import cl.sodexo.service.dto.FavoriteArticleDto;
import cl.sodexo.service.mapper.FavoriteArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteArticle implements FavoriteArticleService{

    private final FavoriteArticleMapper favoriteArticleMapper;

    private final FavoriteArticleRepository favoriteArticleRepository;

    @Autowired
    public FavoriteArticle(FavoriteArticleMapper favoriteArticleMapper, FavoriteArticleRepository favoriteArticleRepository) {
        this.favoriteArticleMapper = favoriteArticleMapper;
        this.favoriteArticleRepository = favoriteArticleRepository;
    }
    @Override
    public List<FavoriteArticleDto> findByTitle(String titulo, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        var pageData = favoriteArticleRepository.findByTitle(titulo, pageable);

        if(pageData.isEmpty()) {
            throw new NotFoundException("No se encontraron favoritos con el título: " + titulo);
        }
        return pageData.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<FavoriteArticleDto> findAll(Sort sort, Pageable pageable, int page, int size) {
        Page<FavoriteArticleEntity> favoritePage = favoriteArticleRepository.findAll(PageRequest.of(page, size, sort));

        if (favoritePage.isEmpty()) {
            throw new NotFoundException("No se encontraron favoritos.");
        }

        Page<FavoriteArticleDto> dtoPage = favoritePage.map(this::convertEntityToDto);
        return dtoPage.getContent();
    }


    @Override
    public void deleteById(Long id) {
        favoriteArticleRepository.deleteById(id);
    }

    @Override
    public FavoriteArticleDto create(FavoriteArticleDto favoriteArticle) {
        FavoriteArticleEntity entity = favoriteArticleMapper.dtoToEntity(favoriteArticle);
        FavoriteArticleEntity savedEntity = favoriteArticleRepository.save(entity);
        return favoriteArticleMapper.entityToDto(savedEntity);
    }


    private FavoriteArticleDto convertEntityToDto(FavoriteArticleEntity entity){
        return favoriteArticleMapper.entityToDto(entity);
    }
}
