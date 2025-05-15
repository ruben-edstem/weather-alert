package com.edstem.weatherAlert.repository;

import com.edstem.weatherAlert.model.WeatherAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherAlertRepository extends JpaRepository<WeatherAlert, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM WeatherAlert w WHERE w.timestamp < :cutoff")
    void deleteOlderThan(@Param("cutoff") LocalDateTime cutoff);

    @Query("SELECT w FROM WeatherAlert w WHERE w.timestamp > :cutoff")
    List<WeatherAlert> getLatest(@Param("cutoff") LocalDateTime cutoff);
}