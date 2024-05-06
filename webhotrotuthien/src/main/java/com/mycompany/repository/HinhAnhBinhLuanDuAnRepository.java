/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.HinhAnhBinhLuanDa;
import com.mycompany.pojo.TvBinhLuanDa;
import java.util.List;

/**
 *
 * @author vuong
 */
public interface HinhAnhBinhLuanDuAnRepository {
    boolean addImageToComment(HinhAnhBinhLuanDa hinhAnhBinhLuanDa);
    public List<HinhAnhBinhLuanDa> listHinhAnh(TvBinhLuanDa binhluan);
}
