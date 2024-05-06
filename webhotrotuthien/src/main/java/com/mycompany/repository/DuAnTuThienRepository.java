/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThanhVien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vuongthai1205
 */
public interface DuAnTuThienRepository {
    boolean addOrUpdateCharityProject(DuAnTuThien duAnTuThien);
    List<DuAnTuThien> getDuAnTuThiens(Map<String, String> params);
    List<DuAnTuThien> getDuAnTuThiensWithApproved(Map<String, String> params);
    DuAnTuThien getDuAnTuThienById(int id);
    boolean deleteProject(DuAnTuThien duAnTuThien);
}
