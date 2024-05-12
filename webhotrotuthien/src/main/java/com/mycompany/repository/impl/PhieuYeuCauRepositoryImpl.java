/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.PhieuYeuCau;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.PhieuYeuCauRepository;
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
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vuong
 */
@Repository
@Transactional
public class PhieuYeuCauRepositoryImpl implements PhieuYeuCauRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    
    @Autowired
    private Environment env;
        

    @Override
    public List<PhieuYeuCau> getRequestFormList(Map<String, String> params) {
    
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<PhieuYeuCau> q = b.createQuery(PhieuYeuCau.class);
        Root root = q.from(PhieuYeuCau.class);
        q.select(root);
        
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String iduser = params.get("iduser");
            // Lấy tham số iduser từ Map
            
            if (iduser != null && !iduser.isEmpty()) {
                ThanhVien u = this.thanhVienRepository.getUserById(Integer.parseInt(iduser));
                predicates.add(b.equal(root.get("maThanhVien"), u)); // Thêm điều kiện lọc theo iduser
            }

            if (!predicates.isEmpty()) {
                q.where(predicates.toArray(Predicate[]::new));
            }
        }
        q.orderBy(b.desc(root.get("ngayTao")));
        Query query = session.createQuery(q);
        
        
        return query.getResultList();
    }

    @Override
    public PhieuYeuCau getRequestFormUser(ThanhVien thanhvien) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<PhieuYeuCau> q = b.createQuery(PhieuYeuCau.class);
        Root root = q.from(PhieuYeuCau.class);
        q.select(root).where(b.and(
                b.equal(root.get("maThanhVien"), thanhvien)
        ));
        
        PhieuYeuCau query = session.createQuery(q).uniqueResult();
        
        
        return query;
    }

    @Override
    public boolean addOrUpdateRequestForm(PhieuYeuCau requestForm) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            if (requestForm.getMaPhieuYeuCau()== null) {
                session.save(requestForm);
            } else {
                session.update(requestForm);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRequestForm(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        PhieuYeuCau reuqestForm = this.getRequestFormId(id);
        try {
            session.delete(reuqestForm);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public PhieuYeuCau getRequestFormId(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        return session.get(PhieuYeuCau.class, id);
    }

    @Override
    public List<PhieuYeuCau> getRequestFormList() {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<PhieuYeuCau> q = b.createQuery(PhieuYeuCau.class);
        Root root = q.from(PhieuYeuCau.class);
        q.select(root);
        
        
        q.orderBy(b.desc(root.get("ngayTao")));
        Query query = session.createQuery(q);
        
        
        return query.getResultList();
    }
    
}
