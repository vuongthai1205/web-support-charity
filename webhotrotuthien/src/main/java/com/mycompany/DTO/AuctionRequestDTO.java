/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTO;

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
public class AuctionRequestDTO {
    private int idPost;
    private double price;
    private int idUser;
    
}
