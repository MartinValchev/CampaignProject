package com.company.campaignproject.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum Webversion implements EnumClass<String> {
    Desktop("desktop"),
    Mobile("mobile");

    private String id;

    Webversion(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Webversion fromId(String id) {
        for (Webversion at : Webversion.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}