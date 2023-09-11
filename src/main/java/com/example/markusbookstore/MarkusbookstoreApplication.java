package com.example.markusbookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.markusbookstore.domain.Book;
import com.example.markusbookstore.domain.BookRepository;

@SpringBootApplication
public class MarkusbookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(MarkusbookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MarkusbookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			log.info("save a couple of books");
			repository.save(new Book("Musta kyynel", "Vilma Västerås", 2011, 112243, 3));
			repository.save(new Book("Nausicaä", "Jelena Pilvilinna", 1966, 322211, 6));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
