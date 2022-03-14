package hyun6ik.servlet.basic.web.frontcontroller.v2;

import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v1.ControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberFromControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberListControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hyun6ik.servlet.basic.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hyun6ik.servlet.basic.web.frontcontroller.v2.controller.MemberListControllerV2;
import hyun6ik.servlet.basic.web.frontcontroller.v2.controller.MemberSaveControllerV2;
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

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
@RequiredArgsConstructor
public class FrontControllerServletV2 extends HttpServlet {

    private final Map<String, ControllerV2> controllerV1Map = new ConcurrentHashMap<>();
    private final MemberRepository memberRepository;

    @PostConstruct
    public void setUp() {
        controllerV1Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV1Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2(memberRepository));
        controllerV1Map.put("/front-controller/v2/members", new MemberListControllerV2(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        final ControllerV2 controller = controllerV1Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final MyView view = controller.process(request, response);

        view.render(request,response);
    }
}
