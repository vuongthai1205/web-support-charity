/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTO;

import com.googlecode.jmapper.annotations.JMap;
import com.mycompany.pojo.ThanhVien;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vuong
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFormDTO {
    private Double moneyMount;
    private String typeRequest;
}
