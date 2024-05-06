/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.pojo.Quyen;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.service.QuyenService;
import com.mycompany.service.ThanhVienService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vuongthai1205
 */
@Controller
public class ThanhVienController {

    @Autowired
    private ThanhVienService thanhVienService;
    @Autowired
    private QuyenService quyenService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ModelAttribute
    public void commAttr(Model model) {
        model.addAttribute("roles", this.quyenService.getListRoles());
    }

    @GetMapping("/user-manager")
    public String userManager(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.thanhVienService.getListUser(params));
        return "user-manager";
    }

    @GetMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new ThanhVien());
        return "add-user";
    }

    @GetMapping("/delete-user/{id}")
    public String getDeleteUser(Model model, @PathVariable(value = "id") int id) {
        if (this.thanhVienService.deleteUser(id) == true) {
            return "redirect:/user-manager";
        }
        return "detail-user";
    }

    @GetMapping("/detail-user/{id}")
    public String detailUser(Model model, @PathVariable(value = "id") int id) {
        ThanhVien user = this.thanhVienService.getUserById(id);
        model.addAttribute("user", user);
        return "detail-user";
    }

    @PostMapping("/add-user")
    public String submitAddUser(@ModelAttribute(value = "user") @Valid ThanhVien user, BindingResult rs, Model model) {

        if (!rs.hasErrors()) {
            Quyen role = this.quyenService.getRole(user.getRole().getMaQuyen());
            user.addRole(role);

            List<ThanhVien> users = this.thanhVienService.getUsers(user.getTenDangNhap());
            if (!users.isEmpty()) {
                
                model.addAttribute("error", "Username exists in database, please choose another username");
                return "add-user";
            }

            if (this.thanhVienService.addOrUpdateUser(user) == true) {
                return "redirect:/user-manager";
            }
        }
        return "add-user";
    }

    @PostMapping("/detail-user")
    public String updateUser(@ModelAttribute(value = "user") @Valid ThanhVien user, BindingResult rs) {
        if (!rs.hasErrors()) {
            Quyen role = this.quyenService.getRole(user.getRole().getMaQuyen());
            user.addRole(role);
            if (this.thanhVienService.addOrUpdateUser(user) == true) {
                return "redirect:/user-manager";
            }
        }
        return "detail-user";
    }

}
