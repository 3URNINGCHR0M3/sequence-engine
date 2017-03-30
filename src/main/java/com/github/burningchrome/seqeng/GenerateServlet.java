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
public class GenerateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ServletOutputStream out = resp.getOutputStream();

        resp.setContentType("text/plain;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        long value = Sequence.getInstance().next();

        out.print(Long.toString(value));

        out.flush();
        out.close();

    }

}
