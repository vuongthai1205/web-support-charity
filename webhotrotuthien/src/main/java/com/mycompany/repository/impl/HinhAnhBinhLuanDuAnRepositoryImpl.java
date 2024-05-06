/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.HinhAnhBinhLuanDa;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.repository.HinhAnhBinhLuanDuAnRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuong
 */
@Repository
@Transactional
public class HinhAnhBinhLuanDuAnRepositoryImpl implements HinhAnhBinhLuanDuAnRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public boolean addImageToComment(HinhAnhBinhLuanDa hinhAnhBinhLuanDa) {
        Session session = sessionFactory.getObject().getCurrentSession();
        try{
            if(hinhAnhBinhLuanDa == null){
                session.save(hinhAnhBinhLuanDa);
            }
            else{
                session.update(hinhAnhBinhLuanDa);
            }
            return true;
        }
        catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HinhAnhBinhLuanDa> listHinhAnh(TvBinhLuanDa binhluan) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(HinhAnhBinhLuanDa.class);
        Root<HinhAnhBinhLuanDa> root = criteriaQuery.from(HinhAnhBinhLuanDa.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("maBinhLuan"), binhluan));
        
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
}
