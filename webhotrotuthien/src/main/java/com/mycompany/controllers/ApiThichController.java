/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvThichBv;
import com.mycompany.pojo.TvThichBvPK;
import com.mycompany.service.BaiVietService;
import com.mycompany.service.RedisService;
import com.mycompany.service.ThanhVienService;
import com.mycompany.service.ThichService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vuongthai1205
 */
@Controller
@CrossOrigin
@RequestMapping("/api")
public class ApiThichController {

    @Autowired
    private ThichService likeService;
    @Autowired
    private BaiVietService postService;
    @Autowired
    private ThanhVienService userService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/post/like/{id}/")
    public ResponseEntity<String> addLike(Principal user, @PathVariable(value = "id") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        BaiViet post = this.postService.getPostById(id);
        if (this.likeService.checkUserLiked(u, post) == true) {
            TvThichBv likePost = this.likeService.getLikePost(u, post);
            if (likePost.getDaThich() == 1) {
                likePost.setIsLike(false);
            } else {
                likePost.setIsLike(true);
            }
            if (this.likeService.updateLike(likePost) == true) {
                redisService.flushAll();
                return new ResponseEntity<>("Liked", HttpStatus.CREATED);
            }
        } else {
            TvThichBv likePost = new TvThichBv();
            TvThichBvPK pk = new TvThichBvPK();
            pk.setMaBaiViet(post.getMaBaiViet());
            pk.setMaThanhVien(u.getMaThanhVien());
            likePost.setTvThichBvPK(pk);
            likePost.setBaiViet(post);
            likePost.setThanhVien(u);
            likePost.setIsLike(true);
            if (this.likeService.addLike(likePost) == true) {
                redisService.flushAll();
                return new ResponseEntity<>("Liked", HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>("Don't like", HttpStatus.BAD_REQUEST);
    }
}
