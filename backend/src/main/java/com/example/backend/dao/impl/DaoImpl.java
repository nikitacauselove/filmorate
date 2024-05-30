package com.example.backend.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DaoImpl {

    private Long entityCounter = 0L;

    protected Long getNextId() {
        return ++entityCounter;
    }

    protected String inSql(List<Long> listOfId) {
        if (listOfId.isEmpty()) {
            return "NULL";
        }
        return listOfId.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
