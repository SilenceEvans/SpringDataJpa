package cn.itchenxi.entity;

import javax.persistence.*;

@Entity
public class LinkMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lkmId;
    private String lkmName;//联系人姓名
    private String lkmGender;//联系人性别
    private String lkmPhone;//联系人办公电话
    private String lkmMobile;//联系人手机
    private String lkmEmail;//联系人邮箱
    private String lkmPosition;//联系人职位
    private String lkmMemo;//联系人备注
    /**
     * 多对一关系映射
     */
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_Id",referencedColumnName = "custId")
    private Customer customer;

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }
}
