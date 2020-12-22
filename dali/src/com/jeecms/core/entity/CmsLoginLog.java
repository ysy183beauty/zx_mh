package com.jeecms.core.entity;
import java.util.Date;
public class CmsLoginLog {
    private Integer id;
    private String iP;
    private Date indate;
    private String title;
    private Integer failCount=0;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getiP() {
        return iP;
    }

    public void setiP(String iP) {
        this.iP = iP;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getFailCount() {
        return failCount;
    }
    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
}
