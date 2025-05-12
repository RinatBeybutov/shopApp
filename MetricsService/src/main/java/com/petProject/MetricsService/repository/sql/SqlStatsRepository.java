package com.petProject.MetricsService.repository.sql;

import com.petProject.MetricsService.entity.sql.SqlStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlStatsRepository extends JpaRepository<SqlStatsEntity, Long> {
}
