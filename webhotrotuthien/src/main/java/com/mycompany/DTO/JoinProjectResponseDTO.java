/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vuongthai1205
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinProjectResponseDTO {
    UserResponseDTO user;
    CharityProjectResponseDTO project;
    private double contributionAmount;
    private String contributionOther;
    private String role;
    private int roleId;
    private Date ngayThamGia;
}
