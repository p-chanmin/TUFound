package com.example.firstproject.repository;

import com.example.firstproject.entity.Lost;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface LostRepository extends CrudRepository<Lost,Long> {
    @Override
    ArrayList<Lost> findAll();

    long countBy();
}
