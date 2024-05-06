/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.HinhAnhBaiViet;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface HinhAnhBaiVietService {
    boolean addImageForPost(HinhAnhBaiViet anhBaiViet);
    List<HinhAnhBaiViet> listHinhAnh(BaiViet baiViet);
    boolean deleteImage(HinhAnhBaiViet anhBaiViet);
    HinhAnhBaiViet getImage(int id );
}
