/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.repository.*;
import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.pojo.BaoCaoThanhVienPK;
import com.mycompany.pojo.ThanhVien;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface BaoCaoThanhVienService {
    boolean addReport(BaoCaoThanhVien reportUser);
    boolean updateReport(BaoCaoThanhVien reportUser);
    List<BaoCaoThanhVien> getListReportUsers(ThanhVien user);
    BaoCaoThanhVien getReportUserById(BaoCaoThanhVienPK baoCaoThanhVienPK);
    BaoCaoThanhVien getReportUser(ThanhVien user, ThanhVien userReported);
    List<BaoCaoThanhVien> getListReportUsers();
    
}
