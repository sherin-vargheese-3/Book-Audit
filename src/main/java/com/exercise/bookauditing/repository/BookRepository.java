package com.exercise.bookauditing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.bookauditing.model.Book;
public interface BookRepository extends JpaRepository<Book, Long> {}
