package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.BookEntity;

import java.util.List;

public interface IBookRepository {

    // DAO = Data Access Object avec methodes CRUD
// et throws RuntimeException implicite

    public interface DaoBookEntity {
        public void createBookEntity(BookEntity p);

        public void updateBookEntity(BookEntity p);

        public void deleteBookEntity(Long numeroBookEntity);

        public BookEntity BookEntityByNum(Long numBookEntity);

        public List<BookEntity> allBookEntitys();
    }
}
