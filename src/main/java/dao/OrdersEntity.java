package dao;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;

// Заказ

@javax.persistence.Table(name = "ORDERS", schema = "SYSTEM", catalog = "")
@Entity
public class OrdersEntity {
    private int orderId;

    @javax.persistence.Column(name = "ORDER_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name="seq4",sequenceName="seq_ord", allocationSize=1)   // автоматическая генерация id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq4")    // автоматическая генерация id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    private int userId;

    @javax.persistence.Column(name = "USER_ID", nullable = false, insertable = false, updatable = false, length = 10, precision = 0)
    @Basic
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private  java.util.Date orderDate;           // автоматом тип был    Timestamp

    @javax.persistence.Column(name = "ORDER_DATE", nullable = false, insertable = true, updatable = true, length = 7, precision = 0)
    @Temporal(TemporalType.DATE)  // автоматом сгенерилось @Basic
    public  java.util.Date getOrderDate() {    // автоматом тип был    Timestamp
        return orderDate;
    }

    public void setOrderDate( java.util.Date orderDate) {       // автоматом тип был    Timestamp
        this.orderDate = orderDate;
    }

    private int orderPrice;

    @javax.persistence.Column(name = "ORDER_PRICE", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    private Integer idStatus;

    @javax.persistence.Column(name = "ID_STATUS", nullable = false, insertable = false, updatable = false, length = 1, precision = 0)
    @Basic
    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (orderId != that.orderId) return false;
        if (orderPrice != that.orderPrice) return false;
        if (userId != that.userId) return false;
        if (idStatus != null ? !idStatus.equals(that.idStatus) : that.idStatus != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + userId;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + orderPrice;
        result = 31 * result + (idStatus != null ? idStatus.hashCode() : 0);
        return result;
    }

    private Collection<ContentOfOrderEntity> contentOfOrdersByOrderId;

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<ContentOfOrderEntity> getContentOfOrdersByOrderId() {
        return contentOfOrdersByOrderId;
    }

    public void setContentOfOrdersByOrderId(Collection<ContentOfOrderEntity> contentOfOrdersByOrderId) {
        this.contentOfOrdersByOrderId = contentOfOrdersByOrderId;
    }

    private StatusOfOrderEntity statusOfOrderByIdStatus;

    @ManyToOne
    @JoinColumn(name = "ID_STATUS", referencedColumnName = "ID_STATUS", nullable = false)
    public StatusOfOrderEntity getStatusOfOrderByIdStatus() {
        return statusOfOrderByIdStatus;
    }

    public void setStatusOfOrderByIdStatus(StatusOfOrderEntity statusOfOrderByIdStatus) {
        this.statusOfOrderByIdStatus = statusOfOrderByIdStatus;
    }

    private UsersEntity usersByUserId;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
