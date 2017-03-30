package com.github.burningchrome.seqeng;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

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

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if((webPort == null) || webPort.isEmpty()) {
            webPort = "8080";
        }

        Server server = new Server(Integer.valueOf(webPort));
//        WebAppContext root = new WebAppContext();

//        root.setContextPath("/");
//        root.setDescriptor("WEB-INF/web.xml");
//        root.setResourceBase("");

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