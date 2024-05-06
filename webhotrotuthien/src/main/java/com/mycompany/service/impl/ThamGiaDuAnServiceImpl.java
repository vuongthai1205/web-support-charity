/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.ThamGiaDuAnPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.ThamGiaDuAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.service.ThamGiaDuAnService;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
@Service
public class ThamGiaDuAnServiceImpl implements ThamGiaDuAnService{
    @Autowired
    private ThamGiaDuAnRepository thamGiaDuAnRepository;

    @Override
    public boolean addUserToProject(ThamGiaDuAn thamGiaDuAn) {
        return this.thamGiaDuAnRepository.addUserToProject(thamGiaDuAn);
    }

    @Override
    public boolean updateJoinProject(ThamGiaDuAn thamGiaDuAn) {
        return this.thamGiaDuAnRepository.updateJoinProject(thamGiaDuAn);
    }

    @Override
    public ThamGiaDuAn getThamGiaDuAn(ThanhVien thanhVien, DuAnTuThien duAn) {
        return this.thamGiaDuAnRepository.getThamGiaDuAn(thanhVien, duAn);
    }

    @Override
    public List<ThamGiaDuAn> getThamGiaDuAnByDuAn(DuAnTuThien duAn) {
        return this.thamGiaDuAnRepository.getThamGiaDuAnByDuAn(duAn);
    }

    @Override
    public boolean deleteJoinProject(ThamGiaDuAn thamGiaDuAn) {
        return this.thamGiaDuAnRepository.deleteJoinProject(thamGiaDuAn);
    }
    
}
