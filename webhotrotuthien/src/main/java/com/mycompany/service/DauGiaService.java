/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DauGia;
import com.mycompany.pojo.ThanhVien;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface DauGiaService {
    boolean addAuction(DauGia auction);
    boolean updateAuction(DauGia auction);
    boolean checkAuctionExist(ThanhVien user, BaiViet post);
    List<DauGia> getListAuction(BaiViet post);
    DauGia getAuctionById(int thanhVienId, int  baiVietId);
    boolean updateListAuction(List<DauGia> auctions);
}
