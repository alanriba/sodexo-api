package cl.sodexo.service.mapper;

import cl.sodexo.jpa.entity.FavoriteArticleEntity;
import cl.sodexo.service.dto.FavoriteArticleDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FavoriteArticleMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    FavoriteArticleEntity dtoToEntity(FavoriteArticleDto favoriteArticleDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    FavoriteArticleDto entityToDto(FavoriteArticleEntity favoriteArticleRepository);


}
