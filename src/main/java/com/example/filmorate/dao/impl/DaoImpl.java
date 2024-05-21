package com.example.filmorate.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DaoImpl {

    private Integer entityCounter = 0;

    protected Integer getNextId() {
        return ++entityCounter;
    }

    protected String inSql(List<Integer> listOfId) {
        if (listOfId.isEmpty()) {
            return "NULL";
        }
        return listOfId.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
