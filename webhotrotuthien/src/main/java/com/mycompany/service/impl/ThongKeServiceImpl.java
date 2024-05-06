/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.repository.ThongKeRepository;
import com.mycompany.service.ThongKeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class ThongKeServiceImpl implements ThongKeService {
    @Autowired
    private ThongKeRepository thongKeRepository;
    @Override
    public List<Object[]> stats(Map<String, String> params) {
        return this.thongKeRepository.stats(params);
    }

    @Override
    public List<Object[]> statsProject(Map<String, String> params) {
        return this.thongKeRepository.statsProject(params);
    }
    
}
