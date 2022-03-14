package hyun6ik.servlet.basic.web.frontcontroller.v3;

import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v2.ControllerV2;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
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

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
@RequiredArgsConstructor
public class FrontControllerServletV3 extends HttpServlet {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    private final Map<String, ControllerV3> controllerV3Map = new ConcurrentHashMap<>();
    private final MemberRepository memberRepository;

    @PostConstruct
    public void setUp() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3(memberRepository));
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String requestURI = request.getRequestURI();

        final ControllerV3 controller = controllerV3Map.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);

        final ModelView modelView = controller.process(paramMap);

        final MyView view = ViewResolver(modelView);
        view.render(modelView.getModel(), request,response);

    }

    private MyView ViewResolver(ModelView modelView) {
        return MyView.of(PREFIX + modelView.getViewName() + SUFFIX);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
