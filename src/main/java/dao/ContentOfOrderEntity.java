package dao;

import javax.persistence.*;

// Состав заказа

@javax.persistence.Table(name = "CONTENT_OF_ORDER", schema = "SYSTEM", catalog = "")
@Entity
public class ContentOfOrderEntity {
    private int id;

    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Id
    @SequenceGenerator(name="seq3",sequenceName="seq_cont", allocationSize=1)   // автоматическая генерация id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq3")    // автоматическая генерация id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int orderId;

    @javax.persistence.Column(name = "ORDER_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private int bookId;

    @javax.persistence.Column(name = "BOOK_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    private int amountOfEachBook;

    @javax.persistence.Column(name = "AMOUNT_OF_EACH_BOOK", nullable = false, insertable = true, updatable = true, length = 3, precision = 0)
    @Basic
    public int getAmountOfEachBook() {
        return amountOfEachBook;
    }

    public void setAmountOfEachBook(int amountOfEachBook) {
        this.amountOfEachBook = amountOfEachBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentOfOrderEntity that = (ContentOfOrderEntity) o;

        if (amountOfEachBook != that.amountOfEachBook) return false;
        if (bookId != that.bookId) return false;
        if (id != that.id) return false;
        if (orderId != that.orderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId;
        result = 31 * result + bookId;
        result = 31 * result + amountOfEachBook;
        return result;
    }

    private BooksEntity booksByBookId;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", nullable = false)
    public BooksEntity getBooksByBookId() {
        return booksByBookId;
    }

    public void setBooksByBookId(BooksEntity booksByBookId) {
        this.booksByBookId = booksByBookId;
    }


    private OrdersEntity ordersByOrderId;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID", nullable = false)
    public OrdersEntity getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(OrdersEntity ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }
}
