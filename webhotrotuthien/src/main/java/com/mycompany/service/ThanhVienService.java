/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.ThanhVien;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author vuongthai1205
 */
public interface ThanhVienService extends UserDetailsService {
    List<ThanhVien> getUsers(String name);
    boolean addOrUpdateUser(ThanhVien user);
    List<ThanhVien> getListUser(Map<String, String> params);
    ThanhVien getUserById(int id );
    boolean deleteUser(int id);
    ThanhVien getUserByUsername(String username );
    int checkUser(String username, String password);
    boolean authUser(String username, String password);
}
