/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vuongthai1205
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentResponseDTO {
    private int id;
    private int idUser;
    private List<LinkImageDTO> images;
    private String username;
    private String image;
    private String content;
}
