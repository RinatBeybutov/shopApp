package com.petProject.MetricsService.repository.redis;

import com.petProject.MetricsService.entity.redis.RedisStatsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RedisStatsRepository {
    private static final String KEY = "Stat";

    private final RedissonClient redissonClient;

    public void save(RedisStatsEntity redisStatsEntity) {
        log.debug("Сохранение статистики {}", redisStatsEntity);
        redissonClient.getMap(KEY).put(redisStatsEntity.getTime(), redisStatsEntity);
    }

    public int getCountEntities(){
        return redissonClient.getMap(KEY).size();
    }

    public RMap<String, RedisStatsEntity> getAllStatistics() {
        return redissonClient.getMap(KEY);
    }

    public void printAll() {
        RMap<Object, Object> map = redissonClient.getMap(KEY);
        System.out.println("-----------------------------------");
        for (Object key : map.keySet()) {
            RedisStatsEntity s = (RedisStatsEntity) map.get(key);
            System.out.println(key);
            System.out.println(s);
            System.out.println("----------");
        }
        System.out.println("-----------------------------------");
    }

    public void clear() {
        log.debug("Очистка статистики");
        redissonClient.getMap(KEY).clear();
    }
}
