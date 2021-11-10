package com.customlog.customlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLogRepository extends JpaRepository<CustomLog, Integer>{
	
}