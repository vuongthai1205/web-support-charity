/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fomatter;

import com.mycompany.pojo.Quyen;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author vuongthai1205
 */
public class RoleFormatter implements Formatter<Quyen>{
    @Override
    public String print(Quyen role, Locale locale) {
        return String.valueOf(role.getMaQuyen());
    }

    @Override
    public Quyen parse(String text, Locale locale) throws ParseException {
        int id = Integer.parseInt(text);
        return new Quyen(id);
    }
}
