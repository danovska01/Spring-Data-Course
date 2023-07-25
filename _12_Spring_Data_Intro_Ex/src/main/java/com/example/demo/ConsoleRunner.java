package com.example.demo;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(
            SeedService seedService,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void _01_booksAfter2000() {
        //Get all books after the year 2000. Print only their titles.

        LocalDate year2000 = LocalDate.of(2000, 12, 31);

        List<Book> books = this.bookRepository.findByReleaseDateAfter(year2000);

        books.forEach(b -> System.out.println(b.getTitle()));

        int count = this.bookRepository.countByReleaseDateAfter(year2000);

        System.out.println("Total count: " + count);
    }

    private void _02_allAuthorsWithBookBefore1990() {
        // Get all authors with at least one book with release date before 1990. Print their first name and last name.
        LocalDate year1990 = LocalDate.of(1990, 1, 1);

        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);

        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _03_allAuthorsOrderedByBookCount() {
        //Get all authors, ordered by the number of their books (descending).
        // Print their first name, last name and book count.
        List<Author> authors = this.authorRepository.findAll();


        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(author ->
                        System.out.printf("%s %s -> %d%n",
                                author.getFirstName(),
                                author.getLastName(),
                                author.getBooks().size()));
    }

    private void _04_bookSorting() {
        //Get all books from author George Powell, ordered by their release date (descending),
        // then by book title (ascending).
        // Print the book's title, release date and copies.

        List<Book> books = this.bookRepository.findAll();
        String authorName = "George Powell";

        books.stream()
                .filter(book -> {
                    String fullName = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
                    return fullName.equals(authorName);
                })
                .sorted(Comparator
                        .comparing(Book::getReleaseDate).reversed()
                        .thenComparing(Book::getTitle))
                .forEach(book -> {
                    System.out.println("Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Release Date: " + book.getReleaseDate());
                    System.out.println("Copies: " + book.getCopies());
                    System.out.println();
                });

    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //  this.seedService.seedAuthors();
        // this.seedService.seedCategories();
        //    this.seedService.seedAll();

        //    this._01_booksAfter2000();
//        this._02_allAuthorsWithBookBefore1990();
        // this._03_allAuthorsOrderedByBookCount();
        this._04_bookSorting();
    }


}
