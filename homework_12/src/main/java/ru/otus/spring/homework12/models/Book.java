package ru.otus.spring.homework12.models;

import javax.persistence.*;
import java.util.List;


@Entity
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "book-comments-entity-graph",
                attributeNodes = {@NamedAttributeNode("comments"),
                        @NamedAttributeNode("author"),
                        @NamedAttributeNode("genre")}
        )
})
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_author", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id_genre", referencedColumnName = "id")
    private Genre genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_books", referencedColumnName = "id")
    private List<Comment> comments;

    public Book() {

    }

    public Book(long id, String name, Author author, Genre genre, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = commentList;
    }

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(long id, String name, int status, Author author, Genre genre, List<Comment> commentList) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.author = author;
        this.genre = genre;
        this.comments = commentList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public Genre getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Comment> getComments() {
        return comments;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String displayFieldsBook = "Книга " + id + '\n' + "название:" + name + '\n' + "статус:" + status + '\n' + "автор:" +
                author.getName() + '\n' + "жанр:" + genre.getName() + '\n';
        return displayFieldsBook;
    }
}
