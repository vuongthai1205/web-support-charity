/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fomatter;

import com.mycompany.pojo.TrangThaiDauGia;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author vuongthai1205
 */
public class AuctionStatusFormatter implements Formatter<TrangThaiDauGia>{

    @Override
    public String print(TrangThaiDauGia auctionStatus, Locale locale) {
        return String.valueOf(auctionStatus.getMaTrangThaiDauGia());
    }

    @Override
    public TrangThaiDauGia parse(String text, Locale locale) throws ParseException {
        int id = Integer.parseInt(text);
        return new TrangThaiDauGia(id);
    }
    
}
