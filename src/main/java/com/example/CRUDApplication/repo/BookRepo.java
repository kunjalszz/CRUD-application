package com.example.CRUDApplication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CRUDApplication.model.Book;
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    
}
