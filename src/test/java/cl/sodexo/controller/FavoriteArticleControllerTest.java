package cl.sodexo.controller;

import cl.sodexo.service.FavoriteArticleService;
import cl.sodexo.service.dto.FavoriteArticleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FavoriteArticleController.class)
public class FavoriteArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteArticleService favoriteArticleService;

    // Método auxiliar para crear un objeto FavoriteArticleDto
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
    @Test
    void testFindByTitle() throws Exception {
        // Mock del servicio
        // Mock del servicio
        List<FavoriteArticleDto> favorites = new ArrayList<>();
        favorites.add(createFavoriteArticleDto(1L, "Title 1", "Summary 1", LocalDateTime.now(), LocalDateTime.now(), "https://example.com/1", "https://example.com/image1.jpg", "News Site 1", true, Collections.emptyList(), Collections.emptyList()));
        favorites.add(createFavoriteArticleDto(2L, "Title 2", "Summary 2", LocalDateTime.now(), LocalDateTime.now(), "https://example.com/2", "https://example.com/image2.jpg", "News Site 2", false, Collections.emptyList(), Collections.emptyList()));
        when(favoriteArticleService.findByTitle(anyString(), anyInt(), anyInt())).thenReturn(favorites);

        // Realizar la petición GET
        mockMvc.perform(get("/favorite-articles/search")
                        .param("title", "Test")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Verificar la cantidad de resultados
                .andExpect(jsonPath("$[0].id").value(1)) // Verificar el ID del primer resultado
                .andExpect(jsonPath("$[0].title").value("Title 1")) // Verificar el título del primer resultado
                .andExpect(jsonPath("$[0].summary").value("Summary 1")) // Verificar el resumen del primer resultado
                .andExpect(jsonPath("$[0].publishedAt").exists()) // Verificar la existencia de la fecha de publicación del primer resultado
                .andExpect(jsonPath("$[0].updatedAt").exists()) // Verificar la existencia de la fecha de actualización del primer resultado
                .andExpect(jsonPath("$[0].url").value("https://example.com/1")) // Verificar la URL del primer resultado
                .andExpect(jsonPath("$[0].imageUrl").value("https://example.com/image1.jpg")) // Verificar la URL de la imagen del primer resultado
                .andExpect(jsonPath("$[0].newsSite").value("News Site 1")) // Verificar el sitio de noticias del primer resultado
                .andExpect(jsonPath("$[0].featured").value(true)) // Verificar si el primer resultado está destacado
                .andExpect(jsonPath("$[0].launches").isEmpty()) // Verificar que no haya lanzamientos asociados al primer resultado
                .andExpect(jsonPath("$[0].events").isEmpty()) // Verificar que no haya eventos asociados al primer resultado
                .andExpect(jsonPath("$[1].id").value(2)) // Verificar el ID del segundo resultado
                .andExpect(jsonPath("$[1].title").value("Title 2")) // Verificar el título del segundo resultado
                .andExpect(jsonPath("$[1].summary").value("Summary 2")) // Verificar el resumen del segundo resultado
                .andExpect(jsonPath("$[1].publishedAt").exists()) // Verificar la existencia de la fecha de publicación del segundo resultado
                .andExpect(jsonPath("$[1].updatedAt").exists()) // Verificar la existencia de la fecha de actualización del segundo resultado
                .andExpect(jsonPath("$[1].url").value("https://example.com/2")) // Verificar la URL del segundo resultado
                .andExpect(jsonPath("$[1].imageUrl").value("https://example.com/image2.jpg")) // Verificar la URL de la imagen del segundo resultado
                .andExpect(jsonPath("$[1].newsSite").value("News Site 2")) // Verificar el sitio de noticias del segundo resultado
                .andExpect(jsonPath("$[1].featured").value(false)) // Verificar si el segundo resultado está destacado
                .andExpect(jsonPath("$[1].launches").isEmpty()) // Verificar que no haya lanzamientos asociados al segundo resultado
                .andExpect(jsonPath("$[1].events").isEmpty()); // Verificar que no haya eventos asociados al segundo resultado

        // Verificar que el método del servicio fue llamado con los parámetros correctos
        verify(favoriteArticleService).findByTitle("Test", 0, 10);
    }

    @Test
    void testFindByTitle_NotFound() throws Exception {
        // Mock del servicio
        when(favoriteArticleService.findByTitle(anyString(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        // Realizar la petición GET
        mockMvc.perform(get("/favorite-articles/search")
                        .param("title", "Test")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isNotFound());

        // Verificar que el método del servicio fue llamado con los parámetros correctos
        verify(favoriteArticleService).findByTitle("Test", 0, 10);
    }

    @Test
    void testFindAll() throws Exception {
        // Mock del servicio
        List<FavoriteArticleDto> favorites = new ArrayList<>();
        favorites.add(createFavoriteArticleDto(1L, "Title 1", "Summary 1", LocalDateTime.now(), LocalDateTime.now(), "https://example.com/1", "https://example.com/image1.jpg", "News Site 1", true, Collections.emptyList(), Collections.emptyList()));
        favorites.add(createFavoriteArticleDto(2L, "Title 2", "Summary 2", LocalDateTime.now(), LocalDateTime.now(), "https://example.com/2", "https://example.com/image2.jpg", "News Site 2", false, Collections.emptyList(), Collections.emptyList()));
        when(favoriteArticleService.findAll(any(Sort.class), any(Pageable.class), anyInt(), anyInt())).thenReturn(favorites);

        // Realizar la petición GET
        mockMvc.perform(get("/favorite-articles")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Verificar la cantidad de resultados
                .andExpect(jsonPath("$[0].title").value("Title 1")) // Verificar el primer resultado
                .andExpect(jsonPath("$[0].content").value("Content 1")) // Verificar el primer resultado
                .andExpect(jsonPath("$[1].title").value("Title 2")) // Verificar el segundo resultado
                .andExpect(jsonPath("$[1].content").value("Content 2")); // Verificar el segundo resultado

        // Verificar que el método del servicio fue llamado con los parámetros correctos
        verify(favoriteArticleService).findAll(any(Sort.class), any(Pageable.class), eq(0), eq(10));
    }

    @Test
    void testFindAll_NotFound() throws Exception {
        // Mock del servicio
        when(favoriteArticleService.findAll(any(Sort.class), any(Pageable.class), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        // Realizar la petición GET
        mockMvc.perform(get("/favorite-articles")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isNotFound());

        // Verificar que el método del servicio fue llamado con los parámetros correctos
        verify(favoriteArticleService).findAll(any(Sort.class), any(Pageable.class), eq(0), eq(10));
    }

    @Test
    public void testDeleteById() throws Exception {
        // Realizar la petición DELETE
        mockMvc.perform(delete("/favorite-articles/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verificar que el método del servicio fue llamado con el ID correcto
        verify(favoriteArticleService).deleteById(1L);
    }

    @Test
    void testCreate() throws Exception {
        // Datos de prueba
        FavoriteArticleDto favoriteArticleDto = new FavoriteArticleDto();
        favoriteArticleDto.setTitle("Title");
        favoriteArticleDto.setSummary("Summary");
        // ...

        // Mock del servicio
        FavoriteArticleDto createdFavorite = new FavoriteArticleDto();
        createdFavorite.setId(1);
        createdFavorite.setTitle("Title");
        createdFavorite.setSummary("Summary");
        // ...

        when(favoriteArticleService.create(any(FavoriteArticleDto.class))).thenReturn(createdFavorite);

        // Realizar la petición POST
        mockMvc.perform(post("/favorite-articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(favoriteArticleDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L)) // Verificar el ID del favorito creado
                .andExpect(jsonPath("$.title").value("Title")) // Verificar el título del favorito creado
                .andExpect(jsonPath("$.summary").value("Summary")); // Verificar el resumen del favorito creado

        // Verificar que el método del servicio fue llamado con el DTO correcto
        verify(favoriteArticleService).create(any(FavoriteArticleDto.class));
    }


    // Método auxiliar para convertir objetos a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
