/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.service.BaoCaoThanhVienService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author vuongthai1205
 */
@Controller
public class BaoCaoThanhVienController {
    @Autowired
    private BaoCaoThanhVienService baoCaoThanhVienService;
    @RequestMapping("/report")
    public String getReports(Model model) {
        List<BaoCaoThanhVien> baoCaoThanhViens  = this.baoCaoThanhVienService.getListReportUsers();
        model.addAttribute("reports", baoCaoThanhViens);
        return "report";
    }
}
