package hyun6ik.servlet.basic.web.frontcontroller.v2.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v2.ControllerV2;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class MemberSaveControllerV2 implements ControllerV2 {

    private static final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";

    private final MemberRepository memberRepository;

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final int age = Integer.parseInt(request.getParameter("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        request.setAttribute("member", member);

        return MyView.of(VIEW_PATH);
    }
}
