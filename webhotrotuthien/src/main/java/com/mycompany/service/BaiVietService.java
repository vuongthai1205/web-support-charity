/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.pojo.BaiViet;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vuongthai1205
 */
public interface BaiVietService {
    List<BaiViet> getPostList(Map<String, String> params);
    BaiViet getPostById(int id);
    boolean addOrUpdatePost(BaiViet post);
    boolean deletePost(int id);
    int countPost();
}
