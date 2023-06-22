package cl.sodexo.service.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteArticleDto {

    private int id;
    private String title;
    private String summary;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
    private String url;
    private String imageUrl;
    private String newsSite;
    private boolean featured;
    private List<String> launches;
    private List<String> events;
}
