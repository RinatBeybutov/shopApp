package com.petProject.MetricsService;

import com.petProject.MetricsService.entity.StatisticEntity;
import com.petProject.MetricsService.repository.StatisticRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DBFiller {

    private final StatisticRepository statisticRepository;

    public DBFiller(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @PostConstruct
    private void init() {
        if (statisticRepository.getCountEntities() == 0) {
            statisticRepository.addStat(new StatisticEntity("19.00", 10));
            statisticRepository.addStat(new StatisticEntity("20.00", 5));
            statisticRepository.addStat(new StatisticEntity("21.00", 3));
        }

        statisticRepository.printAll();
    }
}
