package com.polarbookshop.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;
import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {
@Autowired
private WebTestClient webTestClient;
    @Test
    void whenPostRequestThenBookCreated() {
        var expectedBook =  Book.of("1234567895", "Title", "Author", "manning",9.90);
            webTestClient.post().uri("/books")
                    .bodyValue(expectedBook)
                    .exchange()
                    .expectStatus().isCreated()
                    .expectBody(Book.class)
                    .value(book->{
                        assertThat(book.isbn()).isEqualTo(expectedBook.isbn());
                        assertThat(book.title()).isEqualTo(expectedBook.title());
                        assertThat(book.author()).isEqualTo(expectedBook.author());
                        assertThat(book.price()).isEqualTo(expectedBook.price());
                    })
                  ;


    }

}
