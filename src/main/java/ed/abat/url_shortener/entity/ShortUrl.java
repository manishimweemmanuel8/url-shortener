package ed.abat.url_shortener.entity;


import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "short-url")
public class ShortUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String longUrl;

    @Column(unique = true, nullable = false)
    private String shortId;

    private LocalDateTime createdAt;
    private LocalDateTime expiryTime;

}
