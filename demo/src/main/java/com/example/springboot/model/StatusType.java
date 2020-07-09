package com.example.springboot.model;


public enum StatusType {
    Disable( 0), Activity( 1);


    private Integer value = 0;

    StatusType(Integer value) {
        this.value = value;
    }

    public static StatusType ValueOf(Integer value) {
        switch (value) {
            case 0:
                return Disable;
            case 1:
                return Activity;
            default:
                return null;
        }
    }

    public Integer value() {
        return this.value;
    }
}
