/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvThichBv;
import com.mycompany.repository.ThichRepository;
import com.mycompany.service.ThichService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class ThichServiceImpl implements ThichService{
    @Autowired
    private ThichRepository thichRepository;

    @Override
    public boolean addLike(TvThichBv likePost) {
        return this.thichRepository.addLike(likePost);
    }

    @Override
    public boolean checkUserLiked(ThanhVien user, BaiViet post) {
        return this.thichRepository.checkUserLiked(user, post);
    }

    @Override
    public TvThichBv getLikePost(ThanhVien user, BaiViet post) {
        return this.thichRepository.getLikePost(user, post);
    }

    @Override
    public List<TvThichBv> getLikePosts(BaiViet post) {
        return this.thichRepository.getLikePosts(post);
    }

    @Override
    public List<TvThichBv> getLikePostsByPost(BaiViet post) {
        return this.thichRepository.getLikePostsByPost(post);
    }

    @Override
    public boolean deleteLikePost(TvThichBv likePost) {
        return this.thichRepository.deleteLikePost(likePost);
    }

    @Override
    public boolean updateLike(TvThichBv likePost) {
        return this.thichRepository.updateLike(likePost);
    }
    
    
}
