/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.pojo.BaoCaoThanhVienPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.BaoCaoThanhVienRepository;
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
public class BaoCaoThanhVienRepositoryImpl implements BaoCaoThanhVienRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addReport(BaoCaoThanhVien reportUser) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {

            session.save(reportUser);

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateReport(BaoCaoThanhVien reportUser) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {

            session.update(reportUser);

            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BaoCaoThanhVien> getListReportUsers(ThanhVien user) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BaoCaoThanhVien> criteriaQuery = criteriaBuilder.createQuery(BaoCaoThanhVien.class);
        Root root = criteriaQuery.from(BaoCaoThanhVien.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("thanhVien"), user));

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public BaoCaoThanhVien getReportUserById(BaoCaoThanhVienPK baoCaoThanhVienPK) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(BaoCaoThanhVien.class, baoCaoThanhVienPK);
    }

    @Override
    public BaoCaoThanhVien getReportUser(ThanhVien user, ThanhVien userReported) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BaoCaoThanhVien> criteriaQuery = criteriaBuilder.createQuery(BaoCaoThanhVien.class);
        Root root = criteriaQuery.from(BaoCaoThanhVien.class);

        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("thanhVien"), user),
                criteriaBuilder.equal(root.get("thanhVien1"), userReported)));

        BaoCaoThanhVien reportUser = session.createQuery(criteriaQuery).uniqueResult();
        return reportUser;
    }

    @Override
    public List<BaoCaoThanhVien> getListReportUsers() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BaoCaoThanhVien> criteriaQuery = criteriaBuilder.createQuery(BaoCaoThanhVien.class);
        Root root = criteriaQuery.from(BaoCaoThanhVien.class);

        criteriaQuery.select(root).orderBy(criteriaBuilder.desc(root.get("ngayTao")));
        

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
