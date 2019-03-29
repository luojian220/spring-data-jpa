package com.luno.softone.springdatajpa.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
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
@Table(name = "user_info")
public class UserInfo {

    /**
     * ID
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 创建时间
     */
    private Date createTime;

    private UserAccount userAccount;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userInfo", orphanRemoval = true)
    @OrderBy("id")
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}