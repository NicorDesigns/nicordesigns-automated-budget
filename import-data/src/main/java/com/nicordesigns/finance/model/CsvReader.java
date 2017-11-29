package com.nicordesigns.finance.model;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public abstract class CsvReader {

    private static final Logger LOG = LogManager.getLogger(CsvReader.class);

    protected static File[] getCsvFiles(String directory) {
        File useCaseDirectory = new File(directory);

        if (!useCaseDirectory.isDirectory()) {
            throw new RuntimeException(directory + " is not a valid directory or cannot be found");
        }

        File[] csvFiles = useCaseDirectory.listFiles(pathname -> pathname.getName()
                .endsWith(".csv"));

        if (csvFiles.length > 0) {
            LOG.info("Found " + csvFiles.length + " csv files from " + directory);
        } else {
            throw new RuntimeException(directory + " does not contain any *.csv files");
        }
        return csvFiles;
    }

    protected static MappingIterator<Map<String, String>> getCsvIterator(URL url) throws IOException {
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader();
        ObjectMapper mapper = new CsvMapper();

        return mapper.readerFor(Map.class)
                .with(schema)
                .readValues(url);
    }
}
