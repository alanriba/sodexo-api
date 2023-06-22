package cl.sodexo.service;

import cl.sodexo.config.exception.NotFoundException;
import cl.sodexo.jpa.entity.FavoriteArticleEntity;
import cl.sodexo.jpa.repository.FavoriteArticleRepository;
import cl.sodexo.service.dto.FavoriteArticleDto;
import cl.sodexo.service.mapper.FavoriteArticleMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteArticleServiceTest {

    @InjectMocks
    private FavoriteArticle favoriteArticle;

    @Mock
    private FavoriteArticleMapper favoriteArticleMapper;

    @Mock
    private FavoriteArticleRepository favoriteArticleRepository;

    @Test
    public void testFindByTitle_ExistingFavorites_ReturnsListOfFavorites() {
        // Datos de prueba
        String titulo = "Test";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        List<FavoriteArticleEntity> favoriteEntities = new ArrayList<>();
        favoriteEntities.add(new FavoriteArticleEntity());
        favoriteEntities.add(new FavoriteArticleEntity());
        Page<FavoriteArticleEntity> pageData = new PageImpl<>(favoriteEntities);
        List<FavoriteArticleDto> expectedFavorites = new ArrayList<>();
        expectedFavorites.add(new FavoriteArticleDto());
        expectedFavorites.add(new FavoriteArticleDto());

        // Mock del repositorio
        when(favoriteArticleRepository.findByTitle(titulo, pageable)).thenReturn((List<FavoriteArticleEntity>) pageData);

        // Mock del mapper
        when(favoriteArticleMapper.entityToDto(any(FavoriteArticleEntity.class))).thenReturn(new FavoriteArticleDto());

        // Invocar al método a probar
        List<FavoriteArticleDto> result = favoriteArticle.findByTitle(titulo, page, size);

        // Verificar el resultado
        assertEquals(expectedFavorites.size(), result.size());
    }

    @Test
    public void testFindByTitle_NonExistingFavorites_ThrowsNotFoundException() {
        // Datos de prueba
        String titulo = "Test";
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.unsorted());
        Page<FavoriteArticleEntity> pageData = Page.empty();

        // Mock del repositorio
        when(favoriteArticleRepository.findByTitle(titulo, pageable)).thenReturn((List<FavoriteArticleEntity>) pageData);

        // Invocar y verificar la excepción
        assertThrows(NotFoundException.class, () -> favoriteArticle.findByTitle(titulo, page, size));
    }

    @Test
    public void testFindAll_ExistingFavorites_ReturnsListOfFavorites() {
        // Datos de prueba
        int page = 0;
        int size = 10;
        Sort sort = Sort.unsorted();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<FavoriteArticleEntity> favoriteEntities = new ArrayList<>();
        favoriteEntities.add(new FavoriteArticleEntity());
        favoriteEntities.add(new FavoriteArticleEntity());
        Page<FavoriteArticleEntity> favoritePage = new PageImpl<>(favoriteEntities);
        List<FavoriteArticleDto> expectedFavorites = new ArrayList<>();
        expectedFavorites.add(new FavoriteArticleDto());
        expectedFavorites.add(new FavoriteArticleDto());

        // Mock del repositorio
        when(favoriteArticleRepository.findAll(pageable)).thenReturn(favoritePage);

        // Mock del mapper
        when(favoriteArticleMapper.entityToDto(any(FavoriteArticleEntity.class))).thenReturn(new FavoriteArticleDto());

        // Invocar al método a probar
        List<FavoriteArticleDto> result = favoriteArticle.findAll(sort, pageable, page, size);

        // Verificar el resultado
        assertEquals(expectedFavorites.size(), result.size());
    }

    @Test
    public void testFindAll_NonExistingFavorites_ThrowsNotFoundException() {
        int page = 0;
        int size = 10;
        Sort sort = Sort.unsorted();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<FavoriteArticleEntity> favoritePage = Page.empty();

        // Mock del repositorio
        when(favoriteArticleRepository.findAll(pageable)).thenReturn(favoritePage);

        // Invocar y verificar la excepción
        assertThrows(NotFoundException.class, () -> favoriteArticle.findAll(sort, pageable, page, size));

    }


    @Test
    public void testDeleteById() {
        // Datos de prueba
        Long id = 1L;

        // Invocar al método a probar
        favoriteArticle.deleteById(id);

        // Verificar que el método del repositorio fue llamado con el ID correcto
        verify(favoriteArticleRepository).deleteById(id);
    }

    @Test
    public void testCreate() {
        // Datos de prueba
        FavoriteArticleDto favoriteArticleDto = createFavoriteArticleDto(
                1L,
                "Test",
                "Test",
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Test",
                "Test",
                "Test",
                true,
                Collections.emptyList(),
                Collections.emptyList());


        // Mock del mapper
        FavoriteArticleEntity entity = new FavoriteArticleEntity();
        when(favoriteArticleMapper.dtoToEntity(favoriteArticleDto)).thenReturn(entity);
        FavoriteArticleEntity savedEntity = new FavoriteArticleEntity();
        when(favoriteArticleRepository.save(entity)).thenReturn(savedEntity);
        FavoriteArticleDto savedDto = new FavoriteArticleDto();
        when(favoriteArticleMapper.entityToDto(savedEntity)).thenReturn(savedDto);

        // Invocar al método a probar
        FavoriteArticleDto result = favoriteArticle.create(favoriteArticleDto);

        // Verificar el resultado
        assertEquals(savedDto, result);
    }


    private FavoriteArticleDto createFavoriteArticleDto(
            Long id,
            String title,
            String summary,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String url,
            String imageUrl,
            String newsSite,
            boolean featured,
            List<String> launches,
            List<String> events) {
        FavoriteArticleDto dto = new FavoriteArticleDto();
        dto.setId(Math.toIntExact(id));
        dto.setTitle(title);
        dto.setSummary(summary);
        dto.setPublishedAt(publishedAt);
        dto.setUpdatedAt(updatedAt);
        dto.setUrl(url);
        dto.setImageUrl(imageUrl);
        dto.setNewsSite(newsSite);
        dto.setFeatured(featured);
        dto.setLaunches(launches);
        dto.setEvents(events);
        return dto;
    }

}
