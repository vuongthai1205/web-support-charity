/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanBv;
import com.mycompany.repository.BinhLuanRepository;
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
public class BinhLuanRepositoryImpl implements BinhLuanRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addComment(TvBinhLuanBv comment) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (comment.getMaBinhLuan()== null) {
                session.save(comment);
            } else {
                session.update(comment);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkUserComment(ThanhVien user, BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanBv> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanBv.class);

        Root<TvBinhLuanBv> root = criteriaQuery.from(TvBinhLuanBv.class);

        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post),
                criteriaBuilder.equal(root.get("thanhVien"), user)
        ));
        TvBinhLuanBv existingComment = session.createQuery(criteriaQuery).uniqueResult();
        if (existingComment != null) {
            return true;
        }

        return false;
    }

    @Override
    public TvBinhLuanBv getCommentPost(ThanhVien user, BaiViet post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TvBinhLuanBv> listCommentPost(BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanBv> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanBv.class);

        Root<TvBinhLuanBv> root = criteriaQuery.from(TvBinhLuanBv.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get("baiViet"), post))
                .orderBy(criteriaBuilder.desc(root.get("ngayTao"))); // Sắp xếp theo thứ tự mới nhất

        Query<TvBinhLuanBv> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public TvBinhLuanBv getCommentById(int id) {
         Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(TvBinhLuanBv.class, id);
    }

    @Override
    public boolean deleteComment(TvBinhLuanBv comment) {
         Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.delete(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
