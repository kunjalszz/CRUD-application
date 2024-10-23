package com.example.CRUDApplication.controller;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRUDApplication.model.Book;
import com.example.CRUDApplication.repo.BookRepo;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepo bookRepo;

   
     //Creating a new book
     @PostMapping
     public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookObj= bookRepo.save(book);
 
        return new ResponseEntity<>(bookObj,HttpStatus.CREATED);
 
     }

     //get all books
    @GetMapping
    public List<Book> getAllBooks()
    {
       return bookRepo.findAll();
    }

    //gwt book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByid(@PathVariable Long id){
return bookRepo.findById(id).map(book -> new ResponseEntity<>(book,HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    //update a book
    @PostMapping("/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id,@RequestBody Book newBookData ){
return bookRepo.findById(id).map(book->{book.setTitle(newBookData.getTitle());
book.setAuthor(newBookData.getAuthor());
Book UpdatedBook=bookRepo.save(book);
return new ResponseEntity<>(UpdatedBook,HttpStatus.OK);})
.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id)
    {
        bookRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    
    
}
