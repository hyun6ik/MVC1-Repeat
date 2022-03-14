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
import java.util.List;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
@RequiredArgsConstructor
public class MvcMemberListServlet extends HttpServlet {

    private static final String VIEW_PATH = "/WEB-INF/views/members.jsp";

    private final MemberRepository memberRepository;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<Member> members = memberRepository.findAll();

        request.setAttribute("members", members);

        final RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_PATH);
        dispatcher.forward(request,response);
    }
}
