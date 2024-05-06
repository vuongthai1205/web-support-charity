/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.service.ThongKeService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author vuongthai1205
 */

@Controller
public class IndexController {
    @Autowired  
    private ThongKeService statsService;
    
    @RequestMapping(value = "/")
    public String index(Model model){
        return "index";
    }
    
    @RequestMapping(value = "/stats")
    public String stats(Model model, @RequestParam Map<String, String> params) throws JsonProcessingException {
        List<Object[]> statsData = statsService.stats(params);
        ObjectMapper objectMapper = new ObjectMapper();
        String statsDataJson = objectMapper.writeValueAsString(statsData);
        model.addAttribute("statsData", statsDataJson);
        return "stats";
    }
    
    @RequestMapping(value = "/stats-project")
    public String statsProject(Model model, @RequestParam Map<String, String> params) throws JsonProcessingException {
        List<Object[]> statsData = statsService.statsProject(params);
        ObjectMapper objectMapper = new ObjectMapper();
        String statsDataJson = objectMapper.writeValueAsString(statsData);
        model.addAttribute("statsData", statsDataJson);
        return "stats-project";
    }
    
}
