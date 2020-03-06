package com.magnit.test.dto.impl;

import com.magnit.test.dto.interfaces.DeserializationEntry;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

public class XmlDeserializationEntry implements DeserializationEntry {
    private Long field;

    public XmlDeserializationEntry() {
    }

    @Override
    public Long getField() {
        return field;
    }

    @XmlAttribute(name = "field")
    public void setField(Long field) {
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlDeserializationEntry that = (XmlDeserializationEntry) o;
        return Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "XmlDeserializationEntry{" +
                "field=" + field +
                '}';
    }
}
