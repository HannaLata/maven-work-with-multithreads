package com.mainacad;

import com.mainacad.helper.ConnectionInfoHelper;
import com.mainacad.model.ConnectionInfo;
import com.mainacad.multithreads.MultithreadWrapper;

import java.util.*;
import java.util.logging.Logger;

public class AppRunner {

    private static final Logger LOGGER = Logger.getLogger(AppRunner.class.getName());

    public static void main(String[] args) {

        Operation operation = (num1, num2) -> num1+num2;
        int result = operation.calculate(10, 20);
        LOGGER.info("Resilt is " + result);

        operation = (num1, num2) -> num1*num2;
        result= operation.calculate(10, 20);
        LOGGER.info("Result is " + result);

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            numbers.add(i);
        }

        numbers.stream().filter(it -> it%2 == 0).forEach(it -> LOGGER.info("Filtered element is " + it));

        Map<String, String> users = new HashMap<>();
        users.put("111", "Hanna1");
        users.put("112", "Hanna2");
        users.put("113", "Hanna3");
        users.put("114", "Hanna4");

        users.put("111", "Alex");

        users.put(null, "Maria");

        for (String key: users.keySet()) {
            String value = users.get(key);
            LOGGER.info("Key \"" + key + "\" has value \"" + value + "\"");
        }

        users.keySet().forEach(key -> LOGGER.info("Key \"" + key + "\" has value \"" + users.get(key) + "\""));
        List<String> connectionIpList = Collections.synchronizedList(new ArrayList<>());
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= 100 ; i++) {
            ConnectionInfo connectionInfo = ConnectionInfoHelper.getRandomConnectionInfo();
            MultithreadWrapper multithreadWrapper = new MultithreadWrapper("thread " + i, connectionInfo, connectionIpList);
            threads.add(multithreadWrapper);
            multithreadWrapper.start();
        }

        while (threadsAlive(threads)) {
            try {
                LOGGER.info("\n--------------------\nThreads still alive!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        LOGGER.info(connectionIpList.size() + " connections were written to file");

    }

    private static boolean threadsAlive(List<Thread> threads) {
        for (Thread thread: threads) {
            if(thread.isAlive() || thread.getState().equals(Thread.State.NEW)) {
                return true;
            }
        }
        return false;
    }

    interface Operation{
        int calculate(int num1, int num2);
    }
}
