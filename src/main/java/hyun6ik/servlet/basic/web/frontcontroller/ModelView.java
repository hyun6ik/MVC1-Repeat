package hyun6ik.servlet.basic.web.frontcontroller;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class ModelView {

    private String viewName;
    private Map<String, Object> model = new ConcurrentHashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public ModelView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public static ModelView of(String viewName) {
        return new ModelView(viewName);
    }

    public static ModelView of(String viewName, Map<String, Object> model) {
        return new ModelView(viewName, model);
    }
}
