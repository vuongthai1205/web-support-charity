/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.TrangThaiDauGia;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface TrangThaiDauGiaService {
    List<TrangThaiDauGia> getAuctionStatuses();
    TrangThaiDauGia getAuctionStatus(int id);
}
