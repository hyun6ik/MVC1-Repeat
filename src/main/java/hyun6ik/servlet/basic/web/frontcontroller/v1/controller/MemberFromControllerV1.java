package hyun6ik.servlet.basic.web.frontcontroller.v1.controller;

import hyun6ik.servlet.basic.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFromControllerV1 implements ControllerV1 {

    private static final String VIEW_PATH = "/WEB-INF/views/new-form.jsp";

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request,response);
    }
}
