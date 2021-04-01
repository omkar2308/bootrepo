package com.api.book;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.entities.Book;
import com.api.book.service.BookService;

@RestController
public class BookController {

////	@RequestMapping(value = "/books",method = RequestMethod.GET)
//	@GetMapping("/books")
//	public Book getBook() {
//		
//		Book book =new Book();
//		book.setId(123);
//		book.setName("Spring boot book");
//		book.setAuthor("Akshay");
//		
//		return book ;
//	}
	@Autowired
	private BookService bookService;

	// get all bools
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() { // res entity used for send status
		List<Book> list = bookService.getAllBook(); 
		if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // status code if no book return not found
		}
		return ResponseEntity.of(Optional.of(list));
	}

	// get single book handler
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = this.bookService.addBook(book);
			return ResponseEntity.of(Optional.of(b));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/books/{bookid}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookid") int bookid) {
		try {
			this.bookService.deleteBook(bookid);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update book handler
	@PutMapping("/books/{bookid}")
	public ResponseEntity updateBook(@RequestBody Book book, @PathVariable("bookid") int bookid) {
		try {
			this.bookService.updateBook(book, bookid);
			return  ResponseEntity.ok().body(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
