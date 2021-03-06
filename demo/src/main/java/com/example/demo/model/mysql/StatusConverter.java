package com.example.demo.model.mysql;

import javax.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<StatusType, Integer> {


    @Override
    public Integer convertToDatabaseColumn(StatusType attribute) {
        return attribute.value();
    }

    @Override
    public StatusType convertToEntityAttribute(Integer dbData) {
        return StatusType.ValueOf(dbData);
    }
}