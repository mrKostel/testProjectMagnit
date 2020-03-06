package com.magnit.test.service.impl;

import com.magnit.test.dto.impl.XmlDeserializationEntryContainer;
import com.magnit.test.model.EntryContainer;
import com.magnit.test.service.interfaces.FileService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class XmlFileServiceImpl implements FileService {
    private static final Logger log = Logger.getLogger(XmlFileServiceImpl.class.getName());

    private final static String tempDirectory = "temp";
    private final static String saveDataFile = "1.xml";
    private final static String newFormatFile = "2.xml";
    private final static String transformFile = "src/main/resources/transformer.xsl";

    @Override
    public void writeEntriesToFile(EntryContainer container) {
        try {
            File file = preparateTempFileForUse(saveDataFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(EntryContainer.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(container, file);
            log.info("Writing data from database to xml file was successful");
        } catch (JAXBException ex) {
            log.warning("Failed to write into the file");
        }
    }

    @Override
    public void convertFileToNewFormat() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xsltSource = new StreamSource(new File(transformFile));
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            Source fileSource = new StreamSource(new File(concatPath(saveDataFile)));

            transformer.transform(fileSource, new StreamResult(preparateTempFileForUse(newFormatFile)));
            log.info("Writing data to a new XML file was successful");
        } catch (Exception ex) {
            log.warning("Failed to write to the new xml file");
        }
    }

    @Override
    public XmlDeserializationEntryContainer getDeserializedEntriesFromFile() {
        XmlDeserializationEntryContainer resultEntries = null;
        try {
            File file = new File(concatPath(newFormatFile));
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlDeserializationEntryContainer.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            resultEntries = (XmlDeserializationEntryContainer) jaxbUnmarshaller.unmarshal(file);
            log.info("Retrieving data from the new XML file was successful");
        } catch (Exception ex) {
            log.warning("Failed to read from the new XML file");
        }
        return resultEntries;
    }

    @Override
    public void cleanFileByPath(String path) {
        try (FileWriter fileWriter = new FileWriter(path);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.flush();
            bufferedWriter.close();
            log.info("Cleaned xml file by path: " + path);
        } catch (IOException ex) {
            log.warning("Failed of file cleaning");
        }
    }

    private File preparateTempFileForUse(String fileName) {
        File resultFile = new File(concatPath(fileName));
        File tempDir = new File(tempDirectory);
        try {
            if (!tempDir.exists()) {
                tempDir.mkdirs();
                resultFile.createNewFile();
            } else {
                if (!resultFile.exists()) {
                    resultFile.createNewFile();
                } else {
                    cleanFileByPath(resultFile.getPath());
                }
            }
        } catch (IOException e) {
            log.warning("Failed to create - ".concat(fileName));
        }
        return resultFile;
    }

    private String concatPath(String fileName) {
        return tempDirectory.concat("/").concat(fileName);
    }
}
