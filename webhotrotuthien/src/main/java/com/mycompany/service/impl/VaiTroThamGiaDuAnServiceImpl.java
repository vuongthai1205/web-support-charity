/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.mycompany.pojo.VaiTroThamGiaDa;
import com.mycompany.repository.VaiTroThamGiaDuAnRepository;
import com.mycompany.service.VaiTroThamGiaDuAnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuongthai1205
 */
@Service
public class VaiTroThamGiaDuAnServiceImpl implements VaiTroThamGiaDuAnService{
    @Autowired
    private VaiTroThamGiaDuAnRepository vaiTroThamGiaDuAnRepository;

    @Override
    public VaiTroThamGiaDa vaiTroThamGiaDa(int id) {
        return this.vaiTroThamGiaDuAnRepository.vaiTroThamGiaDa(id);
    }
    
}
