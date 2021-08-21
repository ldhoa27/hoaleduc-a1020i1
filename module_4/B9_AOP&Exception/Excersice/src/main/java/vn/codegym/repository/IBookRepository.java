package vn.codegym.repository;

import vn.codegym.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
}
