package com.github.burningchrome.seqeng;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class SeqEngHandler extends AbstractHandler {

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
//        response.setContentType("text/plain;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//        baseRequest.setHandled(true);
//        _sequence++;
//        response.getWriter().println(Long.toString(_sequence));
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new SeqEngHandler());

        server.start();
        server.join();
    }

}
