package com.felixlaura.rest;

import com.felixlaura.binding.Education;
import com.felixlaura.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EducationRestController {

    @Autowired
    private DcService dcService;

    @PostMapping("/education")
    public ResponseEntity<Long> saveEducation(@RequestBody Education education){

        Long caseNum = dcService.saveEducation(education);

        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
