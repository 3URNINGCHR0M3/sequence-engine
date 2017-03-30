package com.github.burningchrome.seqeng;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class PeekServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletOutputStream out = resp.getOutputStream();

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");

        final long value = Sequence.getInstance().peek();

        out.print("<b>Current value:</b> ");
        out.println(Long.toString(value));

        out.flush();
        out.close();
    }

}
