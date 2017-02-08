package dao;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collection;

// Карточка пользователя

@javax.persistence.Table(name = "USERS", schema = "SYSTEM", catalog = "")
@Entity
public class UsersEntity {
    private int userId;

    @javax.persistence.Column(name = "USER_ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name="seq6",sequenceName="seq_user", allocationSize=1)   // автоматическая генерация id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq6")    // автоматическая генерация id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String userFio;

    @javax.persistence.Column(name = "USER_FIO", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getUserFio() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    private String userAdress;

    @javax.persistence.Column(name = "USER_ADRESS", nullable = false, insertable = true, updatable = true, length = 200, precision = 0)
    @Basic
    public String getUserAdress() {
        return userAdress;
    }

    public void setUserAdress(String userAdress) {
        this.userAdress = userAdress;
    }

    private int userPhone;      // маловат тип для номера телефона нужно исправить

    @javax.persistence.Column(name = "USER_PHONE", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Basic
    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    private String userLogin;

    @javax.persistence.Column(name = "USER_LOGIN", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    private String userPassword;

    @javax.persistence.Column(name = "USER_PASSWORD", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private String userMail;

    @javax.persistence.Column(name = "USER_MAIL", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    @Basic
    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    private Integer userStatusId;

    @javax.persistence.Column(name = "USER_STATUS_ID", nullable = false, insertable = false, updatable = false, length = 1, precision = 0)
    @Basic
    public Integer getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(Integer userStatusId) {
        this.userStatusId = userStatusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (userPhone != that.userPhone) return false;
        if (userAdress != null ? !userAdress.equals(that.userAdress) : that.userAdress != null) return false;
        if (userFio != null ? !userFio.equals(that.userFio) : that.userFio != null) return false;
        if (userLogin != null ? !userLogin.equals(that.userLogin) : that.userLogin != null) return false;
        if (userMail != null ? !userMail.equals(that.userMail) : that.userMail != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userStatusId != null ? !userStatusId.equals(that.userStatusId) : that.userStatusId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userFio != null ? userFio.hashCode() : 0);
        result = 31 * result + (userAdress != null ? userAdress.hashCode() : 0);
        result = 31 * result + userPhone;
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userMail != null ? userMail.hashCode() : 0);
        result = 31 * result + (userStatusId != null ? userStatusId.hashCode() : 0);
        return result;
    }

    private Collection<OrdersEntity> ordersesByUserId;

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<OrdersEntity> getOrdersesByUserId() {
        return ordersesByUserId;
    }

    public void setOrdersesByUserId(Collection<OrdersEntity> ordersesByUserId) {
        this.ordersesByUserId = ordersesByUserId;
    }

    private UserStatusesEntity userStatusesByUserStatusId;

    @ManyToOne
    @JoinColumn(name = "USER_STATUS_ID", referencedColumnName = "USER_STATUS_ID", nullable = false)
    public UserStatusesEntity getUserStatusesByUserStatusId() {
        return userStatusesByUserStatusId;
    }

    public void setUserStatusesByUserStatusId(UserStatusesEntity userStatusesByUserStatusId) {
        this.userStatusesByUserStatusId = userStatusesByUserStatusId;
    }
}
