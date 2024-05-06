/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository.impl;

import com.mycompany.pojo.VaiTroThamGiaDa;
import com.mycompany.repository.VaiTroThamGiaDuAnRepository;
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
public class VaiTroThamGiaDuAnRepositoryImpl implements VaiTroThamGiaDuAnRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public VaiTroThamGiaDa vaiTroThamGiaDa(int id) {
        Session session = this.sessionFactory.getObject().getCurrentSession();

        return session.get(VaiTroThamGiaDa.class, id);
    }

}
