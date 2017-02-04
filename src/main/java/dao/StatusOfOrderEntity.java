package dao;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigInteger;
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
@javax.persistence.Table(name = "STATUS_OF_ORDER", schema = "SYSTEM", catalog = "")
@Entity
public class StatusOfOrderEntity {
    private BigInteger idStatus;

    @javax.persistence.Column(name = "ID_STATUS", nullable = false, insertable = true, updatable = true, length = 1, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    // автоматическая генерация id
    public BigInteger getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(BigInteger idStatus) {
        this.idStatus = idStatus;
    }

    private String nameOfStatus;

    @javax.persistence.Column(name = "NAME_OF_STATUS", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getNameOfStatus() {
        return nameOfStatus;
    }

    public void setNameOfStatus(String nameOfStatus) {
        this.nameOfStatus = nameOfStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusOfOrderEntity that = (StatusOfOrderEntity) o;

        if (idStatus != null ? !idStatus.equals(that.idStatus) : that.idStatus != null) return false;
        if (nameOfStatus != null ? !nameOfStatus.equals(that.nameOfStatus) : that.nameOfStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStatus != null ? idStatus.hashCode() : 0;
        result = 31 * result + (nameOfStatus != null ? nameOfStatus.hashCode() : 0);
        return result;
    }

    private Collection<OrdersEntity> ordersesByIdStatus;

    @OneToMany(mappedBy = "statusOfOrderByIdStatus")
    public Collection<OrdersEntity> getOrdersesByIdStatus() {
        return ordersesByIdStatus;
    }

    public void setOrdersesByIdStatus(Collection<OrdersEntity> ordersesByIdStatus) {
        this.ordersesByIdStatus = ordersesByIdStatus;
    }
}
