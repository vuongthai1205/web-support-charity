/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvThichBv;
import com.mycompany.repository.ThichRepository;
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
public class ThichRepositoryImpl implements ThichRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addLike(TvThichBv likePost) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
                session.save(likePost);
            
            return true;
        }
        catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkUserLiked(ThanhVien user, BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvThichBv> criteriaQuery = criteriaBuilder.createQuery(TvThichBv.class);
        
        Root<TvThichBv> root = criteriaQuery.from(TvThichBv.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post),
                criteriaBuilder.equal(root.get("thanhVien"), user)
            ));
        TvThichBv existingLike = session.createQuery(criteriaQuery).uniqueResult();
        
        if (existingLike != null) {
            return true;
        }
        
        return false;
        
    }

    @Override
    public TvThichBv getLikePost(ThanhVien user, BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvThichBv> criteriaQuery = criteriaBuilder.createQuery(TvThichBv.class);
        
        Root<TvThichBv> root = criteriaQuery.from(TvThichBv.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post),
                criteriaBuilder.equal(root.get("thanhVien"), user)
            ));
        TvThichBv existingLike = session.createQuery(criteriaQuery).uniqueResult();
        
        return existingLike;
    }

    @Override
    public List<TvThichBv> getLikePosts(BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvThichBv> criteriaQuery = criteriaBuilder.createQuery(TvThichBv.class);
        
        Root<TvThichBv> root = criteriaQuery.from(TvThichBv.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post),
                criteriaBuilder.equal(root.get("daThich"), 1)
        ));
        
        Query q = session.createQuery(criteriaQuery);
        
        return q.getResultList();
    }

    @Override
    public List<TvThichBv> getLikePostsByPost(BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvThichBv> criteriaQuery = criteriaBuilder.createQuery(TvThichBv.class);
        
        Root<TvThichBv> root = criteriaQuery.from(TvThichBv.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post)
        ));
        
        Query q = session.createQuery(criteriaQuery);
        
        return q.getResultList();
    }

    @Override
    public boolean deleteLikePost(TvThichBv likePost) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
            session.delete(likePost);
            return true;
        }
        catch(HibernateException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateLike(TvThichBv likePost) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try{
                session.update(likePost);
            
            return true;
        }
        catch(HibernateException ex){
            ex.printStackTrace();
            return false;
        }
    }
}
