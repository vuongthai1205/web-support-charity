/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.HinhAnhBinhLuanDa;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.repository.HinhAnhBinhLuanDuAnRepository;
import com.mycompany.service.HinhAnhBinhLuanDuAnService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuong
 */
@Service
public class HinhAnhBinhLuanDuAnServiceImpl implements HinhAnhBinhLuanDuAnService{
    @Autowired
    private HinhAnhBinhLuanDuAnRepository hinhAnhBinhLuanDuAnRepository;

    @Override
    public boolean addImageToComment(HinhAnhBinhLuanDa hinhAnhBinhLuanDa) {
        return hinhAnhBinhLuanDuAnRepository.addImageToComment(hinhAnhBinhLuanDa);
    }

    @Override
    public List<HinhAnhBinhLuanDa> listHinhAnh(TvBinhLuanDa binhluan) {
        return hinhAnhBinhLuanDuAnRepository.listHinhAnh(binhluan);
    }
    
}
