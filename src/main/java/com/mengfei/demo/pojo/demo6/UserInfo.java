package com.mengfei.demo.pojo.demo6;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;

    @Column(name = "desc_info")
    private String descInfo;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getDescInfo() {
        return descInfo;
    }

    public void setDescInfo(String descInfo) {
        this.descInfo = descInfo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "oid=" + oid +
                ", descInfo='" + descInfo + '\'' +
                '}';
    }
}
