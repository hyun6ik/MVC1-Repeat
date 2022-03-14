package hyun6ik.servlet.basic.web.frontcontroller.v3.controller;

import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    private static final String VIEW_NAME = "new-form";

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return ModelView.of(VIEW_NAME);
    }
}
