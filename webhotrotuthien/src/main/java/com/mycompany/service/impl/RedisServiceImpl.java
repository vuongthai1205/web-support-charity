/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.service.RedisService;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Service;

/**
 *
 * @author vuong
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final ObjectMapper objectMapper;

    public RedisServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void connectRedis() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");

        try ( StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = connection.async();

            asyncCommands.set("foo", "bar").get();
            System.out.println(asyncCommands.get("foo").get()); // prints bar

            Map<String, String> hash = new HashMap<>();
            hash.put("name", "John");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "29");
            asyncCommands.hset("user-session:123", hash).get();

            System.out.println(asyncCommands.hgetall("user-session:123").get());

            // Kiểm tra xem key "foo" có tồn tại không
            System.out.println("Key 'foo' tồn tại trong Redis: " + asyncCommands.exists("foo").get());

            // Kiểm tra xem key "user-session:123" có tồn tại không
            System.out.println("Key 'user-session:123' tồn tại trong Redis: " + asyncCommands.exists("user-session:123").get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            redisClient.shutdown();
        }

    }

    @Override
    public void saveObjectToRedis(Object object, String key) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");

        try ( StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = connection.async();

            // Chuyển đối tượng thành chuỗi JSON
            String json;
            try {
                json = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to serialize object to JSON", e);
            }

            // Lưu chuỗi JSON vào Redis với key được chỉ định
            asyncCommands.set(key, json).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException("Failed to save object to Redis", e);
        } finally {
            redisClient.shutdown();
        }
    }

    @Override
    public <T> T getObjectFromRedis(String key, Class<T> type) {
         RedisClient redisClient = RedisClient.create("redis://localhost:6379");

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = connection.async();

            // Lấy chuỗi JSON từ Redis bằng key
            String json = asyncCommands.get(key).get();

            if (json == null) {
                return null; // Trả về null nếu không tìm thấy đối tượng với key đã cho
            }

            // Chuyển chuỗi JSON thành đối tượng của kiểu được chỉ định
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to get object from Redis", e);
        } finally {
            redisClient.shutdown();
        }
    }

    @Override
    public <T> List<T> getListFromRedis(String key, Class<T> type) {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = connection.async();

            // Lấy chuỗi JSON từ Redis bằng key
            String json = asyncCommands.get(key).get();

            if (json == null) {
                return null; // Trả về null nếu không tìm thấy danh sách với key đã cho
            }

            // Sử dụng Jackson để chuyển chuỗi JSON thành danh sách các đối tượng của kiểu được chỉ định
            return objectMapper.readValue(json, new TypeReference<List<T>>() {});
        } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Failed to get list from Redis", e);
        } finally {
            redisClient.shutdown();
        }
    }

    @Override
    public void flushAll() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379");

        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisAsyncCommands<String, String> asyncCommands = connection.async();
            asyncCommands.flushall();
            
        } finally {
            redisClient.shutdown();
        }
    }
}
