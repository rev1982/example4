package com.bft.spring.service;

/**
 * Created by rev on 18.12.2017.
 */

import com.bft.spring.dao.DaoImpl;
import com.bft.spring.model.DomainEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("dataBaseService")
@Transactional
public class DataBaseService {

    @Autowired
    private DaoImpl dao;

    public void save(DomainEntity domainEntity) {
        dao.save(domainEntity);
    }

    public void saveOrUpdate(DomainEntity domainEntity) {
        dao.saveOrUpdate(domainEntity);
    }

    public void delete(DomainEntity domainEntity){
        dao.delete(domainEntity);
    }

    public <T extends DomainEntity> List<T> findAll(Class<T> classs) {
        return dao.findAll(classs);
    }

    public DomainEntity findByName(String name, Class classs) {
        return dao.findByName(name, classs);
    }

    public DomainEntity findByFullName(String fullName, Class classs){
        return dao.findByFullName(fullName, classs);
    }

    public DomainEntity findById(Long id, Class classs){
        return dao.findById(id, classs);
    }

    public void update(DomainEntity domainEntity){
        dao.update(domainEntity);
    }

    public List<Long> getAllLongColumnValues(String tableName, String columnName){
        return dao.getAllLongColumnValues(tableName, columnName);
    }
}
