/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DauGia;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TrangThaiDauGia;
import com.mycompany.repository.BaiVietRepository;
import com.mycompany.repository.DauGiaRepository;
import com.mycompany.repository.ThanhVienRepository;
import com.mycompany.repository.TrangThaiDauGiaRepository;
import com.mycompany.service.DauGiaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class DauGiaServiceImpl implements DauGiaService {

    @Autowired
    private DauGiaRepository dauGiaRepository;

    @Autowired
    private BaiVietRepository baiVietRepository;

    @Autowired
    private TrangThaiDauGiaRepository trangThaiDauGiaRepository;
    @Autowired
    private ThanhVienRepository thanhVienRepository;

    @Override
    public boolean addAuction(DauGia auction) {
        auction.setIsWinnerAuction(false);
        if(auction.getGiaTien()!= null){
            ThanhVien t = thanhVienRepository.getUserById(auction.getThanhVien().getMaThanhVien());
            
            t.setTongTien(t.getTongTien() - auction.getGiaTien());
            thanhVienRepository.addOrUpdateUser(t);
        }
        return this.dauGiaRepository.addAuction(auction);
    }

    @Override
    public boolean updateAuction(DauGia auction) {

        TrangThaiDauGia auctionStatus = trangThaiDauGiaRepository.getAuctionStatus(3);
        BaiViet post = auction.getBaiViet();
        post.setTrangThaiDauGia(auctionStatus);

        List<DauGia> auctionList = getListAuction(post);
        boolean isAuctionUpdated = updateListAuction(auctionList);
        boolean isPostUpdated = baiVietRepository.addOrUpdatePost(post);

        if (isAuctionUpdated && isPostUpdated) {
            auction.setIsWinnerAuction(true);
            return dauGiaRepository.updateAuction(auction);
        }

        return false;
    }

    @Override
    public boolean checkAuctionExist(ThanhVien user, BaiViet post) {
        return this.dauGiaRepository.checkAuctionExist(user, post);
    }

    @Override
    public List<DauGia> getListAuction(BaiViet post) {
        return this.dauGiaRepository.getListAuction(post);

    }

    @Override
    public DauGia getAuctionById(int thanhVienId, int baiVietId) {
        return this.dauGiaRepository.getAuctionById(thanhVienId, baiVietId);
    }

    @Override
    public boolean updateListAuction(List<DauGia> auctions) {
        auctions.forEach(auction -> {
            auction.setIsWinnerAuction(false);
        });
        return this.dauGiaRepository.updateListAuction(auctions);
    }

}
