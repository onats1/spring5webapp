package com.example.spring5webapp.bootStrap;

import com.example.spring5webapp.domain.Author;
import com.example.spring5webapp.domain.Book;
import com.example.spring5webapp.domain.Publisher;
import com.example.spring5webapp.repositories.AuthorRepository;
import com.example.spring5webapp.repositories.BookRepository;
import com.example.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author toniKan = new Author("Toni", "Kan");
        Book theCarnivorousCity = new Book("The Carnivorous city", "1200");
        Publisher macMillian = new Publisher("Macmillian Publishing", "900", "Nigeria", "10001");
        Publisher oxford = new Publisher("Macmillian Publishing", "900", "England", "10001");

        toniKan.getBooks().add(theCarnivorousCity);
        theCarnivorousCity.getAuthors().add(toniKan);
        theCarnivorousCity.setPublisher(macMillian);

        authorRepository.save(toniKan);
        bookRepository.save(theCarnivorousCity);

        Author charles = new Author("Charles", "Dickens");
        Book oliv = new Book("Oliver Twist", "93428");
        charles.getBooks().add(oliv);
        oliv.getAuthors().add(charles);
        oliv.setPublisher(oxford);

        oxford.getBooks().add(oliv);

        authorRepository.save(charles);
        bookRepository.save(oliv);


        publisherRepository.save(macMillian);
        publisherRepository.save(oxford);

        System.out.println("Books have been loaded");
        System.out.println("Number of books printed:" + bookRepository.count());
        System.out.println("Number of publishers:" + publisherRepository.count());
    }


}
