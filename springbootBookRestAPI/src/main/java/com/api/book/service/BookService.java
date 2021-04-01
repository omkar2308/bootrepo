package com.api.book.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.api.book.dao.BookRepository;
import com.api.book.entities.Book;

//@Service
//public class BookService {
//
//	private static List<Book> list=new ArrayList<>();
//	
//	static {
//		list.add(new Book(121, "java","abc"));
//		list.add(new Book(122, "java spring","lmn"));
//		list.add(new Book(123, "java spring boot","opq"));
//	}
//	
//	//get all book 
//	public List<Book> getAllBook(){
//		return list;
//		
//	}
//	//get single book by id
//	public Book getBookById(int id) {
//		Book book=null;
//		try {
//		book=list.stream().filter(e->e.getId()==id).findFirst().get();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return book;
//	}
//	//adding book 
//	public Book addBook(Book b) {
//		list.add(b);
//		return b;
//	}
//	//deleting book
//	public void deleteBook(int bid) {
//		 list = list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
//		
//	}
//	//update book
//	public void updateBook(Book book, int bookid) {
//		list.stream().map(b->{
//			if(b.getId()==bookid) 
//			{
//				b.setName(book.getName());
//				b.setAuthor(book.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
//	}
	
@Service
public class BookService {
  
	@Autowired
	private BookRepository bookRepository;	
	
	//get all book 
	public List<Book> getAllBook(){
		List<Book> list = (List)this.bookRepository.findAll(); 
		return list ;
		
	}
	//get single book by id
	public Book getBookById(int id) {
		Book book=null;
		try { 
		 book = this.bookRepository.findById(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	//adding book 
	public Book addBook(Book b) {
		Book save = this.bookRepository.save(b);
		return save;
	}
	//deleting book
	public void deleteBook(int bid) {
		this.bookRepository.deleteById(bid);
	}
	//update book
	public void updateBook(Book book, int bookid) { 
		book.setId(bookid); //just to confirm correct id passed or not  
		this.bookRepository.save(book);
	}
}

	

