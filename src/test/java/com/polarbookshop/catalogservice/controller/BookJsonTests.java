package com.polarbookshop.catalogservice.controller;


import com.polarbookshop.catalogservice.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.ObjectContent;

@JsonTest
public class BookJsonTests {

    @Autowired
    JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        Book book =  Book.of("1234567890", "Title", "Author", "manning",9.90);

        JsonContent<Book> jsonContent = json.write(book);

        Assertions.assertThat(jsonContent).extractingJsonPathValue("@.isbn").isEqualTo("1234567890");
        Assertions.assertThat(jsonContent).extractingJsonPathValue("@.title").isEqualTo("Title");
        Assertions.assertThat(jsonContent).extractingJsonPathValue("@.author").isEqualTo("Author");
        Assertions.assertThat(jsonContent).extractingJsonPathValue("@.price").isEqualTo(9.90);
    }

    @Test
    void testDeserialize() throws Exception {
        String content = "{\"isbn\":\"1234567890\",\"title\":\"Title\",\"author\":\"Author\",\"price\":9.90}";
        ObjectContent<Book> book = json.parse(content);

        Assertions.assertThat(book.getObject().isbn()).isEqualTo("1234567890");
        Assertions.assertThat(book.getObject().title()).isEqualTo("Title");
        Assertions.assertThat(book.getObject().author()).isEqualTo("Author");
        Assertions.assertThat(book.getObject().price()).isEqualTo(9.90);


    }
}
