package com.magnit.test.dto.interfaces;

import java.util.List;

/**
 * The interface of the object displaying the deserialization com.magnit.test.model
 * (with the ability to add new file types for deserialization)
 */
public interface DeserializationEntryContainer {
    /**
     * Gets list with nested deserialization models when marshaling
     */
    List<? extends DeserializationEntry> getDeserializationEntryList();
}
