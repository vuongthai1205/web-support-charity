/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvThichBv;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface ThichRepository {
    
    boolean addLike(TvThichBv likePost);
    boolean checkUserLiked(ThanhVien user, BaiViet post);
    TvThichBv getLikePost(ThanhVien user, BaiViet post);
    List<TvThichBv> getLikePosts(BaiViet post);
    List<TvThichBv> getLikePostsByPost(BaiViet post);
    boolean deleteLikePost(TvThichBv likePost);
    boolean updateLike(TvThichBv likePost);
    
}
