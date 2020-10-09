package com.pluralsight.bookstore.repository;

import com.pluralsight.bookstore.model.BookEntity;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.services.IsbnGenerator;
import com.pluralsight.bookstore.services.NumberGenerator;
import com.pluralsight.bookstore.services.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Date;


@RunWith(Arquillian.class)
public class BookRepositoryTest {




    @Inject
    private BookRepository bookRepository;
    /*
    @Test
    public void create() throws Exception {
        //Test count all and find all

        assertEquals(0, bookRepository.findAll().size());
        assertEquals(Long.valueOf(0), bookRepository.countAll());

        //create a book
        BookEntity book = new BookEntity(" Descrip ", "http:blablabla", "isbnn", Language.ENGLISH, 387, new Date(), "title", 12F);
        bookRepository.create(book);
        Long bookID = book.getId();

        assertNotNull(bookID);
        assertTrue(bookRepository.find(1L).getIsbn().equals("isbnn"));

        BookEntity bookFound = bookRepository.find(bookID);


        assertEquals(bookFound.getTitle(), "title");
        assertEquals(Long.valueOf(1), bookRepository.countAll());
        assertEquals(1, bookRepository.findAll().size());


        //Delete the book from DB
        bookRepository.delete(bookID);


        assertEquals(0, bookRepository.findAll().size());
        assertEquals(Long.valueOf(0), bookRepository.countAll());
    }
*/
    @Test(expected = Exception.class)
    public void createInvalidBook(){
        BookEntity book = new BookEntity(" Descrip ", "http:blablabla", null, Language.ENGLISH, 387, new Date(), "title", 12F);
        bookRepository.create(book);

    }
    @Test(expected =Exception.class)
    public void findWithNullID(){
        bookRepository.find(null);
    }

    @Test
    public void avoidTitleSpaces(){
        BookEntity book = new BookEntity(" Descrip ", "http:blablabla", "isbnnn", Language.ENGLISH, 387, new Date(), "this is a      title", 12F);
        bookRepository.create(book);

        String savedTitle = bookRepository.find(book.getId()).getTitle();
        assertEquals(savedTitle, "this is a title");

    }



    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(IBookRepository.class)
                .addClass(BookEntity.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(IsbnGenerator.class)
                .addClass(NumberGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}
