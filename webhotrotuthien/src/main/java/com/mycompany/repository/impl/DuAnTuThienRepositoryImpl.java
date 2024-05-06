/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.repository.DuAnTuThienRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuongthai1205
 */
@Repository
@Transactional
public class DuAnTuThienRepositoryImpl implements DuAnTuThienRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    private Environment env;

    @Override
    public boolean addOrUpdateCharityProject(DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            if (duAnTuThien.getMaDuAn() == null) {
                session.save(duAnTuThien);
            } else {
                session.update(duAnTuThien);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DuAnTuThien> getDuAnTuThiens(Map<String, String> params) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = (CriteriaBuilder) session.getCriteriaBuilder();
        CriteriaQuery<DuAnTuThien> q = b.createQuery(DuAnTuThien.class);
        Root root = q.from(DuAnTuThien.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenDuAn"), String.format("%%%s%%", kw)));
            }

        }

        q.orderBy(b.desc(root.get("ngayTao")));

        Query query = session.createQuery(q);
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                if (p > 0) {  // Kiểm tra nếu page > 0 thì áp dụng giới hạn và vị trí bắt đầu
                    query.setMaxResults(pageSize);
                    query.setFirstResult((p - 1) * pageSize);
                }
            }
        }
        return query.getResultList();
    }

    @Override
    public DuAnTuThien getDuAnTuThienById(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        return session.get(DuAnTuThien.class, id);
    }

    @Override
    public boolean deleteProject(DuAnTuThien duAnTuThien) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            session.delete(duAnTuThien);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DuAnTuThien> getDuAnTuThiensWithApproved(Map<String, String> params) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<DuAnTuThien> q = b.createQuery(DuAnTuThien.class);
        Root root = q.from(DuAnTuThien.class);
        q.select(root).where(b.equal(root.get("daDuyet"), 1));

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenDuAn"), String.format("%%%s%%", kw)));
            }
            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
            }

        }
        q.orderBy(b.desc(root.get("ngayTao")));

        Query query = session.createQuery(q);
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                if (p > 0) {  // Kiểm tra nếu page > 0 thì áp dụng giới hạn và vị trí bắt đầu
                    query.setMaxResults(pageSize);
                    query.setFirstResult((p - 1) * pageSize);
                }
            }
        }
        return query.getResultList();
    }

}
