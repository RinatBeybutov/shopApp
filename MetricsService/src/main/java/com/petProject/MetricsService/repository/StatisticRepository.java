package com.petProject.MetricsService.repository;

import com.petProject.MetricsService.entity.StatisticEntity;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StatisticRepository {
    private static final String KEY = "Stat";

    private final RedissonClient redissonClient;

    public void save(StatisticEntity statisticEntity) {
        redissonClient.getMap(KEY).put(statisticEntity.getTime(), statisticEntity);
    }

    public int getCountEntities(){
        return redissonClient.getMap(KEY).size();
    }

    public RMap<Object, Object> getAllStatistics() {
        return redissonClient.getMap(KEY);
    }

    public void printAll() {
        RMap<Object, Object> map = redissonClient.getMap(KEY);
        System.out.println("-----------------------------------");
        for (Object key : map.keySet()) {
            StatisticEntity s = (StatisticEntity) map.get(key);
            System.out.println(key);
            System.out.println(s);
            System.out.println("----------");
        }
        System.out.println("-----------------------------------");
    }
}
