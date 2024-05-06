/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhDuAn;
import com.mycompany.repository.HinhAnhDuAnRepository;
import com.mycompany.service.HinhAnhDuAnService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class HinhAnhDuAnServiceImpl implements HinhAnhDuAnService{
    
    @Autowired
    private HinhAnhDuAnRepository hinhAnhDuAnRepository;

    @Override
    public boolean addImageForProject(HinhAnhDuAn anhDuAn) {
        return this.hinhAnhDuAnRepository.addImageForProject(anhDuAn);
    }

    @Override
    public List<HinhAnhDuAn> listHinhAnh(DuAnTuThien duAn) {
        return this.hinhAnhDuAnRepository.listHinhAnh(duAn);
    }

    @Override
    public boolean deleteImage(HinhAnhDuAn anhDuAn) {
        return this.hinhAnhDuAnRepository.deleteImage(anhDuAn);
    }

    @Override
    public HinhAnhDuAn getImage(int id) {
        return this.hinhAnhDuAnRepository.getImage(id);
    }
    
}
