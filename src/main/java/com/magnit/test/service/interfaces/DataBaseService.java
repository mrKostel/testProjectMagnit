package com.magnit.test.service.interfaces;

import com.magnit.test.model.Entry;

import java.sql.Connection;
import java.util.List;

/**
 *  The com.magnit.test.service to the maintain operations with file.
 */
public interface DataBaseService {

    void setConnection(Connection con);

    /**
     * Saves numbers from 0 to transmitted value
     * @param value value max  number for save to db
     */
    void saveNewData(Long value);

    /**
     * Returns all models.
     * @return the business models representing a table
     */
    List<Entry> getAllEntries();
}
