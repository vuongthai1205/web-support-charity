/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.Quyen;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.QuyenRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class QuyenRepositoryImpl implements QuyenRepository {
    
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public Quyen getRole(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(Quyen.class, id);
    }

    @Override
    public List<Quyen> getListRoles() {
    Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Quyen> criteriaQuery = criteriaBuilder.createQuery(Quyen.class);

        Root<Quyen> root = criteriaQuery.from(Quyen.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();    
    }

    @Override
    public List<Quyen> getListRolesByUser(ThanhVien user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Quyen> criteriaQuery = criteriaBuilder.createQuery(Quyen.class);

        Root<Quyen> roleRoot = criteriaQuery.from(Quyen.class);

        // Tham gia bảng liên quan (bảng trung gian)
        Join<Quyen, ThanhVien> userRoleJoin = roleRoot.join("thanhVienSet"); // "users" là tên thuộc tính trong Role tham chiếu đến User

        // Tạo một điều kiện để chỉ lấy các vai trò liên quan đến người dùng cụ thể
        Predicate userPredicate = criteriaBuilder.equal(userRoleJoin.get("id"), user.getMaThanhVien()); // "id" là thuộc tính định danh trong User

        criteriaQuery.select(roleRoot).where(userPredicate);

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    
    
}
