package com.felixlaura.binding;

import lombok.Data;
import java.util.Map;

@Data
public class CreateCseResponse {

    private Long caseNum;

    private Map<Integer,String> planNames;
}
