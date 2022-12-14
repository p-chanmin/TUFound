package com.example.firstproject.repository;

import com.example.firstproject.entity.AcquiredBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcquiredBoardRepository extends JpaRepository<AcquiredBoard, Long> {
}
