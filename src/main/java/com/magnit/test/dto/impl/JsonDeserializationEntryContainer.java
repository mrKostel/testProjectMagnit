package com.magnit.test.dto.impl;

import com.magnit.test.dto.interfaces.DeserializationEntryContainer;

import java.util.List;
import java.util.Objects;

/**
 * Designed to work with json format files
 * Not use now
 */
public class JsonDeserializationEntryContainer implements DeserializationEntryContainer {
    private List<JsonDeserializationEntry> deserializationEntryList;

    public JsonDeserializationEntryContainer() {
    }

    @Override
    public List<JsonDeserializationEntry> getDeserializationEntryList() {
        return deserializationEntryList;
    }

    public void setDeserializationEntryList(List<JsonDeserializationEntry> deserializationEntryList) {
        this.deserializationEntryList = deserializationEntryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonDeserializationEntryContainer that = (JsonDeserializationEntryContainer) o;
        return Objects.equals(deserializationEntryList, that.deserializationEntryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deserializationEntryList);
    }

    @Override
    public String toString() {
        return "JsonDeserializationEntryContainer{" +
                "deserializationEntryList=" + deserializationEntryList +
                '}';
    }
}
