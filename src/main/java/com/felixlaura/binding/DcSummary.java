package com.felixlaura.binding;

import lombok.Data;

import java.util.List;

@Data
public class DcSummary {

    private Income income;

    private Education education;

    private List<Child> children;

    private String planName;
}
