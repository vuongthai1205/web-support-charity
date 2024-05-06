/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanBv;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface BinhLuanRepository {
    
    boolean addComment(TvBinhLuanBv comment);

    boolean checkUserComment(ThanhVien user, BaiViet post);

    TvBinhLuanBv getCommentPost(ThanhVien user, BaiViet post);

    List<TvBinhLuanBv> listCommentPost(BaiViet post);
    
    TvBinhLuanBv getCommentById(int id);
    boolean deleteComment(TvBinhLuanBv comment);
    
}
