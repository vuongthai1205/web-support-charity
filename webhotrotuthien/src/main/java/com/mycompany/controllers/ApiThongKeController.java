/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.mycompany.service.ThongKeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vuongthai1205
 */
@RestController
@RequestMapping("/api/posts")
public class ApiThongKeController {
    @Autowired
    private ThongKeService statsService;
    @GetMapping("/stats/")
    public ResponseEntity<List<Object[]>>  getStats(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.statsService.stats(params), HttpStatus.OK);
    }
    @GetMapping("/stats-project/")
    public ResponseEntity<List<Object[]>>  getStatsProject(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.statsService.statsProject(params), HttpStatus.OK);
    }
}
