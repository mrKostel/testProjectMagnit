package com.magnit.test;

import com.magnit.test.dto.interfaces.DeserializationEntry;
import com.magnit.test.dto.interfaces.DeserializationEntryContainer;
import com.magnit.test.model.EntryContainer;
import com.magnit.test.service.impl.DataBaseServiceImpl;
import com.magnit.test.service.impl.FileServiceFactory;
import com.magnit.test.service.impl.JdbcConnection;
import com.magnit.test.service.interfaces.DataBaseService;
import com.magnit.test.service.interfaces.FileService;
import com.magnit.test.type.DbType;
import com.magnit.test.type.FileServiceType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Application {
    private static final Logger log = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        Long valueForProcessing;
        Long startTime, finishTime;
        EntryContainer container;
        FileServiceType filesType;
        Scanner scan = null;
        Connection connection = null;

        try {
            scan = new Scanner(System.in);
            connection = createConnectionByParams(scan);

            System.out.println("Please select \"xml\" or \"json\" format for processing");
            filesType = selectFileType(scan);
            valueForProcessing = selectValueFoProcessing(scan);

            startTime = System.currentTimeMillis();
            System.out.println("Please stand by....");

            DataBaseService dbService = new DataBaseServiceImpl();
            dbService.setConnection(connection);
            dbService.saveNewData(valueForProcessing);
            container = new EntryContainer();
            container.setEntryList(dbService.getAllEntries());
        } finally {
            if (scan != null) {
                scan.close();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                log.warning("Failed to close connection");
            }
        }

        FileService fileService = FileServiceFactory.getFileServiceImpl(filesType);
        fileService.writeEntriesToFile(container);
        fileService.convertFileToNewFormat();
        DeserializationEntryContainer deserializationEntryContainer = fileService.getDeserializedEntriesFromFile();

        Long resultSum = 0L;
        for (DeserializationEntry value : deserializationEntryContainer.getDeserializationEntryList()) {
            resultSum += value.getField();
        }
        finishTime = System.currentTimeMillis();
        System.out.println("*********************************************************");
        System.out.println("The result is: " + resultSum);
        System.out.println("Time of processing is: " + (finishTime - startTime) + " milliseconds");
        System.out.println("*********************************************************");
    }

    /**
     * Returns connection by transmitted params to the console
     *
     * @param scan scanner for reading parameters for the database
     * @return connection with the db
     */
    public static Connection createConnectionByParams(Scanner scan) {
        DbType dbType;
        String url, login, password;
        Connection connection = null;
        while (connection == null) {
            System.out.println("Please correctly fill all fields");
            System.out.println("*********************************************************");
            System.out.println("Please write your database com.magnit.test.type(\"MySql\" or \"PostgreSql\"):");
            String type = scan.nextLine();
            System.out.println("Please write your db url: ");
            url = scan.nextLine();
            System.out.println("Please write your db login: ");
            login = scan.nextLine();
            System.out.println("Please write your db password: ");
            password = scan.nextLine();
            try {
                dbType = DbType.valueOf(type.toUpperCase());
                connection = JdbcConnection.buildConnection(url, login, password, dbType);
            } catch (Exception e) {
                log.warning("Failed to create connection, please check your transmitted data and try again");
            }
            System.out.println("*********************************************************");
        }
        return connection;
    }

    /**
     * Returns file service type for transmitted params to the console
     *
     * @param scan scanner to read file format
     * @return file type for processing
     */
    public static FileServiceType selectFileType(Scanner scan) {
        FileServiceType filesType = null;
        while (filesType == null) {
            System.out.println("(Only xml is currently supported)");
            String format = scan.nextLine();
            filesType = format.equalsIgnoreCase(FileServiceType.XML.getFormat()) ? FileServiceType.XML : null;
        }
        return filesType;
    }

    /**
     * Returns number transmitted to the console
     *
     * @param scan scanner to read value
     * @return number written to the console
     */
    public static Long selectValueFoProcessing(Scanner scan) {
        Long valueForProcessing = null;
        while (valueForProcessing == null) {
            System.out.println("Please write a number for processing:");
            try {
                valueForProcessing = Long.valueOf(scan.nextLine());
            } catch (Exception e) {
                System.out.println("You are writing a value with the wrong format (not a number).\n" +
                        "Please try again.");
            }
        }
        return valueForProcessing;
    }
}
