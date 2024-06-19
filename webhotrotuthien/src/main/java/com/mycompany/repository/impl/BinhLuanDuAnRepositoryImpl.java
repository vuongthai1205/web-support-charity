/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanBv;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.repository.BinhLuanDuAnRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class BinhLuanDuAnRepositoryImpl implements BinhLuanDuAnRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addComment(TvBinhLuanDa comment) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (comment.getMaBinhLuan() == null) {
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
    public boolean checkUserComment(ThanhVien user, DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanDa> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanDa.class);

        Root<TvBinhLuanDa> root = criteriaQuery.from(TvBinhLuanDa.class);

        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("duAn"), duAnTuThien),
                criteriaBuilder.equal(root.get("thanhVien"), user)
        ));
        TvBinhLuanDa existingComment = session.createQuery(criteriaQuery).uniqueResult();
        if (existingComment != null) {
            return true;
        }

        return false;
    }

    @Override
    public TvBinhLuanDa getCommentPost(ThanhVien user, DuAnTuThien duAnTuThien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TvBinhLuanDa> listCommentPost(DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanDa> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanDa.class);

        Root<TvBinhLuanDa> root = criteriaQuery.from(TvBinhLuanDa.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                criteriaBuilder.equal(root.get("trangThai"), 0));
        predicates.add(
                criteriaBuilder.equal(root.get("duAnTuThien"), duAnTuThien));
        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ngayTao")));
        Query<TvBinhLuanDa> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public TvBinhLuanDa getCommentById(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(TvBinhLuanDa.class, id);
    }

    @Override
    public boolean deleteComment(TvBinhLuanDa comment) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.delete(comment);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TvBinhLuanDa> listCommentProjectPrivate(DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanDa> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanDa.class);

        Root<TvBinhLuanDa> root = criteriaQuery.from(TvBinhLuanDa.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                criteriaBuilder.equal(root.get("trangThai"), 1));
        predicates.add(
                criteriaBuilder.equal(root.get("duAnTuThien"), duAnTuThien));
        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ngayTao")));
        Query<TvBinhLuanDa> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<TvBinhLuanDa> listAllCommentPost(DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<TvBinhLuanDa> criteriaQuery = criteriaBuilder.createQuery(TvBinhLuanDa.class);

        Root<TvBinhLuanDa> root = criteriaQuery.from(TvBinhLuanDa.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(
                criteriaBuilder.equal(root.get("duAnTuThien"), duAnTuThien));
        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ngayTao")));
        Query<TvBinhLuanDa> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
