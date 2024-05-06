/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.pojo.BaoCaoThanhVienPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.BaoCaoThanhVienRepository;
import com.mycompany.service.BaoCaoThanhVienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class BaoCaoThanhVienServiceImpl implements BaoCaoThanhVienService{
    
    @Autowired
    private  BaoCaoThanhVienRepository baoCaoThanhVienRepository;

    @Override
    public boolean addReport(BaoCaoThanhVien reportUser) {
        return this.baoCaoThanhVienRepository.addReport(reportUser);
    }

    @Override
    public boolean updateReport(BaoCaoThanhVien reportUser) {
        return this.baoCaoThanhVienRepository.updateReport(reportUser);
    }

    @Override
    public List<BaoCaoThanhVien> getListReportUsers(ThanhVien user) {
        return this.baoCaoThanhVienRepository.getListReportUsers(user);
    }

    @Override
    public BaoCaoThanhVien getReportUserById(BaoCaoThanhVienPK baoCaoThanhVienPK) {
        return this.baoCaoThanhVienRepository.getReportUserById(baoCaoThanhVienPK);
    }

    @Override
    public BaoCaoThanhVien getReportUser(ThanhVien user, ThanhVien userReported) {
        return this.baoCaoThanhVienRepository.getReportUser(user, userReported);
    }

    @Override
    public List<BaoCaoThanhVien> getListReportUsers() {
        return this.baoCaoThanhVienRepository.getListReportUsers();
    }
    
}
