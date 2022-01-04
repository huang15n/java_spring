package com.uwindsor.firstApp.mainCommand;

import com.uwindsor.firstApp.models.Author;
import com.uwindsor.firstApp.models.Book;
import com.uwindsor.firstApp.models.Publisher;
import com.uwindsor.firstApp.repositories.AuthorRepository;
import com.uwindsor.firstApp.repositories.BookRepository;
import com.uwindsor.firstApp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class firstApp implements CommandLineRunner {

    private final AuthorRepository ar;
    private final BookRepository br;
    private final PublisherRepository pr;

    public firstApp(AuthorRepository ar, BookRepository br, PublisherRepository pr) {
        this.ar = ar;
        this.br = br;
        this.pr = pr;
    }

    @Override
    public void run(String... args) throws Exception {

        Author oneAuthor = new Author("eddie","huang");
        Book oneBook = new Book("book one","123456");
        Publisher onePublisher = new Publisher();
        onePublisher.setCity("Ottawa");
        onePublisher.setAddressLine("100 Lauriner Avenue");
        onePublisher.setProvince("Ontario");
        onePublisher.setZipCode("K1H9K9");


        pr.save(onePublisher);

        oneAuthor.getBooks().add(oneBook);
        oneBook.getAuthors().add(oneAuthor);


        ar.save(oneAuthor);
        br.save(oneBook);
        pr.save(onePublisher);


        Author twoAuthor = new Author("jason","gao");
        Book twoBook = new Book("book two","1234567");

        twoAuthor.getBooks().add(twoBook);
        twoBook.getAuthors().add(twoAuthor);

        ar.save(twoAuthor);
        br.save(twoBook);
        pr.save(onePublisher);



    }
}
