package com.allen.learningbootcache.service;

/**
 * @author chan
 * @date 2020/10/23
 * description: TODO
 */
public interface LogServiceWithCaffeineCache {

    String get(String username);

    String del(String username);
}
