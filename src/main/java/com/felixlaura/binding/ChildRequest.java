package com.felixlaura.binding;

import lombok.Data;

import java.util.List;

/**
 * This binding class represent the UI "Kids Details".
 * It contains a caseNum and a List of Child Binding Class.
 *
 */

@Data
public class ChildRequest {

    private Long caseNum;

    private List<Child> childs;
}
