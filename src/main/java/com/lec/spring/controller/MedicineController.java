package com.lec.spring.controller;

import com.lec.spring.domain.Medicine;
import com.lec.spring.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/smmp")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/medicine")
    public void list(Integer page, Model model) {
        medicineService.list(page, model);
    }

    @GetMapping("/medicine/api")
    @ResponseBody
    public String MedicineInfo(Model model) {
        List<Medicine> list = medicineService.list();

        model.addAttribute("list", list);

        return "smmp/medicine";
    }


}
