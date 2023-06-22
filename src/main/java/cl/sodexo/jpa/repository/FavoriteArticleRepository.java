package cl.sodexo.jpa.repository;

import cl.sodexo.jpa.entity.FavoriteArticleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticleEntity, Long>, JpaSpecificationExecutor<FavoriteArticleEntity> {

    List<FavoriteArticleEntity> findByTitle(String titulo, Pageable pageable);

    List<FavoriteArticleEntity> findAll(Sort sort, Pageable pageable);

    void deleteById(Long id);
}