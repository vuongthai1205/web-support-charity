/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.DTO.AuctionRequestDTO;
import com.mycompany.DTO.AuctionResponseDTO;
import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DauGia;
import com.mycompany.pojo.DauGiaPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.service.BaiVietService;
import com.mycompany.service.DauGiaService;
import com.mycompany.service.EmailService;
import com.mycompany.service.ThanhVienService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuongthai1205
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiDauGiaController {
    @Autowired
    private DauGiaService auctionService;
    @Autowired
    private BaiVietService postService;
    @Autowired
    private ThanhVienService userService;
    @Autowired
    private EmailService emailService;
    @GetMapping("/test-email/")
    public String testEmail() {
        this.emailService.sendSimpleMessage("giavuong.1205@gmail.com", "test", "test");
        return "thanh cong";
    }
    
    
    @PostMapping("/auction/")
    public ResponseEntity<String> addAuction(Principal user, @RequestBody AuctionRequestDTO auctionRequestDTO) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        BaiViet post = this.postService.getPostById(auctionRequestDTO.getIdPost());

        if (this.auctionService.checkAuctionExist(u, post)) {
            return new ResponseEntity<>("Ban da dau gia bai viet", HttpStatus.CONFLICT);
        }
        if (auctionRequestDTO.getPrice() < post.getGiaKhoiDiem()) {
            return new ResponseEntity<>("Vui lòng cho giá cao hon giá khoi diem", HttpStatus.BAD_REQUEST);
        } else {
            DauGiaPK dauGiaPK = new DauGiaPK(u.getMaThanhVien(), post.getMaBaiViet());
            DauGia auction = new DauGia();
            auction.setDauGiaPK(dauGiaPK);
            auction.setThanhVien(u);
            auction.setBaiViet(post);
            auction.setGiaTien(auctionRequestDTO.getPrice());
            if (this.auctionService.addAuction(auction)) {

                return new ResponseEntity<>("Thành Công", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Không thành công ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/auction/")
    public ResponseEntity<?> updateAuction(Principal user, @RequestBody AuctionRequestDTO auctionRequestDTO) {
        try {
            ThanhVien u = this.userService.getUserByUsername(user.getName());
            
            
            DauGia auction = this.auctionService.getAuctionById(auctionRequestDTO.getIdUser(),auctionRequestDTO.getIdPost());
            BaiViet post = this.postService.getPostById(auction.getBaiViet().getMaBaiViet());
            if (!post.getMaThanhVien().equals(u)) {
                return new ResponseEntity<>("you do not have access", HttpStatus.BAD_REQUEST);
            }
            // Thực hiện cập nhật lại danh sách phiên đấu giá
            if (this.auctionService.updateAuction(auction)) {
                List<DauGia> auctions = this.auctionService.getListAuction(post);

                for (DauGia a : auctions) {

                    if (!a.isWinnerAuction()) {
                        this.emailService.sendSimpleMessage(a.getThanhVien().getEmail(), "Charity Auction Result Notification", "Hello " + a.getThanhVien().getTen()
                                + "\n\nWe are pleased to inform you about the results of the charity auction on our social network."
                                + "\nPost: " + a.getBaiViet().getTieuDe()
                                + "\nPost owner: " + post.getMaThanhVien().getTen()
                                + "\nYou lose");
                    } else {
                        this.emailService.sendSimpleMessage(a.getThanhVien().getEmail(), "Charity Auction Result Notification", "Hello " + a.getThanhVien().getTen()
                                + "\n\nWe are pleased to inform you about the results of the charity auction on our social network."
                                + "\nPost: " + a.getBaiViet().getTieuDe()
                                + "\nPost owner: " + post.getMaThanhVien().getTen()
                                + "\nContact info:\n" + "Phone: " + post.getMaThanhVien().getSoDienThoai()
                                + "\nEmail: " + post.getMaThanhVien().getEmail()
                                + "\nYou win");
                    }

                }

                return new ResponseEntity<>("Thành Công", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Không thành công ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Xử lý các ngoại lệ nếu có
            return new ResponseEntity<>("Lỗi trong quá trình cập nhật: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/auction/{id-post}/")
    public ResponseEntity<?> getAuctions(Principal user, @PathVariable(value = "id-post") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        BaiViet post = this.postService.getPostById(id);
        if (post.getMaThanhVien().equals(u)) {
            List<DauGia> auctions = this.auctionService.getListAuction(post);
            List<AuctionResponseDTO> auctionResponseDTOs = new ArrayList<>();
            auctions.forEach(a -> {
                AuctionResponseDTO auctionResponseDTO = new AuctionResponseDTO();
                auctionResponseDTO.setUsername(a.getThanhVien().getTenDangNhap());
                auctionResponseDTO.setAvatar(a.getThanhVien().getAnhDaiDien());
                auctionResponseDTO.setPrice(a.getGiaTien());
                auctionResponseDTO.setWinnerAuctioned((a.getDaThangDauGia()== 1));
                auctionResponseDTO.setIdPost(a.getBaiViet().getMaBaiViet());
                auctionResponseDTO.setIdUser(a.getThanhVien().getMaThanhVien());
                auctionResponseDTOs.add(auctionResponseDTO);
            });

            return new ResponseEntity<>(auctionResponseDTOs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ban khong phai chu so huu bai viet", HttpStatus.BAD_REQUEST);
        }

    }
}
