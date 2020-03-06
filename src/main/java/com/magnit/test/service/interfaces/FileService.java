package com.magnit.test.service.interfaces;

import com.magnit.test.dto.interfaces.DeserializationEntryContainer;
import com.magnit.test.model.EntryContainer;

/**
 *  The com.magnit.test.service to the maintain operations with file.
 */
public interface FileService {
    /**
     * Writes transmitted com.magnit.test.model to file
     * @param container com.magnit.test.model for marshaling
     */
    void writeEntriesToFile(EntryContainer container);

    /**
     * Converts a file to a new format(writes data to new file)
     */
    void convertFileToNewFormat();

    /**
     * Reads deserialization com.magnit.test.model from file
     * @return deserialization com.magnit.test.model
     */
    DeserializationEntryContainer getDeserializedEntriesFromFile();

    /**
     * Cleans file by transmitted path
     * @param path path for find file
     */
    void cleanFileByPath(String path);
}
