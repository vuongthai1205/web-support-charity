/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.ThamGiaDuAnPK;
import com.mycompany.pojo.ThanhVien;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface ThamGiaDuAnRepository {
    boolean addUserToProject(ThamGiaDuAn thamGiaDuAn);
    boolean updateJoinProject(ThamGiaDuAn thamGiaDuAn);
    ThamGiaDuAn getThamGiaDuAn(ThanhVien thanhVien, DuAnTuThien duAn);
    List<ThamGiaDuAn> getThamGiaDuAnByDuAn(DuAnTuThien duAn);
    boolean deleteJoinProject(ThamGiaDuAn thamGiaDuAn); 
}
