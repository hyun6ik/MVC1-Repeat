package hyun6ik.servlet.basic.web.frontcontroller.v1.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.v1.ControllerV1;
import lombok.RequiredArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class MemberListControllerV1 implements ControllerV1 {

    private static final String VIEW_PATH = "/WEB-INF/views/members.jsp";

    private final MemberRepository memberRepository;

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        final RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request,response);
    }
}
