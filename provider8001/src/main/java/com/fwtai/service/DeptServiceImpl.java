package com.fwtai.service;

import com.fwtai.api.DeptService;
import com.fwtai.dao.DeptDao;
import com.fwtai.entities.Dept;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接口实现,get 或 list才符合restFul的风格
 * @作者 田应平
 * @版本 v1.0
 * @创建时间 2019-05-01 22:40
 * @QQ号码 444141300
 * @官网 http://www.fwtai.com
*/
@Service
public class DeptServiceImpl implements DeptService{

    @Resource
    private DeptDao deptDao;

    @Override
    public Integer add(Dept dept){
        return deptDao.addDept(dept);
    }

    //get 或 list才符合restFul的风格
    @Override
    public Dept get(Integer id){
        return deptDao.findById(id);
    }

    //get 或 list才符合restFul的风格
    @Override
    public List<Dept> list(){
        return deptDao.findAll();
    }
}