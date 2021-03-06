package ru.otus.spring.homework12.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.homework12.models.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName("Класс сервиса для работы с книгами должен")
@SpringBootTest
public class BooksServiceImplTest {

    private static final long FIND_BY_ID = 1l;
    private static final long UPDATE_BY_ID = 1l;
    private static final long DELETE_BY_ID = 4l;
    private static final int BOOK_LIST_SIZE = 5;
    private static final String FIND_BY_NAME = "David Copperfield";
    private static final String INSERT_NAME_AUTHOR = "Steven King";
    private static final String INSERT_NAME_GENRE = "lyrics";
    private static final String INSERT_NAME_BOOK = "test new book";
    private static final int UPDATE_STATUS = 8;
    private static final long DELETE_SIZE = 1;
    private static final long DELETE_ONE_BY_ID = 2l;

    @Autowired
    BooksService booksService;

    @Autowired
    EntityManager entityManager;

    @DisplayName("загрузить все книги. Не Null")
    @Test
    void shouldReturnListBooksNotNull() {
        final List<Book> booksList = booksService.getAllBooks();
        assertThat(booksList).isNotNull();
    }

    @DisplayName("загрузить корректное кол-во книг")
    @Test
    void shouldCorrectNumberReturnRows() {
        final List<Book> booksList = booksService.getAllBooks();
        assertThat(booksList).hasSize(BOOK_LIST_SIZE);
    }

    @DisplayName("загружать список всех книг с полной информацией о них")
    @Test
    void shouldReturnLisCorrectValuesNotNull() {
        final List<Book> booksList = booksService.getAllBooks();
        assertThat(booksList)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);
        booksList.forEach(System.out::println);
    }

    @DisplayName("загружать книгу по её id. не null")
    @Test
    public void shouldFindByIdNotNull() {
        final Optional<Book> actualBook = booksService.findBookById(FIND_BY_ID);
        assertThat(actualBook).isPresent().get().isNotNull();
    }

    @DisplayName("загружать книгу по её id с ожидаемыми полями")
    @ParameterizedTest
    @MethodSource("generateDataValuesReturnBook")
    public void shouldFindById(long idBook, String nameBook, String nameAuthor, long idAuthor,
                               String nameGenre, long idGenre) {
        final Optional<Book> optionalBook = booksService.findBookById(idBook);
        Book book = optionalBook.get();
        assertEquals(book.getId(), idBook);
        assertEquals(book.getName(), nameBook);
        assertEquals(book.getAuthor().getName(), nameAuthor);
        assertEquals(book.getAuthor().getId(), idAuthor);
        assertEquals(book.getGenre().getName(), nameGenre);
        assertEquals(book.getGenre().getId(), idGenre);
    }

    private static Stream<Arguments> generateDataValuesReturnBook() {
        return Stream.of(
                Arguments.of(1, "Misery", "Steven King", 1, "thriller", 1),
                Arguments.of(2, "Desperation", "Steven King", 1, "novel", 3),
                Arguments.of(3, "To Chaadaev", "Alexandr Pushkin", 2, "letter", 4),
                Arguments.of(4, "I remember a wonderful moment", "Alexandr Pushkin", 2, "lyrics", 2),
                Arguments.of(5, "David Copperfield", "Charles Dickens", 3, "novel", 3)
        );
    }

    @DisplayName("загружать книгу по её названию")
    @Test
    void shouldFindExpectedBookByName() {
        Optional<Book> book = booksService.findBookByName(FIND_BY_NAME);
        assertThat(book).isPresent();
    }

    @DisplayName("добавить новую книгу не NULL")
    @Test
    public void shouldInsertNewBookNotNull() {
        booksService.addNewBook(INSERT_NAME_BOOK, INSERT_NAME_AUTHOR, INSERT_NAME_GENRE);
        Optional<Book> actualBook = booksService.findBookByName(INSERT_NAME_BOOK);
        assertThat(actualBook).isPresent();
    }

    @DisplayName("обновить статус книги")
    @Test
    public void shouldUpdateStatusDifferentBeforeAfter() {
        Book bookBefore = entityManager.find(Book.class, UPDATE_BY_ID);
        int beforeStatus = bookBefore.getStatus();
        entityManager.detach(bookBefore);
        booksService.updateStatusBook(UPDATE_BY_ID, UPDATE_STATUS);
        Book bookAfter = entityManager.find(Book.class, UPDATE_BY_ID);
        int afterStatus = bookAfter.getStatus();
        assertNotEquals(beforeStatus, afterStatus);
    }

    @DisplayName("удалить ровно 1 книгу")
    @Test
    public void shouldDeleteOnlyOneBook() {
        List<Book> bookListBefore = booksService.getAllBooks();
        int sizeBefore = bookListBefore.size();
        booksService.deleteBookById(DELETE_ONE_BY_ID);
        List<Book> bookListAfter = booksService.getAllBooks();
        int sizeAfter = bookListAfter.size();
        assertEquals(sizeBefore - sizeAfter, DELETE_SIZE);
    }

    @DisplayName("удалить книгу")
    @Test
    public void shouldDeleteBook() {
        Book book = entityManager.find(Book.class, DELETE_BY_ID);
        assertNotNull(book);
        booksService.deleteBookById(DELETE_BY_ID);
        assertNull(entityManager.find(Book.class, DELETE_BY_ID));
    }
}
