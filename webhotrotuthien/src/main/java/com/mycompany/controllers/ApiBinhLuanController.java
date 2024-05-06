/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.DTO.CommentRequestDTO;
import com.mycompany.DTO.CommentResponseDTO;
import com.mycompany.DTO.LinkImageDTO;
import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhBinhLuanDa;
import com.mycompany.pojo.ThamGiaDuAn;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.pojo.TvBinhLuanBv;
import com.mycompany.pojo.TvBinhLuanDa;
import com.mycompany.service.BaiVietService;
import com.mycompany.service.BinhLuanDuAnService;
import com.mycompany.service.BinhLuanService;
import com.mycompany.service.DuAnTuThienService;
import com.mycompany.service.HinhAnhBinhLuanDuAnService;
import com.mycompany.service.RedisService;
import com.mycompany.service.ThamGiaDuAnService;
import com.mycompany.service.ThanhVienService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vuongthai1205
 */
@Controller
@CrossOrigin
@RequestMapping("/api")
public class ApiBinhLuanController {

    @Autowired
    private BinhLuanService commentService;
    @Autowired
    private BaiVietService postService;
    @Autowired
    private ThanhVienService userService;
    @Autowired
    private BinhLuanDuAnService binhLuanService;
    @Autowired
    private DuAnTuThienService duAnTuThienService;
    @Autowired
    private HinhAnhBinhLuanDuAnService hinhAnhBinhLuanDuAnService;
    @Autowired
    private ThamGiaDuAnService thamGiaDuAnService;
    @Autowired
    private RedisService redisService;

