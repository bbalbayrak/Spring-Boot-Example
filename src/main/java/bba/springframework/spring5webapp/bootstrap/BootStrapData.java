package bba.springframework.spring5webapp.bootstrap;

import bba.springframework.spring5webapp.domain.Author;
import bba.springframework.spring5webapp.domain.Book;
import bba.springframework.spring5webapp.domain.Publisher;
import bba.springframework.spring5webapp.repository.AuthorRepo;
import bba.springframework.spring5webapp.repository.BookRepo;
import bba.springframework.spring5webapp.repository.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootStrapData(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("System Works");

        Publisher publisher = new Publisher();
        publisher.setName("Burak");
        publisher.setCity("Istanbul");
        publisher.setState("TUR");

        publisherRepo.save(publisher);

        System.out.println("Publisher counts: " + publisherRepo.count());


    Author eric = new Author("Eric", "Evans");
    Book ddd = new Book("Domain Driven Design", "1");
    Set<Book> books = new HashSet<>();
    books.add(ddd);
    eric.setBooks(books);

    ddd.getAuthors().add(eric);

    ddd.setPublisher(publisher);
    publisher.getBooks().add(ddd);

    authorRepo.save(eric);
    bookRepo.save(ddd);
    publisherRepo.save(publisher);

    Author bradbury = new Author("Ray", "Bradbury");
    Book fahrenheit = new Book("Fahrenheit 451","2");
    bradbury.getBooks().add(fahrenheit);
    fahrenheit.getAuthors().add(bradbury);

    fahrenheit.setPublisher(publisher);
    publisher.getBooks().add(fahrenheit);

    authorRepo.save(bradbury);
    bookRepo.save(fahrenheit);
    publisherRepo.save(publisher);

    System.out.println("Started in Bootstrap");
    System.out.println("Number of Books: " + bookRepo.count());
        //System.out.println("Books" + bookRepo.findAll());
    }
}
