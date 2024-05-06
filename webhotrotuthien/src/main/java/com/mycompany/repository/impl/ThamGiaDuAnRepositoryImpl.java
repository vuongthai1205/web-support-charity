/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.DauGia;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.ThamGiaDuAnPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.ThamGiaDuAnRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
public class ThamGiaDuAnRepositoryImpl implements ThamGiaDuAnRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addUserToProject(ThamGiaDuAn thamGiaDuAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.save(thamGiaDuAn);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateJoinProject(ThamGiaDuAn thamGiaDuAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        try {
            session.update(thamGiaDuAn);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ThamGiaDuAn getThamGiaDuAn(ThanhVien thanhVien, DuAnTuThien duAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThamGiaDuAn> criteriaQuery = criteriaBuilder.createQuery(ThamGiaDuAn.class);

        Root<ThamGiaDuAn> root = criteriaQuery.from(ThamGiaDuAn.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("duAnTuThien"), duAn),
                criteriaBuilder.equal(root.get("thanhVien"), thanhVien)
        ));

        ThamGiaDuAn thamGiaDuAn = session.createQuery(criteriaQuery).uniqueResult();
        return thamGiaDuAn;
    }

    @Override
    public List<ThamGiaDuAn> getThamGiaDuAnByDuAn(DuAnTuThien duAn) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<ThamGiaDuAn> criteriaQuery = criteriaBuilder.createQuery(ThamGiaDuAn.class);

        Root<ThamGiaDuAn> root = criteriaQuery.from(ThamGiaDuAn.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("duAnTuThien"), duAn)
        ));

         List<ThamGiaDuAn> thamGiaDuAns = session.createQuery(criteriaQuery).getResultList();
        return thamGiaDuAns;
    }

    @Override
    public boolean deleteJoinProject(ThamGiaDuAn thamGiaDuAn) {
         Session session = this.sessionFactory.getObject().getCurrentSession();
         try {
            session.delete(thamGiaDuAn);
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
