/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.pojo.ThanhVien;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vuongthai1205
 */
public interface ThanhVienRepository {
    List<ThanhVien> getUsers(String name);
    void addOrUpdateUser(ThanhVien user);
    List<ThanhVien> getListUser(Map<String, String> params);
    ThanhVien getUserById(int id );
    boolean deleteUser(int id);
    ThanhVien getUserByUsername(String username );
    boolean authUser(String username, String password);
}
