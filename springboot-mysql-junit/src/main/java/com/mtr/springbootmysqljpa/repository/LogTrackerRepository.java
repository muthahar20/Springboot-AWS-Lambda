package com.mtr.springbootmysqljpa.repository;

import com.mtr.springbootmysqljpa.model.LogTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogTrackerRepository extends JpaRepository<LogTracker, String> {
}
