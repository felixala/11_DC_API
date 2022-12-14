package com.felixlaura.rest;

import com.felixlaura.binding.ChildRequest;
import com.felixlaura.binding.DcSummary;
import com.felixlaura.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildRestController {

    @Autowired
    private DcService dcService;

    @PostMapping("/childrens")
    public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request){

        Long caseNum = dcService.saveChildren(request);

        DcSummary summary = dcService.getSummary(caseNum);

        return new ResponseEntity<>(summary, HttpStatus.OK);

    }
}
