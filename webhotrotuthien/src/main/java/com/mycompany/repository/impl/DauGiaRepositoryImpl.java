/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DauGia;
import com.mycompany.pojo.DauGiaPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.DauGiaRepository;
import java.util.Date;
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
public class DauGiaRepositoryImpl implements DauGiaRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public boolean addAuction(DauGia auction) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            session.save(auction);
            return true;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkAuctionExist(ThanhVien user, BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DauGia> criteriaQuery = criteriaBuilder.createQuery(DauGia.class);

        Root<DauGia> root = criteriaQuery.from(DauGia.class);
        criteriaQuery.select(root).where(criteriaBuilder.and(
                criteriaBuilder.equal(root.get("baiViet"), post),
                criteriaBuilder.equal(root.get("thanhVien"), user)
        ));

        DauGia auction = session.createQuery(criteriaQuery).uniqueResult();
        if (auction != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<DauGia> getListAuction(BaiViet post) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<DauGia> criteriaQuery = criteriaBuilder.createQuery(DauGia.class);

        Root<DauGia> root = criteriaQuery.from(DauGia.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("baiViet"), post));
        Query q = session.createQuery(criteriaQuery);

        return q.getResultList();
    }

    @Override
    public DauGia getAuctionById(int thanhVienId, int baiVietId) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        DauGiaPK dauGiaPK = new DauGiaPK(thanhVienId, baiVietId); // Thay thế thanhVienId và baiVietId bằng giá trị thích hợp

        // Sử dụng phương thức get của Hibernate để lấy DauGia bằng khóa chính.
        DauGia dauGia = session.get(DauGia.class, dauGiaPK);

        return dauGia;
    }

    @Override
    public boolean updateListAuction(List<DauGia> auctions) {
        try {

            for (DauGia auction : auctions) {
                this.updateAuction(auction); // Cập nhật từng phiên đấu giá trong danh sách
            }

            return true; // Trả về true nếu cập nhật thành công
        } catch (HibernateException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    @Override
    public boolean updateAuction(DauGia auction) {

        Session session = this.sessionFactory.getObject().getCurrentSession();

        try {
            session.update(auction);
            return true;

        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
