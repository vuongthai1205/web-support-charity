/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.DTO.RequestFormDTO;
import com.mycompany.pojo.PhieuYeuCau;
import com.mycompany.pojo.ThanhVien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vuong
 */
public interface PhieuYeuCauServices {

    List<PhieuYeuCau> getRequestFormList(Map<String, String> params);

    PhieuYeuCau getRequestFormUser(ThanhVien thanhvien);

    PhieuYeuCau getRequestFormId(int id);

    List<PhieuYeuCau> getRequestFormList();

    boolean addOrUpdateRequestForm(PhieuYeuCau requestForm);

    boolean deleteRequestForm(int id);
}
