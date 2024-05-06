/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.TrangThaiDauGia;
import com.mycompany.repository.TrangThaiDauGiaRepository;
import com.mycompany.service.TrangThaiDauGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class TrangThaiDauGiaServiceImpl implements TrangThaiDauGiaService{
    @Autowired
    private TrangThaiDauGiaRepository trangThaiDauGiaRepository;

    @Override
    public List<TrangThaiDauGia> getAuctionStatuses() {
        return this.trangThaiDauGiaRepository.getAuctionStatuses();
    }

    @Override
    public TrangThaiDauGia getAuctionStatus(int id) {
        return this.trangThaiDauGiaRepository.getAuctionStatus(id);
    }
    
}
