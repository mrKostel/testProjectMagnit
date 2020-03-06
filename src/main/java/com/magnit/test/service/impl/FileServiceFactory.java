package com.magnit.test.service.impl;

import com.magnit.test.service.interfaces.FileService;
import com.magnit.test.type.FileServiceType;

/**\
 * Service for choosing the implementation of a file service with the required file format
 */
public class FileServiceFactory {

    public static FileService getFileServiceImpl(FileServiceType type) {
        switch (type) {
            case XML:
                return new XmlFileServiceImpl();
            case JSON:
                return new JsonFileServiceImpl();
            default:
                throw new IllegalStateException();
        }
    }
}
