package hyun6ik.servlet.basic.web.frontcontroller.v4;

import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v3.ControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
@RequiredArgsConstructor
public class FrontControllerServletV4 extends HttpServlet {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    private final Map<String, ControllerV4> controllerV4Map = new ConcurrentHashMap<>();
    private final MemberRepository memberRepository;

    @PostConstruct
    public void setUp() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4(memberRepository));
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        final ControllerV4 controller = controllerV4Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        final String viewName = controller.process(paramMap, model);

        final MyView view = ViewResolver(viewName);
        view.render(model, request,response);

    }

    private MyView ViewResolver(String viewName) {
        return MyView.of(PREFIX + viewName + SUFFIX);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}