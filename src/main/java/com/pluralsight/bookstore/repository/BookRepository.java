package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.BookEntity;
import com.pluralsight.bookstore.services.IsbnGenerator;
import com.pluralsight.bookstore.services.NumberGenerator;
import com.pluralsight.bookstore.services.TextUtil;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.*;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;




@Transactional(Transactional.TxType.SUPPORTS)
public class BookRepository implements IBookRepository {

    @PersistenceContext(unitName = "bookStorePU")
    private EntityManager entityManager;

    @Inject
    NumberGenerator generator;
    @Inject
    TextUtil textUtil;


    public BookEntity find(@NotNull Long id) {

        return entityManager.find(BookEntity.class, id);
        // NB la methode .find() retourne null si rien n'est trouvé
    }

    public List<BookEntity> findAll() {
        TypedQuery<BookEntity> query = entityManager.createQuery("SELECT b FROM BookEntity b ORDER BY b.title DESC", BookEntity.class);
        return query.getResultList();
    }

    public Long countAll() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM BookEntity b", Long.class);
        return query.getSingleResult();
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public BookEntity create(@NotNull BookEntity book) {
        book.setIsbn(generator.generateNumber()+(book.getIsbn().substring(0,6)));
        book.setTitle(textUtil.sanitize(book.getTitle()));
        entityManager.persist(book); // insert into et auto_increment
        return book;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(@NotNull Long id) {

            entityManager.remove(entityManager.getReference(BookEntity.class, id)); // delete sql


    }

}
