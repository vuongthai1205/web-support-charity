/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.ThanhVienRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuongthai1205
 */
@Repository
@Transactional
public class ThanhVienRepositoryImpl implements ThanhVienRepository{
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<ThanhVien> getUsers(String name) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ThanhVien> q = b.createQuery(ThanhVien.class);
        Root root = q.from(ThanhVien.class);
        q.select(root);

        if (!name.isEmpty()) {
            Predicate p = b.equal(root.get("tenDangNhap").as(String.class), name.trim());
            q = q.where(p);
        }

        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void addOrUpdateUser(ThanhVien user) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            if (user.getMaThanhVien()== null) {

                session.save(user);
            } else {
                session.update(user);
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ThanhVien> getListUser(Map<String, String> params) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThanhVien> criteriaQuery = criteriaBuilder.createQuery(ThanhVien.class);
        Root root = criteriaQuery.from(ThanhVien.class);

        criteriaQuery.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("tenDangNhap"), String.format("%%%s%%", kw)));
            }
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }

        Query query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public ThanhVien getUserById(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        return session.get(ThanhVien.class, id);
    }

    @Override
    public boolean deleteUser(int id) {
         Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        ThanhVien user = this.getUserById(id);
        user.getQuyenSet().clear();
        try {
            session.delete(user);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ThanhVien getUserByUsername(String username) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThanhVien> criteriaQuery = criteriaBuilder.createQuery(ThanhVien.class);
        Root<ThanhVien> root = criteriaQuery.from(ThanhVien.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("tenDangNhap"), username));

        return session.createQuery(criteriaQuery).uniqueResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        ThanhVien u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }
    
}
