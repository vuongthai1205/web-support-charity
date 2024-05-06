/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.DTO.ReportUserRequestDTO;
import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.pojo.BaoCaoThanhVienPK;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.service.BaoCaoThanhVienService;
import com.mycompany.service.ThanhVienService;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuongthai1205
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiBaoCaoThanhVienController {
    
    @Autowired
    private BaoCaoThanhVienService reportuserService;

    @Autowired
    private ThanhVienService userService;
    
    @PostMapping("/report/")
    public ResponseEntity<Object> addReport(@RequestBody ReportUserRequestDTO reportUserResquestDTO, Principal user) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        ThanhVien uReport = this.userService.getUserById(reportUserResquestDTO.getIdUser());

        BaoCaoThanhVien reportUser = new BaoCaoThanhVien();
        reportUser.setThanhVien(u);
        reportUser.setThanhVien1(uReport);
        BaoCaoThanhVienPK baoCaoThanhVienPK = new BaoCaoThanhVienPK(u.getMaThanhVien(), uReport.getMaThanhVien(), reportUserResquestDTO.getReportReason());
        reportUser.setBaoCaoThanhVienPK(baoCaoThanhVienPK);
        if (this.reportuserService.addReport(reportUser)) {
            return new ResponseEntity<>("report user successfully", HttpStatus.OK);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Thêm không thành công");
            errorResponse.put("errorCode", "12345");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }
}
