package com.example.markusbookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.markusbookstore.domain.AppUser;
import com.example.markusbookstore.domain.AppUserRepository;
import com.example.markusbookstore.domain.Book;
import com.example.markusbookstore.domain.BookRepository;
import com.example.markusbookstore.domain.Category;
import com.example.markusbookstore.domain.CategoryRepository;

//@DataJpaTest

//@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MarkusbookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	@Autowired
	private AppUserRepository urepository;

	@Test
	public void findByTitleShouldReturnAuthor() {
		List<Book> books = repository.findByTitle("Nausicaä");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Jelena Pilvilinna");
	}

	@Test
	public void createNewBook() {
		Category category = new Category("testi");
		crepository.save(category);
		Book book = new Book("Mickey", "Mouse", 1, 2, 3, category);
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("Nausicaä");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("Nausicaä");
		assertThat(newBooks).hasSize(0);
	}

	@Test
	public void createNewAppUser() {
		AppUser kayttaja = new AppUser("a", "b", "c", "d");
		urepository.save(kayttaja);
		AppUser kayttajaRepossa = urepository.findByUsername("a");
		assertThat(kayttajaRepossa.getRole()).isEqualTo("d");
	}

}
