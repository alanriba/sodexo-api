package cl.sodexo.controller;

import cl.sodexo.service.dto.FavoriteArticleDto;
import cl.sodexo.service.FavoriteArticleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite-articles")
public class FavoriteArticleController {

    private final FavoriteArticleService favoriteArticleService;

    @Autowired
    public FavoriteArticleController(FavoriteArticleService favoriteArticleService) {
        this.favoriteArticleService = favoriteArticleService;
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search favorites by title", notes = "Retrieve a list of favorite articles based on the provided title")
    public ResponseEntity<List<FavoriteArticleDto>> findByTitle(@RequestParam String title, @RequestParam int page, @RequestParam int size) {
        List<FavoriteArticleDto> favorites = favoriteArticleService.findByTitle(title, page, size);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping
    @ApiOperation(value = "Get all favorites", notes = "Retrieve a list of all favorite articles")
    public ResponseEntity<List<FavoriteArticleDto>> findAll(Pageable pageable, @RequestParam int page, @RequestParam int size) throws InstantiationException, IllegalAccessException {
        List<FavoriteArticleDto> favorites = favoriteArticleService.findAll(Sort.unsorted(), pageable, page, size);
        return ResponseEntity.ok(favorites);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete favorite by ID", notes = "Delete a favorite article based on the provided ID")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        favoriteArticleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation(value = "Create a new favorite", notes = "Create a new favorite article")
    public ResponseEntity<FavoriteArticleDto> create(@RequestBody FavoriteArticleDto favoriteArticleDto) {
        FavoriteArticleDto createdFavorite = favoriteArticleService.create(favoriteArticleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFavorite);
    }
}

