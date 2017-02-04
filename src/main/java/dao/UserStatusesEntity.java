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
@javax.persistence.Table(name = "USER_STATUSES", schema = "SYSTEM", catalog = "")
@Entity
public class UserStatusesEntity {
    private BigInteger userStatusId;

    @javax.persistence.Column(name = "USER_STATUS_ID", nullable = false, insertable = false, updatable = false, length = 1, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // автоматическая генерация id
    public BigInteger getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(BigInteger userStatusId) {
        this.userStatusId = userStatusId;
    }

    private String nameOfUserStatus;

    @javax.persistence.Column(name = "NAME_OF_USER_STATUS", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
    @Basic
    public String getNameOfUserStatus() {
        return nameOfUserStatus;
    }

    public void setNameOfUserStatus(String nameOfUserStatus) {
        this.nameOfUserStatus = nameOfUserStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatusesEntity that = (UserStatusesEntity) o;

        if (nameOfUserStatus != null ? !nameOfUserStatus.equals(that.nameOfUserStatus) : that.nameOfUserStatus != null)
            return false;
        if (userStatusId != null ? !userStatusId.equals(that.userStatusId) : that.userStatusId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userStatusId != null ? userStatusId.hashCode() : 0;
        result = 31 * result + (nameOfUserStatus != null ? nameOfUserStatus.hashCode() : 0);
        return result;
    }

    private Collection<UsersEntity> usersesByUserStatusId;

    @OneToMany(mappedBy = "userStatusesByUserStatusId")
    public Collection<UsersEntity> getUsersesByUserStatusId() {
        return usersesByUserStatusId;
    }

    public void setUsersesByUserStatusId(Collection<UsersEntity> usersesByUserStatusId) {
        this.usersesByUserStatusId = usersesByUserStatusId;
    }
}
