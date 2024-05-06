/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import java.util.List;

/**
 *
 * @author vuong
 */
public interface RedisService {
    void connectRedis();
    void saveObjectToRedis(Object object, String key);
    <T> T getObjectFromRedis(String key, Class<T> type);
    public <T> List<T> getListFromRedis(String key, Class<T> type);
    void flushAll();
}
