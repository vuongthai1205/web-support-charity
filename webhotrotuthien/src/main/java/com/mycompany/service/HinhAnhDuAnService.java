/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhDuAn;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface HinhAnhDuAnService {
    boolean addImageForProject(HinhAnhDuAn anhDuAn);
    List<HinhAnhDuAn> listHinhAnh(DuAnTuThien duAn);
    boolean deleteImage(HinhAnhDuAn anhDuAn);
    HinhAnhDuAn getImage(int id );
}
