package ru.otus.spring.homework_7.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_7.models.Book;
import ru.otus.spring.homework_7.models.Comment;
import ru.otus.spring.homework_7.services.BooksService;
import ru.otus.spring.homework_7.services.CommentsService;

import java.util.List;
import java.util.Optional;


@ShellComponent
public class LibraryBooksShellCommands {

    BooksService booksService;
    CommentsService commentsService;

    LibraryBooksShellCommands(BooksService booksService, CommentsService commentsService) {
        this.booksService = booksService;
        this.commentsService = commentsService;
    }

    @ShellMethod(value = "Select all books", key = {"sb", "selectBooks"})
    public String selectAllBooks() {
        List<Book> bookList = booksService.getAllBooks();
        System.out.println(bookList);
        return "Показали весь список книг из библиотеки";
    }

    @ShellMethod(value = "Insert new book. Example ib 'new book' 'Lermontov' 'prose'", key = {"ib", "insertBook"})
    public String addNewBook(String nameBook, String nameAuthor, String nameGenre) {
        booksService.addNewBook(nameBook, nameAuthor, nameGenre);
        return "Добавлена новая книга";
    }

    @ShellMethod(value = "Update book's status", key = {"ub", "updateBook"})
    public String update(long id, int status) {
        booksService.updateStatusBook(id, status);
        return "Изменили статус книги";
    }

    @ShellMethod(value = "Delete book", key = {"db", "deleteBook"})
    public String deleteBook(long id) {
        booksService.deleteBookById(id);
        return "Удалили книгу";
    }

    @ShellMethod(value = "Find book by Id. Example: fbid 1", key = {"fbid", "findBookById"})
    public String findBookById(long id) {
        Optional<Book> book = booksService.findBookById(id);
        System.out.println(book.isPresent() ? book.get() : "");
        return "Нашли книгу по id";
    }

    @ShellMethod(value = "Find book by name. Example: fbname 'Misery'", key = {"fbname", "findBookByName"})
    public String findBookByName(String name) {
        Optional<Book> book = booksService.findBookByName(name);
        System.out.println(book.isPresent() ? book.get() : "");
        return "Нашли книгу по названию";
    }

    @ShellMethod(value = "Select all comments", key = {"sc", "selectComments"})
    public String selectAllComments() {
        List<Comment> listComments = commentsService.getAllComments();
        System.out.println(listComments);
        return "Показали все-все комментарии";
    }

    @ShellMethod(value = "Find comments by Id book. Example: fcid 1", key = {"fcid", "findCommentByIdBook"})
    public String findCommentByIdBook(long id) {
        List<Comment> listComments = commentsService.findCommentByIdBook(id);
        System.out.println(listComments);
        return "Нашли все комментарии к книге по id";
    }

    @ShellMethod(value = "Find comments by name book. Example: fcname 'Misery'", key = {"fcn", "findCommentByNameBook"})
    public String findCommentByNameBook(String nameBook) {
        List<Comment> listComments = commentsService.findCommentsByNameBook(nameBook);
        System.out.println(listComments);
        return "Нашли все комментарии к книге по названию книги";
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public String deleteCommentByIdBook(long id) {
        commentsService.deleteCommentByIdBook(id);
        return "Удалили комментарий к книге по id";
    }

    @ShellMethod(value = "Update comment.Example uc 1 'super-puper book'", key = {"uc", "updateComment"})
    public String updateComment(long id, String newComment) {
        commentsService.updateCommentById(id, newComment);
        return "Изменили текст комментария";
    }

    @ShellMethod(value = "Add new comment. Example i 'new book' 'Lermontov' 'prose'", key = {"ic", "insertComment"})
    public String addNewComment(String textComments, String nameBook) {
        commentsService.addNewComment(textComments, nameBook);
        return "Добавлен новый комментарий";
    }
}
