package hyun6ik.servlet.basic.web.frontcontroller.v5;

import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberListControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberListControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hyun6ik.servlet.basic.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
@RequiredArgsConstructor
public class FrontControllerServletV5 extends HttpServlet {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    private final Map<String, Object> handlerMappingMap = new ConcurrentHashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3(memberRepository));
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3(memberRepository));

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4(memberRepository));
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4(memberRepository));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final MyHandlerAdapter adapter = getHandlerAdapter(handler);
        final ModelView modelView = adapter.handle(request, response, handler);

        final MyView view = viewResolver(modelView);


        view.render(modelView.getModel(), request, response);

    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        return handlerAdapters
                .stream()
                .filter(adapter -> adapter.supports(handler))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("핸들러 어댑터 찾을 수 없어요. handler = " + handler));
    }

    private Object getHandler(HttpServletRequest request) {
        final String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(ModelView modelView) {
        return MyView.of(PREFIX + modelView.getViewName() + SUFFIX);
    }
}