    @PostMapping("/post/comment/{id-post}/")
    public ResponseEntity<String> addComment(Principal user, @PathVariable(value = "id-post") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        BaiViet post = this.postService.getPostById(id);

        TvBinhLuanBv newComment = new TvBinhLuanBv();
        newComment.setBaiViet(post);
        newComment.setThanhVien(u);
        newComment.setNoiDung(commentRequestDTO.getContent());
        if (this.commentService.addComment(newComment)) {
            redisService.flushAll();
            return new ResponseEntity<>("Cmt", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/project/comment/{id-project}/")
    public ResponseEntity<String> addCommentProject(Principal user, @PathVariable(value = "id-project") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        DuAnTuThien project = this.duAnTuThienService.getDuAnTuThienById(id);

        TvBinhLuanDa newComment = new TvBinhLuanDa();
        List<HinhAnhBinhLuanDa> hinhAnhBinhLuan = new ArrayList<>();

        if (commentRequestDTO.getImages() != null) {
            for (LinkImageDTO linkImageDTO : commentRequestDTO.getImages()) {
                HinhAnhBinhLuanDa hinhAnhBinhLuanDa = new HinhAnhBinhLuanDa();
                hinhAnhBinhLuanDa.setDuongDanHinh(linkImageDTO.getLink());
                hinhAnhBinhLuanDa.setMaBinhLuan(newComment);
                hinhAnhBinhLuan.add(hinhAnhBinhLuanDa);
            }
        }

        newComment.setHinhAnhBinhLuanDaList(hinhAnhBinhLuan);
        newComment.setDuAnTuThien(project);
        newComment.setThanhVien(u);
        newComment.setNoiDung(commentRequestDTO.getContent());
        if (this.binhLuanService.addComment(newComment)) {
            redisService.flushAll();
            return new ResponseEntity<>("Cmt", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/project/comment-private/{id-project}/")
    public ResponseEntity<List<CommentResponseDTO>> getCommentProjectPrivate(Principal user, @PathVariable(value = "id-project") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        DuAnTuThien project = this.duAnTuThienService.getDuAnTuThienById(id);
        ThamGiaDuAn thamgia = thamGiaDuAnService.getThamGiaDuAn(u, project);
        if (project.getMaThanhVienTaoDA().equals(u) || thamgia != null) {
            List<TvBinhLuanDa> listComments = this.binhLuanService.listCommentProjectPrivate(project);
            List<CommentResponseDTO> listCommentDTOs = new ArrayList<>();
            listComments.forEach(cmt -> {
                List<LinkImageDTO> imagesComment = new ArrayList<>();
                List<HinhAnhBinhLuanDa> hinhAnhBinhLuan = hinhAnhBinhLuanDuAnService.listHinhAnh(cmt);
                hinhAnhBinhLuan.forEach(h -> {
                    LinkImageDTO hinhAnh = new LinkImageDTO();
                    hinhAnh.setLink(h.getDuongDanHinh());
                    imagesComment.add(hinhAnh);
                });
                CommentResponseDTO commentDTO = new CommentResponseDTO();
                commentDTO.setId(cmt.getMaBinhLuan());
                commentDTO.setImage(cmt.getThanhVien().getAnhDaiDien());
                commentDTO.setUsername(cmt.getThanhVien().getTenDangNhap());
                commentDTO.setContent(cmt.getNoiDung());
                commentDTO.setIdUser(cmt.getThanhVien().getMaThanhVien());
                commentDTO.setImages(imagesComment);
                listCommentDTOs.add(commentDTO);
            });
            return new ResponseEntity<>(listCommentDTOs, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/project/comment-private/{id-project}/")
    public ResponseEntity<String> addCommentProjectPrivate(Principal user, @PathVariable(value = "id-project") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        DuAnTuThien project = this.duAnTuThienService.getDuAnTuThienById(id);

        TvBinhLuanDa newComment = new TvBinhLuanDa();
        List<HinhAnhBinhLuanDa> hinhAnhBinhLuan = new ArrayList<>();

        if (commentRequestDTO.getImages() != null) {
            for (LinkImageDTO linkImageDTO : commentRequestDTO.getImages()) {
                HinhAnhBinhLuanDa hinhAnhBinhLuanDa = new HinhAnhBinhLuanDa();
                hinhAnhBinhLuanDa.setDuongDanHinh(linkImageDTO.getLink());
                hinhAnhBinhLuanDa.setMaBinhLuan(newComment);
                hinhAnhBinhLuan.add(hinhAnhBinhLuanDa);
            }
        }

        newComment.setHinhAnhBinhLuanDaList(hinhAnhBinhLuan);
        newComment.setDuAnTuThien(project);
        newComment.setThanhVien(u);
        newComment.setNoiDung(commentRequestDTO.getContent());
        newComment.setTrangThai(1);
        if (this.binhLuanService.addComment(newComment)) {
            redisService.flushAll();
            return new ResponseEntity<>("Cmt", HttpStatus.CREATED);
        }

        return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/post/comment/{id}/")
    public ResponseEntity<String> updateComment(Principal user, @PathVariable(value = "id") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        TvBinhLuanBv comment = this.commentService.getCommentById(id);

        if (comment.getThanhVien().equals(u)) {
            comment.setNoiDung(commentRequestDTO.getContent());
            if (this.commentService.addComment(comment)) {
                redisService.flushAll();
                return new ResponseEntity<>("comment thanh cong", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("k có quyen sua comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/project/comment/{id}/")
    public ResponseEntity<String> updateCommentProject(Principal user, @PathVariable(value = "id") int id, @RequestBody CommentRequestDTO commentRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        TvBinhLuanDa comment = this.binhLuanService.getCommentById(id);

        if (comment.getThanhVien().equals(u)) {
            comment.setNoiDung(commentRequestDTO.getContent());
            if (this.binhLuanService.addComment(comment)) {
                redisService.flushAll();
                return new ResponseEntity<>("comment thanh cong", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("k có quyen sua comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post/comment/{id}/")
    public ResponseEntity<String> deleteComment(Principal user, @PathVariable(value = "id") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        TvBinhLuanBv comment = this.commentService.getCommentById(id);

        if (comment.getThanhVien().equals(u)) {
            if (this.commentService.deleteComment(comment)) {
                redisService.flushAll();
                return new ResponseEntity<>("xóa comment thanh cong", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("k có quyen xóa comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/project/comment/{id}/")
    public ResponseEntity<String> deleteCommentProject(Principal user, @PathVariable(value = "id") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        TvBinhLuanDa comment = this.binhLuanService.getCommentById(id);

        if (comment.getThanhVien().equals(u)) {
            if (this.binhLuanService.deleteComment(comment)) {
                redisService.flushAll();
                return new ResponseEntity<>("xóa comment thanh cong", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("comment loi", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("k có quyen xóa comment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
