package com.exercise.BookAuditingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.BookAuditingApp.model.Book;
public interface BookRepository extends JpaRepository<Book, Long> {}
