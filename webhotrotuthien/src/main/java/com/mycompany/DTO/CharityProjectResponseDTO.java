/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.DTO;

import java.util.Date;
import java.util.List;
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
public class CharityProjectResponseDTO {
    private  int id;
    private String nameProject;
    private String purpose;
    private String address;
    private String startTime;
    private String endTime;
    private double amountRaised;
    private UserResponseDTO user;
    private List<ImagePostDTO> images;
    private List<CommentResponseDTO> listComment;
    private Date createAt;
    private Date updateAt;
}
