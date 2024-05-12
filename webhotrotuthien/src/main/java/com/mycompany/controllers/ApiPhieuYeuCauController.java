/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.googlecode.jmapper.JMapper;
import com.mycompany.DTO.RequestFormDTO;
import com.mycompany.pojo.PhieuYeuCau;
import com.mycompany.pojo.ThanhVien;
import com.mycompany.service.PhieuYeuCauServices;
import com.mycompany.service.ThanhVienService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuong
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiPhieuYeuCauController {

    @Autowired
    private PhieuYeuCauServices phieuYeuCauServices;
    @Autowired
    private ThanhVienService userService;

    @PostMapping(path = "/requestForm/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRequestForm(@RequestBody RequestFormDTO requestFormDTO, Principal user) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        JMapper<PhieuYeuCau, RequestFormDTO> requestFormMapper = new JMapper<>(PhieuYeuCau.class, RequestFormDTO.class);
        PhieuYeuCau phieuYeuCau = requestFormMapper.getDestination(requestFormDTO);
        phieuYeuCau.setMaThanhVien(u);
        if(phieuYeuCau.getLoaiPhieu().compareTo("rut_tien") == 0 && phieuYeuCau.getSoTien() > u.getTongTien()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("requestFormDTO added or updated fail");
        }
        if (phieuYeuCauServices.addOrUpdateRequestForm(phieuYeuCau)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("requestFormDTO added or updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("requestFormDTO added or updated fail");
        }
    }

    @PutMapping(path = "/requestForm/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRequestForm(@RequestBody RequestFormDTO requestFormDTO, Principal user, @PathVariable(value = "id") int id) {
        ThanhVien u = this.userService.getUserByUsername(user.getName());
        PhieuYeuCau phieuYeuCau = phieuYeuCauServices.getRequestFormId(id);
        if (phieuYeuCau != null) {
            phieuYeuCau.setSoTien(requestFormDTO.getMoneyMount());
            phieuYeuCau.setMaThanhVien(u);
            if (phieuYeuCauServices.addOrUpdateRequestForm(phieuYeuCau)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("requestFormDTO added or updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("requestFormDTO added or updated fail");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("requestFormDTO added or updated fail");
        }

    }
}
