package com.github.burningchrome.seqeng;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

/**
 *
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 *
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{

        String webPort;

        if (args.length == 1) {
            webPort = args[0];
        } else {
            webPort = System.getenv("SEQ_GEN_PORT");
        }

        if((webPort == null) || webPort.isEmpty()) {
            webPort = "8080";
        }

        final File file = new File("sequence-engine.dat");
        final FileManager fileManager = new FileManager(file);

        final long currentValue = fileManager.load();
        System.out.println("currentValue = " + currentValue);

        final Sequence sequence = Sequence.getInstance();
        sequence.init(currentValue);
        sequence.setListener(fileManager);

        Server server = new Server(Integer.valueOf(webPort));

        WebAppContext wac = new WebAppContext();
        wac.setResourceBase(".");
        wac.setDescriptor(Main.class.getResource("/WEB-INF/web.xml").toString());

        wac.setContextPath("/");
        wac.setParentLoaderPriority(true);
        server.setHandler(wac);

        server.start();
        server.join();

    }

}