/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.HinhAnhBaiViet;
import com.mycompany.repository.HinhAnhBaiVietRepository;
import com.mycompany.service.HinhAnhBaiVietService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class HinhAnhBaiVietServiceImpl implements HinhAnhBaiVietService{
    @Autowired
    private HinhAnhBaiVietRepository hinhAnhBaiVietRepository;

    @Override
    public boolean addImageForPost(HinhAnhBaiViet anhBaiViet) {
        return this.hinhAnhBaiVietRepository.addImageForPost(anhBaiViet);
    }

    @Override
    public List<HinhAnhBaiViet> listHinhAnh(BaiViet baiViet) {
        return this.hinhAnhBaiVietRepository.listHinhAnh(baiViet);
    }

    @Override
    public boolean deleteImage(HinhAnhBaiViet anhBaiViet) {
        return this.hinhAnhBaiVietRepository.deleteImage(anhBaiViet);
    }

    @Override
    public HinhAnhBaiViet getImage(int id) {
        return this.hinhAnhBaiVietRepository.getImage(id);
    }
    
}
