/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.HinhAnhBaiViet;
import com.mycompany.pojo.TvThichBv;
import com.mycompany.repository.BaiVietRepository;
import com.mycompany.repository.HinhAnhBaiVietRepository;
import com.mycompany.repository.ThichRepository;
import com.mycompany.service.BaiVietService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vuongthai1205
 */
@Service
public class BaiVietServiceImpl implements BaiVietService {

    @Autowired
    private BaiVietRepository baiVietRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private HinhAnhBaiVietRepository hinhAnhBaiVietRepository;
    
    @Autowired
    private ThichRepository thichRepository;

    @Override
    public List<BaiViet> getPostList(Map<String, String> params) {
        return this.baiVietRepository.getPostList(params);
    }

    @Override
    @Cacheable("baiviet")
    public BaiViet getPostById(int id) {
        return baiVietRepository.getPostById(id);
    }

    @Override
    public boolean addOrUpdatePost(BaiViet post) {
        
        if (post.getFile() != null && post.getFile()[0].getSize() > 0) {
            List<HinhAnhBaiViet> list = this.hinhAnhBaiVietRepository.listHinhAnh(post);
            list.forEach(i -> {
                this.hinhAnhBaiVietRepository.deleteImage(i);
            });
            for (MultipartFile file : post.getFile()) {
                // Lưu tệp tin, xử lý nó, hoặc thực hiện các thao tác khác
                if (!file.isEmpty()) {
                    // Lưu tệp tin
                    byte[] bytes;
                    try {
                        bytes = file.getBytes();
                        Map res = this.cloudinary.uploader().upload(bytes, ObjectUtils.asMap("resource_type", "auto"));
                        HinhAnhBaiViet anhBaiViet = new HinhAnhBaiViet();
                        anhBaiViet.setBaiViet(post);
                        anhBaiViet.setDuongDanHinh(res.get("secure_url").toString());
                        this.hinhAnhBaiVietRepository.addImageForPost(anhBaiViet);
                    } catch (IOException ex) {
                        // Handle the exception appropriately, e.g., throw a custom exception or return an error response
                        Logger.getLogger(BaiVietService.class.getName()).log(Level.SEVERE, "Error uploading image to Cloudinary", ex);
                    }
                }
            }

        }
        

        return this.baiVietRepository.addOrUpdatePost(post);
    }

    @Override
    public boolean deletePost(int id) {
        BaiViet post = this.baiVietRepository.getPostById(id);
        List<TvThichBv> thichBvs = this.thichRepository.getLikePostsByPost(post);
        thichBvs.forEach(t -> {
            this.thichRepository.deleteLikePost(t);
        });
        return this.baiVietRepository.deletePost(id);
    }

    @Override
    public int countPost() {
        return this.baiVietRepository.countPost();
    }

}
