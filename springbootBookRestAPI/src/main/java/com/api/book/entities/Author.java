package com.api.book.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Author {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;
	
	@Column(name = "first_name")
	private String firstName;
	private String lastName;
	
	@OneToOne(mappedBy ="author")
	@JsonBackReference   //bidirectional  only we ll get parent data to avoid nested calling 
	private Book book ;
	
	public int getAuthorId() {
		return authorId; 
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId; 
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {  
		this.lastName = lastName; 
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", firstName=" + firstName + ", lastNmare=" + lastName + "]";
	}
	
}
