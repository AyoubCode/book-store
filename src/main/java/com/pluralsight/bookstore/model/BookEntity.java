package com.pluralsight.bookstore.model;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "BOOK", schema = "PUBLIC")
public class BookEntity {
    private Long id;
    private String description;
    private String imageUrl;
    private String isbn;
    private Language language;
    private Integer nbOfPages;
    private Date publicationDate;
    private String title;
    private Float unitCost;

    public BookEntity(){

    }

    public BookEntity( String description, String imageUrl, String isbn, Language language, Integer nbOfPages, Date publicationDate, String title, Float unitCost) {

        this.description = description;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
        this.language = language;
        this.nbOfPages = nbOfPages;
        this.publicationDate = publicationDate;
        this.title = title;
        this.unitCost = unitCost;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Size(min =1, max =10000)
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "IMAGE_URL")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Basic
    @NotNull
    @Size(min = 3, max= 19)
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "LANGUAGE")
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Basic
    @Column(name = "NB_OF_PAGES")
    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "PUBLICATION_DATE")
    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Basic
    @NotNull
    @Size(min =1, max =200)
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Min(1)
    @Column(name = "UNIT_COST")
    public Float getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(Float unitCost) {
        this.unitCost = unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(isbn, that.isbn) &&
                Objects.equals(language, that.language) &&
                Objects.equals(nbOfPages, that.nbOfPages) &&
                Objects.equals(publicationDate, that.publicationDate) &&
                Objects.equals(title, that.title) &&
                Objects.equals(unitCost, that.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, imageUrl, isbn, language, nbOfPages, publicationDate, title, unitCost);
    }
}
