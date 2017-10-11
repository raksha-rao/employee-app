package com.employee.model;

import java.util.Objects;
import java.util.function.Predicate;

public class EmployeePredicates {

    //Java 8 Predicates
    public static Predicate<String> isValidLength = (s) -> s.length() > 5;

    public static Predicate<Boolean> nonNull = Objects::nonNull;
    public static Predicate<Boolean> isNull = Objects::isNull;

    public static Predicate<String> isEmpty = String::isEmpty;
    public static Predicate<String> isNotEmpty = isEmpty.negate();


}
