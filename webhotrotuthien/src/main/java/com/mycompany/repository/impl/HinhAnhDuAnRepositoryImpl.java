/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhDuAn;
import com.mycompany.repository.HinhAnhDuAnRepository;
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
 * @author vuongthai1205
 */
@Repository
@Transactional
public class HinhAnhDuAnRepositoryImpl implements HinhAnhDuAnRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addImageForProject(HinhAnhDuAn anhDuAn) {
         Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            if (anhDuAn.getMaHinhDuAn() == null) {
                session.save(anhDuAn);
            } else {
                session.update(anhDuAn);
                
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        } 
    }

    @Override
    public List<HinhAnhDuAn> listHinhAnh(DuAnTuThien duAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(HinhAnhDuAn.class);
        Root<HinhAnhDuAn> root = criteriaQuery.from(HinhAnhDuAn.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("duAn"), duAn));
        
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean deleteImage(HinhAnhDuAn anhDuAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.delete(anhDuAn);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public HinhAnhDuAn getImage(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(HinhAnhDuAn.class, id);
    }
    
}
