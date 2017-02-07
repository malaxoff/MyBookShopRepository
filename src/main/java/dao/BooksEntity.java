package dao;

import javax.persistence.*;
import java.util.Collection;

// Карточка книги
@javax.persistence.Table(name = "BOOKS", schema = "SYSTEM", catalog = "")
@Entity
public class BooksEntity {
    private int bookId;
    private String autors;
    private String bookTitle;
    private int idPublisher;
    private String bookDescription;
    private int bookPrice;
    private Integer bookAmount;
    private PublisherEntity publisherByIdPublisher;
    private Collection<ContentOfOrderEntity> contentOfOrdersByBookId;

    @javax.persistence.Column(name = "BOOK_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name="seq2",sequenceName="seq_book", allocationSize=1)   // автоматическая генерация id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq2")    // автоматическая генерация id
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @javax.persistence.Column(name = "AUTORS", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getAutors() {
        return autors;
    }

    public void setAutors(String autors) {
        this.autors = autors;
    }

    @javax.persistence.Column(name = "BOOK_TITLE", nullable = false, insertable = true, updatable = true, length = 300, precision = 0)
    @Basic
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @javax.persistence.Column(name = "ID_PUBLISHER", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    @javax.persistence.Column(name = "BOOK_DESCRIPTION", nullable = true, insertable = true, updatable = true, length = 1000, precision = 0)
    @Basic
    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    @javax.persistence.Column(name = "BOOK_PRICE", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(int bookPrice) {
        this.bookPrice = bookPrice;
    }

    @javax.persistence.Column(name = "BOOK_AMOUNT", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public Integer getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(Integer bookAmount) {
        this.bookAmount = bookAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksEntity that = (BooksEntity) o;

        if (bookId != that.bookId) return false;
        if (bookPrice != that.bookPrice) return false;
        if (idPublisher != that.idPublisher) return false;
        if (autors != null ? !autors.equals(that.autors) : that.autors != null) return false;
        if (bookAmount != null ? !bookAmount.equals(that.bookAmount) : that.bookAmount != null) return false;
        if (bookDescription != null ? !bookDescription.equals(that.bookDescription) : that.bookDescription != null)
            return false;
        if (bookTitle != null ? !bookTitle.equals(that.bookTitle) : that.bookTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (autors != null ? autors.hashCode() : 0);
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + idPublisher;
        result = 31 * result + (bookDescription != null ? bookDescription.hashCode() : 0);
        result = 31 * result + bookPrice;
        result = 31 * result + (bookAmount != null ? bookAmount.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "ID_PUBLISHER", referencedColumnName = "ID_PUBLISHER", nullable = false)
    public PublisherEntity getPublisherByIdPublisher() {
        return publisherByIdPublisher;
    }

    public void setPublisherByIdPublisher(PublisherEntity publisherByIdPublisher) {
        this.publisherByIdPublisher = publisherByIdPublisher;
    }

    @OneToMany(mappedBy = "booksByBookId")
    public Collection<ContentOfOrderEntity> getContentOfOrdersByBookId() {
        return contentOfOrdersByBookId;
    }

    public void setContentOfOrdersByBookId(Collection<ContentOfOrderEntity> contentOfOrdersByBookId) {
        this.contentOfOrdersByBookId = contentOfOrdersByBookId;
    }
}
