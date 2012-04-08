package ru.efive.start.rhino;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Launcher {
    private final static ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/jetty.xml");
    private final static Server server = context.getBean(Server.class);
    private final static Logger logger = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        Signal.handle(new Signal("TERM"), new SignalHandler() {
            public void handle(Signal signal) {
                stopServer();
            }
        });
        try{
            logger.info("Starting Jetty server...");
            server.start();
            logger.info("Started");
            server.join();
        } catch(Exception e) {
            logger.trace("{}", e);
        }
    }

    public static void stopServer() {
        try {
            logger.info("Stopping Jetty server...");
            server.stop();
            logger.info("Stopped");
        } catch (Exception e) {
            logger.trace("{}", e);
            System.exit(1);
        }
    }


}
