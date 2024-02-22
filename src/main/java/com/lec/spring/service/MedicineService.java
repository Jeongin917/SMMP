package com.lec.spring.service;

import com.lec.spring.domain.Medicine;
import org.springframework.ui.Model;

import java.util.List;

public interface MedicineService {

    List<Medicine> list(Integer page, Model model);

    List<Medicine> list();
}
