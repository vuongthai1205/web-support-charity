/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.Quyen;
import com.mycompany.pojo.ThanhVien;
import java.util.List;

/**
 *
 * @author vuongthai1205
 */
public interface QuyenService {
     Quyen getRole(int id);
    List<Quyen> getListRoles();
    List<Quyen> getListRolesByUser(ThanhVien user);
    
}
