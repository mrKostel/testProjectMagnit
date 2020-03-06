package com.magnit.test.service.impl;

import com.magnit.test.model.Entry;
import com.magnit.test.service.interfaces.DataBaseService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class DataBaseServiceImpl implements DataBaseService {
    private static final Logger log = Logger.getLogger(DataBaseServiceImpl.class.getName());
    private static final String CHECK_EXISTS_TABLE = "CREATE TABLE IF NOT EXISTS test(value BIGINT(20) NOT NULL)";
    private static final String CLEAN_TABLE = "DELETE FROM test";
    private static final String CREATE_VALUES = "INSERT INTO test(value) VALUES";
    private static final String FIND_ALL = "SELECT value FROM test";

    private Connection connection;

    public DataBaseServiceImpl() {
    }

    public void setConnection(Connection con) {
        this.connection = con;
    }

    @Override
    public void saveNewData(Long value) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(CHECK_EXISTS_TABLE);
            String query = CREATE_VALUES;
            statement.executeUpdate(CLEAN_TABLE);
            for (long i = 1; i <= value; i++) {
                if (i % 1000 == 0 || i == value) {
                    statement.executeUpdate(query.concat("(").concat(String.valueOf(i).concat(")")));
                    query = CREATE_VALUES;
                } else {
                    query = query.concat("(").concat(String.valueOf(i)).concat(")").concat(",");
                }
            }
            statement.close();
        } catch (SQLException e) {
            log.warning("Failed to save new values to the database");
        }
    }

    @Override
    public List<Entry> getAllEntries() {
        List<Entry> resultList = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(FIND_ALL);
            while (rs.next()) {
                Long value = rs.getLong("value");
                Entry entry = new Entry();
                entry.setField(value);
                resultList.add(entry);
            }
        } catch (SQLException e) {
            log.warning("Failed to get values from database");
        }
        return resultList;
    }
}
