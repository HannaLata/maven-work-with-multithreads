package com.mainacad.service;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileServiceTest {

        private static final String TEXT_FILE_NAME = "test_text_file.txt";

        @BeforeEach
        void setUp() {

            FileService.writeTextToFile("", TEXT_FILE_NAME, false);
            ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
            FileService.writeTextToFile(connectionInfo.toString(), TEXT_FILE_NAME, false);

        }

        @AfterEach
        void tearDown() {

            File testTextFile = new File(FileService.FILES_DIR + FileService.SEP + TEXT_FILE_NAME);
            testTextFile.delete();

        }


        @Test
        void readTextFromFile() {
            String testText = FileService.readTextFromFile(TEXT_FILE_NAME);
            assertNotNull(testText);
            assertTrue(testText.contains(" "));
            assertTrue(testText.length() > 22);
        }

        @Test
        void readConnectionsFromFile() {
            List<ConnectionInfo> testObj = FileService.readConnectionsFromFile(TEXT_FILE_NAME);
            assertNotNull(testObj);;
            assertTrue(testObj.size() > 0);;
            assertNotNull(testObj.get(0).getConnectionTime());
            assertNotNull(testObj.get(0).getIp());
            assertNotNull(testObj.get(0).getSessionId());
            assertTrue(testObj.get(0).getSessionId() > 10000 && testObj.get(0).getSessionId() < 99999);
        }

    }


