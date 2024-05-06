/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.Quyen;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.QuyenRepository;
import com.mycompany.service.QuyenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class QuyenServiceImpl implements QuyenService{
    
    @Autowired
    private QuyenRepository quyenRepository;

    @Override
    public Quyen getRole(int id) {
        return this.quyenRepository.getRole(id);
    }

    @Override
    public List<Quyen> getListRoles() {
        return this.quyenRepository.getListRoles();
    }

    @Override
    public List<Quyen> getListRolesByUser(ThanhVien user) {
        return this.quyenRepository.getListRolesByUser(user);
    }
    
}
