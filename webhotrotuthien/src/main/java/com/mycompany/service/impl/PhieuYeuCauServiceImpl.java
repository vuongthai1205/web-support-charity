/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.googlecode.jmapper.JMapper;
import com.mycompany.DTO.RequestFormDTO;
import com.mycompany.pojo.PhieuYeuCau;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.PhieuYeuCauRepository;
import com.mycompany.repository.ThanhVienRepository;
import com.mycompany.service.PhieuYeuCauServices;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuong
 */
@Service
public class PhieuYeuCauServiceImpl implements PhieuYeuCauServices {

    @Autowired
    private PhieuYeuCauRepository phieuYeuCauRepository;

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public List<PhieuYeuCau> getRequestFormList(Map<String, String> params) {
        return phieuYeuCauRepository.getRequestFormList(params);
    }

    @Override
    public PhieuYeuCau getRequestFormUser(ThanhVien thanhvien) {
        return phieuYeuCauRepository.getRequestFormUser(thanhvien);
    }

    @Override
    public PhieuYeuCau getRequestFormId(int id) {
        return phieuYeuCauRepository.getRequestFormId(id);
    }

    @Override
    public boolean addOrUpdateRequestForm(PhieuYeuCau phieuYeuCau) {
        if (phieuYeuCau.getMaPhieuYeuCau() != null && phieuYeuCau.getXacNhan() == 1 && phieuYeuCau.getNgayCapNhat() == null && phieuYeuCau.getLoaiPhieu().compareTo("nap_tien") == 0) {
            ThanhVien thanhVien = thanhVienRepository.getUserById(phieuYeuCau.getMaThanhVien().getMaThanhVien());
            if(thanhVien.getTongTien() == null){
                thanhVien.setTongTien(0.0);
            }
            thanhVien.setTongTien(thanhVien.getTongTien() + phieuYeuCau.getSoTien());
            thanhVienRepository.addOrUpdateUser(thanhVien);
        }
        else if(phieuYeuCau.getMaPhieuYeuCau() != null && phieuYeuCau.getXacNhan() == 1 && phieuYeuCau.getNgayCapNhat() == null && phieuYeuCau.getLoaiPhieu().compareTo("rut_tien") == 0){
            ThanhVien thanhVien = thanhVienRepository.getUserById(phieuYeuCau.getMaThanhVien().getMaThanhVien());
            if(thanhVien.getTongTien() == null){
                thanhVien.setTongTien(0.0);
            }
            thanhVien.setTongTien(thanhVien.getTongTien() - phieuYeuCau.getSoTien());
            thanhVienRepository.addOrUpdateUser(thanhVien);
        }
        else{
            phieuYeuCau.setXacNhan((short)0);
        }

        return phieuYeuCauRepository.addOrUpdateRequestForm(phieuYeuCau);
    }

    @Override
    public boolean deleteRequestForm(int id) {
        return phieuYeuCauRepository.deleteRequestForm(id);
    }

    @Override
    public List<PhieuYeuCau> getRequestFormList() {
        return phieuYeuCauRepository.getRequestFormList();
    }

}
