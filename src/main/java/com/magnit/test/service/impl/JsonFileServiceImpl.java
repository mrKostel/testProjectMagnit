package com.magnit.test.service.impl;

import com.magnit.test.dto.interfaces.DeserializationEntryContainer;
import com.magnit.test.model.EntryContainer;
import com.magnit.test.service.interfaces.FileService;

/**
 * Designed to work with json format files
 * Not implemented now
 */
public class JsonFileServiceImpl implements FileService {

    @Override
    public void writeEntriesToFile(EntryContainer container) {
    }

    @Override
    public void convertFileToNewFormat() {
    }

    @Override
    public DeserializationEntryContainer getDeserializedEntriesFromFile() {
        return null;
    }

    @Override
    public void cleanFileByPath(String path) {
    }
}
