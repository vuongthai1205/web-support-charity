/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanBv;
import com.mycompany.repository.BinhLuanRepository;
import com.mycompany.service.BinhLuanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class BinhLuanServiceImpl implements BinhLuanService{
    @Autowired
    private BinhLuanRepository binhLuanRepository;

    @Override
    public boolean addComment(TvBinhLuanBv comment) {
        return this.binhLuanRepository.addComment(comment);
    }

    @Override
    public boolean checkUserComment(ThanhVien user, BaiViet post) {
        return this.binhLuanRepository.checkUserComment(user, post);
    }

    @Override
    public TvBinhLuanBv getCommentPost(ThanhVien user, BaiViet post) {
        return this.binhLuanRepository.getCommentPost(user, post);
    }

    @Override
    public List<TvBinhLuanBv> listCommentPost(BaiViet post) {
        return this.binhLuanRepository.listCommentPost(post);
    }

    @Override
    public TvBinhLuanBv getCommentById(int id) {
        return this.binhLuanRepository.getCommentById(id);
    }

    @Override
    public boolean deleteComment(TvBinhLuanBv comment) {
        return this.binhLuanRepository.deleteComment(comment);
    }

    @Override
    public boolean editComment(TvBinhLuanBv cmt, String newCmt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
