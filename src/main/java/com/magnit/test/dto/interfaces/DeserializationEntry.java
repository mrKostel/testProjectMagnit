package com.magnit.test.dto.interfaces;

/**
 * The interface displaying a nested deserialization com.magnit.test.model
 * (with the ability to add new file types for deserialization)
 */
public interface DeserializationEntry {
    /**
     * Gets value when marshaling
     */
    Long getField();
}
