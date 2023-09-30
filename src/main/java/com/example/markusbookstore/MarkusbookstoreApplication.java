package com.example.markusbookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.markusbookstore.domain.Book;
import com.example.markusbookstore.domain.BookRepository;
import com.example.markusbookstore.domain.Category;
import com.example.markusbookstore.domain.CategoryRepository;
import com.example.markusbookstore.domain.AppUser;
import com.example.markusbookstore.domain.AppUserRepository;

@SpringBootApplication
public class MarkusbookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(MarkusbookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MarkusbookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Tietokirjallisuus"));
			crepository.save(new Category("Kaunokirjallisuus"));
			
			repository.save(new Book("Musta kyynel", "Vilma Västerås", 2011, 112243, 3, crepository.findByName("Kaunokirjallisuus").get(0)));
			repository.save(new Book("Nausicaä", "Jelena Pilvilinna", 1966, 322211, 6, crepository.findByName("Tietokirjallisuus").get(0)));	
			
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@bookstore.fi", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "user@bookstore.fi", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
