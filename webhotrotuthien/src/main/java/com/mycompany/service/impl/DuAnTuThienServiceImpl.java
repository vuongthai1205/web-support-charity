/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhDuAn;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.repository.BinhLuanDuAnRepository;
import com.mycompany.repository.DuAnTuThienRepository;
import com.mycompany.repository.HinhAnhDuAnRepository;
import com.mycompany.service.DuAnTuThienService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vuongthai1205
 */
@Service
public class DuAnTuThienServiceImpl implements DuAnTuThienService {

    @Autowired
    private DuAnTuThienRepository duAnTuThienRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private HinhAnhDuAnRepository hinhAnhDuAnRepository;
    
    @Autowired
    private BinhLuanDuAnRepository tvBinhLuanDaRepository;

    @Override
    public boolean addOrUpdateCharityProject(DuAnTuThien duAnTuThien) {

        if (duAnTuThien.getFile() != null && duAnTuThien.getFile()[0].getSize() > 0) {
            List<HinhAnhDuAn> hinhAnhDuAns = this.hinhAnhDuAnRepository.listHinhAnh(duAnTuThien);
                hinhAnhDuAns.forEach(i -> {
                    this.hinhAnhDuAnRepository.deleteImage(i);

                });
            for (MultipartFile file : duAnTuThien.getFile()) {
                
                // Lưu tệp tin, xử lý nó, hoặc thực hiện các thao tác khác
                if (!file.isEmpty()) {
                    // Lưu tệp tin
                    byte[] bytes;
                    try {
                        bytes = file.getBytes();
                        Map res = this.cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "auto"));
                        HinhAnhDuAn anhDuAn = new HinhAnhDuAn();
                        anhDuAn.setMaDuAn(duAnTuThien);
                        anhDuAn.setDuongDanHinh(res.get("secure_url").toString());
                        this.hinhAnhDuAnRepository.addImageForProject(anhDuAn);
                    } catch (IOException ex) {
                        // Handle the exception appropriately, e.g., throw a custom exception or return an error response
                        Logger.getLogger(DuAnTuThienService.class.getName()).log(Level.SEVERE, "Error uploading image to Cloudinary", ex);
                    }
                }
            }

        }
        return this.duAnTuThienRepository.addOrUpdateCharityProject(duAnTuThien);
    }

    @Override
    public List<DuAnTuThien> getDuAnTuThiens(Map<String, String> params) {
        return this.duAnTuThienRepository.getDuAnTuThiens(params);
    }

    @Override
    public DuAnTuThien getDuAnTuThienById(int id) {
        return this.duAnTuThienRepository.getDuAnTuThienById(id);
    }

    @Override
    public boolean deleteProject(DuAnTuThien duAnTuThien) {
        List<TvBinhLuanDa> binhLuanDas = this.tvBinhLuanDaRepository.listAllCommentPost(duAnTuThien);
        binhLuanDas.forEach(t -> {
            this.tvBinhLuanDaRepository.deleteComment(t);
        });
        return this.duAnTuThienRepository.deleteProject(duAnTuThien);
    }

    @Override
    public List<DuAnTuThien> getDuAnTuThiensWithApproved(Map<String, String> params) {
        return this.duAnTuThienRepository.getDuAnTuThiensWithApproved(params);
    }

}
