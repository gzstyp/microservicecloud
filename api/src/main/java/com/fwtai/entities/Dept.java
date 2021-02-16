package com.fwtai.entities;

import java.io.Serializable;

/**
 * ORM 一一对一的,dept orm mysql->dept(table) 类和表的关系映射,微服务必须序列化
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 11:55
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
public class Dept implements Serializable{

    private Integer deptno;

    private String dname;

    private String db_source;

    public Dept(){
    }

    public Dept(String dname){
        this.dname = dname;
    }

    public Dept(Integer deptno,String dname,String db_source){
        this.deptno = deptno;
        this.dname = dname;
        this.db_source = db_source;
    }

    @Override
    public String toString(){
        return "Dept{" + "deptno=" + deptno + ", dname='" + dname + '\'' + ", db_source='" + db_source + '\'' + '}';
    }

    public Integer getDeptno(){
        return deptno;
    }

    public void setDeptno(Integer deptno){
        this.deptno = deptno;
    }

    public String getDname(){
        return dname;
    }

    public void setDname(String dname){
        this.dname = dname;
    }

    public String getDb_source(){
        return db_source;
    }

    public void setDb_source(String db_source){
        this.db_source = db_source;
    }
}