package com.example.springintro;

import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.BookSummaryImpl;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BookRepository bookRepository) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //seedData();

        Scanner scanner = new Scanner(System.in);

//        List<BookSummaryImpl> allByCopiesGreaterThan = this.bookRepository.findAllByCopiesGreaterThan(100);
//        System.out.println(allByCopiesGreaterThan);


//        13 - Remove from the database those books, which copies are lower than a given number.
//        Print the number of books that were deleted from the database
//        int amount = Integer.parseInt(scanner.nextLine());
//        int deleteCount = this.bookService.deleteWithCopiesLessThan(amount);
//        System.out.println(deleteCount + " books were deleted.");

//        12 - Increase the copies of all books released after a given date with a given number. Print the
//        total amount of book copies that were added.
//        String date = scanner.nextLine();
//        int amount = Integer.parseInt(scanner.nextLine());
//        int booksUpdated = this.bookService.addCopiesToBooksAfter(date, amount);
//        System.out.printf("%d books are released after %s, " +
//                "so total of %d book copies were added",
//                booksUpdated, date, amount * booksUpdated);

//        11 - Print information (title, edition type, age restriction and price) for a book by given title.
//        String title = scanner.nextLine();
//        BookSummary summary = this.bookService.getInformationForTitle(title);
//        System.out.println(summary.getTitle() + " " + summary.getEditionType() +
//                " " + summary.getAgeRestriction() + " " + summary.getPrice());

//        10 - Print the total number of book copies by author. Order the results descending by total book copies.
//        this.authorService.getWithTotalCopies()
//            .forEach(a -> System.out.println(
//                    a.getFirstName() + " " + a.getLastName() +
//                    " - " + a.getTotalCopies()));

//        09 - Print the number of books, whose title is longer than a given number.
//        int length = Integer.parseInt(scanner.nextLine());
//        int totalBooks = this.bookService.countBooksWithTitleLongerThan(length);
//        System.out.printf("There are %d books with longer title than %d symbols%n",
//                totalBooks, length);

//        08 - Print the titles of books, which are written by authors, whose last name starts with a given string.
//        String search = scanner.nextLine();
//        this.bookService.findByAuthorLastNameStartsWith(search)
//            .forEach(b -> System.out.printf("%s (%s %s)%n",
//                b.getTitle(), b.getAuthor().getFirstName(),
//                b.getAuthor().getLastName()));

//        07 - Print the titles of books, which contain a given string (regardless of the casing).
//        String search = scanner.nextLine();
//        this.bookService.findAllTitlesContaining(search)
//                .forEach(System.out::println);

//        06 - Print the names of those authors, whose first name ends with a given string.
//        String endsWith = scanner.nextLine();
//        this.authorService.findByFirstNameEndingWith(endsWith)
//            .stream()
//            .map(a -> a.getFirstName() + " " + a.getLastName())
//            .forEach(System.out::println);


//        05 - Print the title, the edition type and the price of books, which are released before a given date.
//        The date will be in the format dd-MM-yyyy.
//        String date = scanner.nextLine();
//        this.bookService.findBooksReleasedBefore(date)
//            .forEach(b -> System.out.printf("%s %s %.2f%n",
//                b.getTitle(), b.getEditionType(), b.getPrice()));

//        04 - Print the titles of all books that are NOT released in a given year.
//        int releaseYear = Integer.parseInt(scanner.nextLine());
//        this.bookService.findNotReleasedIn(releaseYear)
//                .forEach(b -> System.out.println(b.getTitle()));

//        03 - Print the titles and prices of books with price lower than 5 and higher than 40.
//        this.bookService.findAllWithPriceNotBetween(5, 40)
//            .forEach(b -> System.out.println(b.getTitle() + " - " + b.getPrice()));

//        02 - Print the titles of the golden edition books, which have less than 5000 copies
//        this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
//                .forEach(System.out::println);


//        01 - Print the titles of all books, for which the age restriction matches the given input (minor, teen or adult). Ignore casing of the input.
//        String restriction = scanner.nextLine();
//        this.bookService.findAllTitlesByAgeRestriction(restriction)
//            .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
