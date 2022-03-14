package hyun6ik.servlet.basic.web.frontcontroller.v1;

import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberFromControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberListControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import lombok.RequiredArgsConstructor;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
@RequiredArgsConstructor
public class FrontControllerServletV1 extends HttpServlet {

    private final Map<String, ControllerV1> controllerV1Map = new ConcurrentHashMap<>();
    private final MemberRepository memberRepository;

    @PostConstruct
    public void setUp() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFromControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1(memberRepository));
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        final String requestURI = request.getRequestURI();

        final ControllerV1 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response);
    }
}
