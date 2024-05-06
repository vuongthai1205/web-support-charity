/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.HinhAnhBaiViet;
import com.mycompany.repository.HinhAnhBaiVietRepository;
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
public class HinhAnhBaiVietRepositoryImpl implements HinhAnhBaiVietRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addImageForPost(HinhAnhBaiViet anhBaiViet) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            if (anhBaiViet.getMaHinhBaiViet() == null) {
                session.save(anhBaiViet);
            } else {
                session.update(anhBaiViet);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HinhAnhBaiViet> listHinhAnh(BaiViet baiViet) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(HinhAnhBaiViet.class);
        Root<HinhAnhBaiViet> root = criteriaQuery.from(HinhAnhBaiViet.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("baiViet"), baiViet));
        
        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public boolean deleteImage(HinhAnhBaiViet anhBaiViet) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.delete(anhBaiViet);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public HinhAnhBaiViet getImage(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(HinhAnhBaiViet.class, id);
    }
    
    
}
