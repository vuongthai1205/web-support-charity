/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.pojo.BaiViet;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.HinhAnhDuAn;
import com.mycompany.service.DuAnTuThienService;
import com.mycompany.service.HinhAnhDuAnService;
import com.mycompany.service.RedisService;
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
public class DuAnTuThienController {
    @Autowired
    private DuAnTuThienService duAnTuThienService;
    @Autowired
    private RedisService redisService;
    
    @RequestMapping("/charityproject")
    public String getAll(Model model, @RequestParam Map<String, String> params){
        model.addAttribute("charityprojects", this.duAnTuThienService.getDuAnTuThiens(params));
        return "charityproject";
    }
    
    @GetMapping("/detail-charityproject/{id}")
    public String detailCharityProject(Model model, @PathVariable(value = "id") int id){
        model.addAttribute("charityproject", this.duAnTuThienService.getDuAnTuThienById(id));
        return "detail-charityproject";
    }
    
    @GetMapping("/delete-charityproject/{id}")
    public String deleteCharityProject(Model model, @PathVariable(value = "id") int id){
        DuAnTuThien duAnTuThien = this.duAnTuThienService.getDuAnTuThienById(id);
        if (this.duAnTuThienService.deleteProject(duAnTuThien)) {
            return "redirect:/charityproject";
        }
        return "detail-charityproject";
    }
    
    @PostMapping("/update-charityproject")
    public String updateProject(@ModelAttribute(value = "charityproject") @Valid DuAnTuThien project, 
            BindingResult rs){
        if(!rs.hasErrors()){
            
            if(this.duAnTuThienService.addOrUpdateCharityProject(project) == true){
                redisService.flushAll();
                return "redirect:/charityproject";
            }
                
        }
        return "detail-charityproject";
    }
}
