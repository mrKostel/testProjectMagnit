package com.magnit.test.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name = "entries")
public class EntryContainer {

    private List<Entry> entryList;

    public EntryContainer() {
    }

    @XmlElement(name = "entry")
    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryContainer that = (EntryContainer) o;
        return Objects.equals(entryList, that.entryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryList);
    }

    @Override
    public String toString() {
        return "EntryContainer{" +
                "entryList=" + entryList +
                '}';
    }
}
