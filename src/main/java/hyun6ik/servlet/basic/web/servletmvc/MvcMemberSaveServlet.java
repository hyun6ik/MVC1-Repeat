package hyun6ik.servlet.basic.web.servletmvc;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
@RequiredArgsConstructor
public class MvcMemberSaveServlet extends HttpServlet {

    private static final String VIEW_PATH = "/WEB-INF/views/save-result.jsp";

    private final MemberRepository memberRepository;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String username = request.getParameter("username");
        final int age = Integer.parseInt(request.getParameter("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        // Model에 저장하기
        request.setAttribute("member",member);

        final RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request,response);
    }
}
