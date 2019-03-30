package com.luno.softone.springdatajpa.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: User
 * @Reason:
 * @date: 2019年03月28日 14:18
 * @since JDK 1.7
 */
@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

    /**
     * ID
     */
    private Long id;
    /**
     * 账号id
     */
    private String accountCode;
    /**
     * 余额
     */
    private BigDecimal amount;
    /**
     * 创建时间
     */
    private Date createTime;

    private Long version;


    private UserInfo userInfo;

    private String createUser;

    private List<UserAccountLog> userAccountLogList;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Column(updatable = true,columnDefinition = "datetime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "userAccount",orphanRemoval = true)
    @OrderBy("id")
    public List<UserAccountLog> getUserAccountLogList() {
        return userAccountLogList;
    }

    public void setUserAccountLogList(List<UserAccountLog> userAccountLogList) {
        this.userAccountLogList = userAccountLogList;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", accountCode='" + accountCode + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", version=" + version +
                '}';
    }
}