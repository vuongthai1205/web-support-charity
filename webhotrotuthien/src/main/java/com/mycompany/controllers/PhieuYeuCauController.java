/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.pojo.BaoCaoThanhVien;
import com.mycompany.pojo.DuAnTuThien;
import com.mycompany.pojo.PhieuYeuCau;
import com.mycompany.service.PhieuYeuCauServices;
import java.util.List;
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

/**
 *
 * @author vuong
 */
@Controller
public class PhieuYeuCauController {
    @Autowired
    private PhieuYeuCauServices phieuYeuCauServices;
    @RequestMapping("/request-form")
    public String getRequestForm(Model model) {
        List<PhieuYeuCau> phieuyeucaus  = this.phieuYeuCauServices.getRequestFormList();
        model.addAttribute("requestForms", phieuyeucaus);
        return "request-form";
    }
    
    @GetMapping("/request-form/{id}")
    public String getDetailRequestForm(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("requestForm", this.phieuYeuCauServices.getRequestFormId(id));
        return "detail-request-form";
    }
    
    @GetMapping("/delete-request-form/{id}")
    public String deleteCharityProject(Model model, @PathVariable(value = "id") int id){
        if (this.phieuYeuCauServices.deleteRequestForm(id)) {
            return "redirect:/request-form";
        }
        return "detail-request-form";
    }
    
    @PostMapping("/update-request-form")
    public String updateProject(@ModelAttribute(value = "requestForm") @Valid PhieuYeuCau requestForm, 
            BindingResult rs){
        if(!rs.hasErrors()){
            
            if(this.phieuYeuCauServices.addOrUpdateRequestForm(requestForm) == true)
                return "redirect:/request-form";
        }
        return "detail-request-form";
    }
    
    
}
