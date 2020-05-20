package com.mainacad.helper;

import com.mainacad.model.ConnectionInfo;

import java.util.Date;
import java.util.Random;

public class ConnectionInfoHelper {

    public static ConnectionInfo getRandomConnectionInfo() {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setSessionId(getRandomNumber(10000, 99999));
        connectionInfo.setConnectionTime(new Date().getTime() - getRandomNumber(0, 20000));
        connectionInfo.setIp(
                getRandomNumber(100, 255) + "." +
                        getRandomNumber(100, 255) + "." +
                        getRandomNumber(100, 255) + "." +
                        getRandomNumber(100, 255)

        );

        return connectionInfo;
    }

    private static int getRandomNumber(int from, int to) {
        return from + new Random().nextInt(to - from);
    }
}
