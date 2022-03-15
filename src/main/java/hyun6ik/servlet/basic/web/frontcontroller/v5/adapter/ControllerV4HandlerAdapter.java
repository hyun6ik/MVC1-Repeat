package hyun6ik.servlet.basic.web.frontcontroller.v5.adapter;

import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v4.ControllerV4;
import hyun6ik.servlet.basic.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    private static final String PREFIX = "/WEB-INF/views/";
    private static final String SUFFIX = ".jsp";

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        final ControllerV4 controllerV4 = (ControllerV4) handler;

        final Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        final String viewName = controllerV4.process(paramMap, model);

        return ModelView.of(viewName, model);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return MyView.of(PREFIX + viewName + SUFFIX);
    }
}
