package com.petProject.MetricsService.utility;

import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.StatisticRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisDBFiller {

    private final StatisticRepository statisticRepository;

    @PostConstruct
    private void init() {
        if (statisticRepository.getCountEntities() == 0) {
            statisticRepository.save(new StatisticEntity("19.00", 10));
            statisticRepository.save(new StatisticEntity("20.00", 5));
            statisticRepository.save(new StatisticEntity("21.00", 3));
        }

        statisticRepository.printAll();
    }
}
