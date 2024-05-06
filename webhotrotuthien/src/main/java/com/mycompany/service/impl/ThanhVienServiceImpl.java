/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.repository.ThanhVienRepository;
import com.mycompany.service.ThanhVienService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service("userDetailsService")
public class ThanhVienServiceImpl implements ThanhVienService {

    public static final SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public ThanhVien getUserByUsername(String username) {
        return this.thanhVienRepository.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<ThanhVien> users = thanhVienRepository.getUsers(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }
        ThanhVien u = users.get(0);
        Set<GrantedAuthority> authorities = new HashSet<>();

        u.getQuyenSet().forEach(item -> {
            authorities.add(new SimpleGrantedAuthority(item.getTenQuyen()));
        });

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public List<ThanhVien> getUsers(String name) {
        return this.thanhVienRepository.getUsers(name);
    }

    @Override
    public boolean addOrUpdateUser(ThanhVien user) {
        if (user.getMaThanhVien()== null) {
            String pass = user.getPassword();
            if (user.getFile() != null && !user.getFile().isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    user.setAnhDaiDien(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(ThanhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            user.setMatKhau(passwordEncoder.encode(pass));
            if (user.getDateString() != null) {
                try {
                    Date dateOfBirth = F.parse(user.getDateString());
                    user.setNgaySinh(dateOfBirth);
                } catch (ParseException ex) {
                    Logger.getLogger(ThanhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Lưu user vào cơ sở dữ liệu trước khi tạo userRole
            this.thanhVienRepository.addOrUpdateUser(user);
            // Lấy role từ repository hoặc cách nào bạn đã cấu hình
            return true;
        } else {
            if (!user.getFile().isEmpty()) {
                try {
                    Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                    user.setAnhDaiDien(res.get("secure_url").toString());
                } catch (IOException ex) {
                    Logger.getLogger(ThanhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (user.getDateString() != null) {
                try {
                    Date dateOfBirth = F.parse(user.getDateString());
                    user.setNgaySinh(dateOfBirth);
                } catch (ParseException ex) {
                    Logger.getLogger(ThanhVienServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (user.getFakePassword() != null) {
                user.setMatKhau(passwordEncoder.encode(user.getFakePassword()));
            }

            this.thanhVienRepository.addOrUpdateUser(user);
            return true;
        }
    }

    @Override
    public List<ThanhVien> getListUser(Map<String, String> params) {
        return this.thanhVienRepository.getListUser(params);
    }

    @Override
    public ThanhVien getUserById(int id) {
        return  this.thanhVienRepository.getUserById(id);
    }

    @Override
    public boolean deleteUser(int id) {
        return this.thanhVienRepository.deleteUser(id);
    }

    @Override
    public int checkUser(String username, String password) {
        List<ThanhVien> users = thanhVienRepository.getUsers(username);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }
        ThanhVien u = users.get(0);
        if (u != null) {
            if (passwordEncoder.matches(password, u.getPassword()) == true) {
                return 1;
            } else {
                return 2;
            }

        }
        return 0;
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.thanhVienRepository.authUser(username, password);
    }

}
