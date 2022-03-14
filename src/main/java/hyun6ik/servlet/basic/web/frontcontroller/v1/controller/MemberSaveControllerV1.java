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

@RequiredArgsConstructor
public class MemberSaveControllerV1 implements ControllerV1 {

    private static final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";

    private final MemberRepository memberRepository;


    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final int age = Integer.parseInt(request.getParameter("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        // Model에 저장하기
        request.setAttribute("member",member);

        final RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request,response);
    }
}
