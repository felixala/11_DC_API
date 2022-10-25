package com.felixlaura.rest;

import com.felixlaura.binding.CreateCseResponse;
import com.felixlaura.binding.PlanSelection;
import com.felixlaura.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

/**
 * This controller create a case.
 * createCase method is expecting an appId to search the case.
 * createCase method return a CreateCaseResponse Binding class that will receive PlanSelection screen.
 * createCase method needs to pass the fields caseNum and Map planNames to PlanSelection screen (see planSelection sreenshot).
 * See screenshot createCase.jpg for reference
 */

@RestController
public class CreateCaseRestController {

    @Autowired
    private DcService dcService;

    @GetMapping("/case/{appId}")
    public ResponseEntity<CreateCseResponse> creatCase(@PathVariable Integer appId){
        Long caseNum = dcService.loadCaseNum(appId);

        Map<Integer, String> planMap = dcService.getPlanNames();
        CreateCseResponse response = new CreateCseResponse();
        response.setCaseNum(caseNum);
        response.setPlanNames(planMap);

        return new ResponseEntity<CreateCseResponse>(response, HttpStatus.OK);


    }
}
