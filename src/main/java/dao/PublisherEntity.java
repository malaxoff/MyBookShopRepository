package dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import javax.persistence.GenerationType;  // для      @GeneratedValue(strategy = GenerationType.AUTO)
import javax.persistence.GeneratedValue;  // для      @GeneratedValue(strategy = GenerationType.AUTO)

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 02.02.17
 * Time: 17:47
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "PUBLISHER", schema = "SYSTEM", catalog = "")
@Entity
public class PublisherEntity {
    private int idPublisher;

    @javax.persistence.Column(name = "ID_PUBLISHER", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)    // автоматическая генерация id
    public int getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(int idPublisher) {
        this.idPublisher = idPublisher;
    }

    private String nameOfPublisher;

    @javax.persistence.Column(name = "NAME_OF_PUBLISHER", nullable = true, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getNameOfPublisher() {
        return nameOfPublisher;
    }

    public void setNameOfPublisher(String nameOfPublisher) {
        this.nameOfPublisher = nameOfPublisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublisherEntity that = (PublisherEntity) o;

        if (idPublisher != that.idPublisher) return false;
        if (nameOfPublisher != null ? !nameOfPublisher.equals(that.nameOfPublisher) : that.nameOfPublisher != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPublisher;
        result = 31 * result + (nameOfPublisher != null ? nameOfPublisher.hashCode() : 0);
        return result;
    }

    private Collection<BooksEntity> booksesByIdPublisher;

    @OneToMany(mappedBy = "publisherByIdPublisher")
    public Collection<BooksEntity> getBooksesByIdPublisher() {
        return booksesByIdPublisher;
    }

    public void setBooksesByIdPublisher(Collection<BooksEntity> booksesByIdPublisher) {
        this.booksesByIdPublisher = booksesByIdPublisher;
    }
}
