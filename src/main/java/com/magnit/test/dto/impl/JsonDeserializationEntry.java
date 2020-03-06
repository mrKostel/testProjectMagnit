package com.magnit.test.dto.impl;

import com.magnit.test.dto.interfaces.DeserializationEntry;

import java.util.Objects;

/**
 * Designed to work with json format files
 * Not use now
 */

public class JsonDeserializationEntry implements DeserializationEntry {
    private Long field;

    public JsonDeserializationEntry() {
    }

    @Override
    public Long getField() {
        return field;
    }

    public void setField(Long field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonDeserializationEntry that = (JsonDeserializationEntry) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "JsonDeserializationEntry{" +
                "field=" + field +
                '}';
    }
}
