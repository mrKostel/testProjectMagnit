package com.magnit.test.dto.impl;

import com.magnit.test.dto.interfaces.DeserializationEntryContainer;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "entries")
public class XmlDeserializationEntryContainer implements DeserializationEntryContainer {

    private List<XmlDeserializationEntry> deserializationEntryList;

    public XmlDeserializationEntryContainer() {
    }

    @Override
    public List<XmlDeserializationEntry> getDeserializationEntryList() {
        return deserializationEntryList;
    }

    @XmlElement(name = "entry")
    public void setDeserializationEntryList(List<XmlDeserializationEntry> list) {
        deserializationEntryList = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XmlDeserializationEntryContainer that = (XmlDeserializationEntryContainer) o;
        return Objects.equals(deserializationEntryList, that.deserializationEntryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deserializationEntryList);
    }

    @Override
    public String toString() {
        return "XmlDeserializationEntryContainer{" +
                "deserializationEntryList=" + deserializationEntryList +
                '}';
    }
}
