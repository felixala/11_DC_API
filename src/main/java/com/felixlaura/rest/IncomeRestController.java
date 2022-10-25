package com.felixlaura.rest;

import com.felixlaura.binding.Income;
import com.felixlaura.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeRestController {

    @Autowired
    private DcService dcService;

    @PostMapping("/income")
    public ResponseEntity<Long> saveIncome(@RequestBody Income income){
        Long caseNum = dcService.saveIncomeData(income);
        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
