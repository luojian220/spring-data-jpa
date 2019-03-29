package com.luno.softone.springdatajpa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: User
 * @Reason:
 * @date: 2019年03月28日 14:18
 * @since JDK 1.7
 */
@Entity
@Table(name = "user_Account")
public class UserAccount {

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

    @ManyToOne(fetch = FetchType.LAZY)
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