package com.mainacad.multithreads;

import com.mainacad.model.ConnectionInfo;
import com.mainacad.service.FileService;
import java.util.List;
import java.util.logging.Logger;

public class MultithreadWrapper extends Thread {

    private final String threadName;
    private final ConnectionInfo connectionInfo;
    private static final Logger LOGGER = Logger.getLogger(MultithreadWrapper.class.getName());

    private List connectionIpList;

    public MultithreadWrapper(String threadName, ConnectionInfo connectionInfo, List connectionIpList) {
        this.threadName = threadName;
        this.connectionInfo = connectionInfo;
        this.connectionIpList = connectionIpList;
    }

    @Override
    public void run() {
        LOGGER.info(threadName + " was started!");

        FileService.writeTextToFile(connectionInfo.toString(), "multi.txt", true);
        connectionIpList.add(connectionInfo.getIp());



    }
}
