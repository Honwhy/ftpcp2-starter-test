package com.github.honwhy;

import org.apache.ftpserver.test.ClientTestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ftpcp2StarterTestApplication {

    private static ClientTestTemplate clientTestTemplate;

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(Ftpcp2StarterTestApplication.class, args);
        startServer();
        FTPClientManager ftpClientManager = applicationContext.getBean(FTPClientManager.class);
        PooledFTPClient pooledFTPClient = ftpClientManager.getFTPClient();
        System.out.println(pooledFTPClient.getCreateTimestamp());
        pooledFTPClient.close();
        stopServer();
    }



    private static void startServer() throws Exception {
        ClientTestTemplate clientTestTemplate = new ClientTestTemplate(){};
        clientTestTemplate.setUp();
        Ftpcp2StarterTestApplication.clientTestTemplate = clientTestTemplate;
    }

    private static void stopServer() throws Exception {
        Ftpcp2StarterTestApplication.clientTestTemplate.tearDown();
    }
}
