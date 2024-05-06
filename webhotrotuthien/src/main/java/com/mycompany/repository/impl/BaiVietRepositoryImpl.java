/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.BaiVietRepository;
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
import org.springframework.cache.annotation.Cacheable;
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
public class BaiVietRepositoryImpl implements BaiVietRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    
    @Autowired
    private Environment env;

    @Override
    public List<BaiViet> getPostList(Map<String, String> params) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();

        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<BaiViet> q = b.createQuery(BaiViet.class);
        Root root = q.from(BaiViet.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tieuDe"), String.format("%%%s%%", kw)));
            }

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
    @Cacheable(value = "baiviet", key = "#id")
    public BaiViet getPostById(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        return session.get(BaiViet.class, id);
    }

    @Override
    public boolean addOrUpdatePost(BaiViet post) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        try {
            if (post.getMaBaiViet()== null) {
                session.save(post);
            } else {
                session.update(post);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePost(int id) {
        Session session = this.sessionFactory
                .getObject()
                .getCurrentSession();
        BaiViet post = this.getPostById(id);
        try {
            session.delete(post);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countPost() {
        Session s = this.sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM BaiViet");
        return Integer.parseInt(q.getSingleResult().toString());
    }

}
