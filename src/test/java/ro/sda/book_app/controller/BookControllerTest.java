package ro.sda.book_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.w3c.dom.stylesheets.LinkStyle;
import org.xmlunit.util.Mapper;
import ro.sda.book_app.model.Book;
import ro.sda.book_app.model.ClientError;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    static ObjectMapper mapper;

    @BeforeAll
    static void inInt() {
        mapper = new ObjectMapper();
    }

    @Test
    public void createBook_WithSuccess() throws Exception {
        Book book = Book.builder()
                .author("author")
                .title("title")
                .year(2000)
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/book")
                        .contentType("application/json")
                        .content(mapper.writeValueAsBytes(book))
        ).andExpect(status().isCreated());
    }

    @Test
    public void createBook_WithValidationErrors() throws Exception {
        Book book = Book.builder()
                .author("")
                .title("")
                .year(2000)
                .build();

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/book")
                                .contentType("application/json")
                                .content(mapper.writeValueAsBytes(book)))
                .andExpect(status().isBadRequest())
                .andReturn();

        ClientError response = mapper.readValue(result.getResponse().getContentAsString(), ClientError.class);

        List<String> expectedErrMessages = List.of(
                "Title should not be empty!",
                "Author should not be empty!"
        );

        assertEquals(400, response.getStatusCode());
        assertEquals("Constraint violation!", response.getMessage());
        assertThat(response.getErrors())
                .hasSize(2)
                .containsExactlyInAnyOrderElementsOf(expectedErrMessages);
    }
}
