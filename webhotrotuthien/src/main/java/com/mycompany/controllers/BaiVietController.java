/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.DTO.AuctionResponseDTO;
import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DauGia;
import com.mycompany.service.BaiVietService;
import com.mycompany.service.DauGiaService;
import com.mycompany.service.TrangThaiDauGiaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vuongthai1205
 */
@Controller
public class BaiVietController {
    @Autowired
    private BaiVietService baiVietService;
    @Autowired
    private DauGiaService auctionService;
    
    @Autowired
    private TrangThaiDauGiaService trangThaiDauGiaService;
    
    @RequestMapping("/post")
    public String post(Model model, @RequestParam Map<String, String> params){
        
        
        model.addAttribute("posts", this.baiVietService.getPostList(params));
        return "post";
                
    }
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("auctionStatuses", this.trangThaiDauGiaService.getAuctionStatuses());
    }
    
    @GetMapping("/detail-post/{id}")
    public String detailPost(Model model, @PathVariable(value = "id") int id){
        BaiViet baiViet = this.baiVietService.getPostById(id);
         List<DauGia> auctions = this.auctionService.getListAuction(baiViet);
            List<AuctionResponseDTO> auctionResponseDTOs = new ArrayList<>();
            auctions.forEach(a -> {
                AuctionResponseDTO auctionResponseDTO = new AuctionResponseDTO();
                auctionResponseDTO.setUsername(a.getThanhVien().getTenDangNhap());
                auctionResponseDTO.setAvatar(a.getThanhVien().getAnhDaiDien());
                auctionResponseDTO.setPrice(a.getGiaTien());
                auctionResponseDTO.setWinnerAuctioned((a.getDaThangDauGia() == 1));
                auctionResponseDTO.setIdPost(a.getBaiViet().getMaBaiViet());
                auctionResponseDTO.setIdUser(a.getThanhVien().getMaThanhVien());
                auctionResponseDTOs.add(auctionResponseDTO);
            });
        model.addAttribute("post", baiViet);
        model.addAttribute("auctions", auctionResponseDTOs);
        return "detail-post";
    }
    @GetMapping("/delete-post/{id}")
    public String getDeletePost(Model model, @PathVariable(value = "id") int id){
        if(this.baiVietService.deletePost(id) == true)
            return "redirect:/post";
        return "detail-post";
    }
    @PostMapping("/detail-post")
    public String updatePost(@ModelAttribute(value = "post") @Valid BaiViet post, 
            BindingResult rs){
        if(!rs.hasErrors()){
            if(this.baiVietService.addOrUpdatePost(post) == true)
                return "redirect:/post";
        }
        return "detail-post";
    }
    
}
