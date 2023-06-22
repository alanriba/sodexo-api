package cl.sodexo.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Table(name = "favorite_articles")
public class FavoriteArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(length = 1000)
    private String summary;

    @Column(nullable = false)
    private LocalDateTime publishedAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private String url;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private String newsSite;

    @Column(nullable = false)
    private boolean featured;

    @ElementCollection
    private List<String> launches;

    @ElementCollection
    private List<String> events;
}
