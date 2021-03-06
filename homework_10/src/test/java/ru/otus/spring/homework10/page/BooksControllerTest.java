package ru.otus.spring.homework10.page;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.homework10.services.BooksServiceImpl;
import ru.otus.spring.homework10.services.CommentsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Тест контроллера BooksController ")
@WebMvcTest
public class BooksControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    BooksController controller;

    @MockBean
    BooksServiceImpl service;

    @MockBean
    CommentsService serviceComment;

    @DisplayName("Контекст успешно создаёт контроллер BooksController")
    @Test
    public void shouldCorrectCreateBooksController() {
        assertThat(controller).isNotNull();
    }

    @DisplayName("Возвращает форму для списка книг. GET")
    @Test
    public void shouldReturnFormListBooks() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("Возвращает форму для информации по книге. GET")
    @Test
    public void shouldReturnFormBookInfo() throws Exception {
        mvc.perform(get("/bookInfo/1")).andExpect(status().isOk());
    }

    @DisplayName("Возвращает форму добавление книги. GET")
    @Test
    public void shouldReturnFormAddBook() throws Exception {
        mvc.perform(get("/addNewBook")).andExpect(status().isOk());
    }
}
