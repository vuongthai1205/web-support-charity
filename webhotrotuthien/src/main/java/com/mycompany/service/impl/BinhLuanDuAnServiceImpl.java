/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.repository.BinhLuanDuAnRepository;
import com.mycompany.service.BinhLuanDuAnService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class BinhLuanDuAnServiceImpl implements BinhLuanDuAnService{
    
    @Autowired
    private BinhLuanDuAnRepository binhLuanDuAnRepository;
    @Override
    public boolean addComment(TvBinhLuanDa comment) {
        return this.binhLuanDuAnRepository.addComment(comment);
    }

    @Override
    public boolean checkUserComment(ThanhVien user, DuAnTuThien duAnTuThien) {
        return this.binhLuanDuAnRepository.checkUserComment(user, duAnTuThien);
    }

    @Override
    public TvBinhLuanDa getCommentPost(ThanhVien user, DuAnTuThien duAnTuThien) {
        return this.binhLuanDuAnRepository.getCommentPost(user, duAnTuThien);
    }

    @Override
    public List<TvBinhLuanDa> listCommentPost(DuAnTuThien duAnTuThien) {
        return this.binhLuanDuAnRepository.listCommentPost(duAnTuThien);
    }

    @Override
    public TvBinhLuanDa getCommentById(int id) {
        return this.binhLuanDuAnRepository.getCommentById(id);
    }

    @Override
    public boolean deleteComment(TvBinhLuanDa comment) {
        return this.binhLuanDuAnRepository.deleteComment(comment);
    }

    @Override
    public List<TvBinhLuanDa> listCommentProjectPrivate(DuAnTuThien duAnTuThien) {
        return this.binhLuanDuAnRepository.listCommentProjectPrivate(duAnTuThien);
    }
    
}
