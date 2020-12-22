package com.credit.CreditPublicity.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目: 〔大理〕征信
 * 作者: zhanglei
 * 日期: 2017/4/1
 * 备注:
 */
@Entity
@Table(name = "SGS_TJ")
public class SGSEntity implements Serializable {

    private Long id;
    private String dataTypeDbname;
    private String dataTypeName;
    private int dataCount;
    private String dataWbj;
    private String type;
    private Date updateTime;


    @Basic
    @Column(name = "DATA_TYPE_DBNAME", nullable = true, length = 256)
    public String getDataTypeDbname() {
        return dataTypeDbname;
    }

    public void setDataTypeDbname(String dataTypeDbname) {
        this.dataTypeDbname = dataTypeDbname;
    }

    @Basic
    @Column(name = "DATA_TYPE_NAME", nullable = true, length = 128)
    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    @Basic
    @Column(name = "DATA_COUNT", nullable = true)
    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    @Basic
    @Column(name = "TYPE", nullable = true, length = 128)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "DATA_WBJ", nullable = true, length = 128)
    public String getdataWbj() {
        return dataWbj;
    }

    public void setdataWbj(String dataWbj) {
        this.dataWbj = dataWbj;
    }

    @Basic
    @Column(name = "UPDATE_TIME", nullable = true, length = 4000)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Id
    @Column(name = "ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sgs_seq")
    @SequenceGenerator(name="sgs_seq", sequenceName="SEQ_SGS_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
