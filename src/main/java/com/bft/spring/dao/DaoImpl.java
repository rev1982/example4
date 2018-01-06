package com.bft.spring.dao;

/**
 * Created by rev on 18.12.2017.
 */
import java.util.List;

import com.bft.spring.model.DomainEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class DaoImpl extends AbstractDao{

    public void save(DomainEntity domainEntity) {
        persist(domainEntity);
    }

    public void saveOrUpdate(DomainEntity domainEntity) {
        saveOrUpdateEntity(domainEntity);
    }

    @SuppressWarnings("unchecked")
    public <T extends DomainEntity> List<T> findAll(Class<T> classs) {
        Criteria criteria = getSession().createCriteria(classs);
        return (List<T>) criteria.list();
    }

    public DomainEntity findByName(String name, Class classs){
        Criteria criteria = getSession().createCriteria(classs);
        criteria.add(Restrictions.eq("name", name));
        return (DomainEntity) criteria.uniqueResult();
    }

    public DomainEntity findById(Long id, Class classs){
        Criteria criteria = getSession().createCriteria(classs);
        criteria.add(Restrictions.eq("id", id));
        return (DomainEntity) criteria.uniqueResult();
    }


    public DomainEntity findByFullName(String fullName, Class classs){
        Criteria criteria = getSession().createCriteria(classs);
        criteria.add(Restrictions.eq("fullName", fullName));
        return (DomainEntity) criteria.uniqueResult();
    }

    public void update(DomainEntity domainEntity){
        getSession().update(domainEntity);
    }

    public List<String> getAllStringColumnValues(String tableName, String columnName){
        return getSession().createQuery("SELECT mt." + columnName + " FROM " + tableName + " mt").list();
    }

    public List<Long> getAllLongColumnValues(String tableName, String columnName){
        return getSession().createQuery("SELECT mt." + columnName + " FROM " + tableName + " mt").list();
    }

}