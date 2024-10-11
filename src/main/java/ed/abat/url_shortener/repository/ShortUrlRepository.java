package ed.abat.url_shortener.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ed.abat.url_shortener.entity.ShortUrl;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortId(String shortId);

    Optional<ShortUrl> deleteByShortId(String shortId);
}
