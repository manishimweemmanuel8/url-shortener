package ed.abat.url_shortener.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import ed.abat.url_shortener.entity.ShortUrl;

import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShortUrlRepositoryTest {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Test
    @DisplayName("Test 1:Save Short url Test")
    @Order(1)
    @Rollback(value = false)
    public void saveShortUrlTest() {

        // Action
        ShortUrl shortUrl = ShortUrl.builder()
                .longUrl("https://www.abat.de/")
                .shortId("s7v1")
                .build();

        shortUrlRepository.save(shortUrl);

        // Verify
        Assertions.assertThat(shortUrl.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getShortUrlTest() {

        // Action
        ShortUrl shortUrl = shortUrlRepository.findByShortId("s7v1").get();
        // Verify
        Assertions.assertThat(shortUrl.getShortId()).isEqualTo("s7v1");
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void deleteShortUrlTest() {
        // Test delete action
        shortUrlRepository.deleteByShortId("s7v1");
        Optional<ShortUrl> shortUrlOptional = shortUrlRepository.findByShortId("s7v1");

        // velify deleted shorturl Verify
        Assertions.assertThat(shortUrlOptional).isEmpty();
    }

}

