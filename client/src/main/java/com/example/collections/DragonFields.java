package com.example.collections;

public enum DragonFields {
    ID("id"), USER("login"), NAME("name"), AGE("age"), COLOR("color"), TYPE("type"),
    CHARACTER("character"), CAVE("cave"), DATE("creation");

    private String field;

    DragonFields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    @Deprecated
    public static DragonFields getFieldByNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            for (DragonFields fields : DragonFields.values()) {
                if (fields.ordinal() + 1 == num) {
                    return fields;
                }
            }
        } catch (NumberFormatException ignored){}
        return null;
    }

    public static String[] getAll() {
        String[] enums = new String[10];
        for(DragonFields fields: DragonFields.values()) {
            enums[fields.ordinal()] = fields.getField();
        }
        return enums;
    }
}
