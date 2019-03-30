package com.luno.softone.springdatajpa.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author luojian
 * @version 1.0
 * @ClassName: UserAccountLogRepository
 * @Reason:
 * @date: 2019年03月29日 23:51
 * @company:warWolf
 * @since JDK 1.8
 */
@Entity
@Table(name = "user_account_log")
public class UserAccountLog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    /**
     * 账户code
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_code" , referencedColumnName = "accountCode")
    private UserAccount userAccount;
    /**
     * 操作金额
     */
    @Column(name = "operateAmt")
    private BigDecimal operateAmount;


    private BigDecimal beforeAmount;


    private BigDecimal afterAmount;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    public BigDecimal getOperateAmount() {
        return operateAmount;
    }

    public void setOperateAmount(BigDecimal operateAmount) {
        this.operateAmount = operateAmount;
    }

    public BigDecimal getBeforeAmount() {
        return beforeAmount;
    }

    public void setBeforeAmount(BigDecimal beforeAmount) {
        this.beforeAmount = beforeAmount;
    }

    public BigDecimal getAfterAmount() {
        return afterAmount;
    }

    public void setAfterAmount(BigDecimal afterAmount) {
        this.afterAmount = afterAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserAccountLog{" +
                "id=" + id +
                ", userAccount=" + userAccount +
                ", operateAmount=" + operateAmount +
                ", beforeAmount=" + beforeAmount +
                ", afterAmount=" + afterAmount +
                ", createTime=" + createTime +
                '}';
    }
}
