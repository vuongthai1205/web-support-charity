/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.TrangThaiDauGia;
import com.mycompany.repository.TrangThaiDauGiaRepository;
import java.util.List;
import javax.persistence.Query;
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
public class TrangThaiDauGiaRepositoryImpl implements TrangThaiDauGiaRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<TrangThaiDauGia> getAuctionStatuses() {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM TrangThaiDauGia");
        return q.getResultList();
    }

    @Override
    public TrangThaiDauGia getAuctionStatus(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();
        return session.get(TrangThaiDauGia.class, id);
    }
}
