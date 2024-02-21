package com.lec.spring.repository;

import com.lec.spring.domain.Medicine;

import java.util.List;

public interface MedicineRepository {
    int countAll();

    List<Medicine> selectFromRow(int from, Integer rows);
}
