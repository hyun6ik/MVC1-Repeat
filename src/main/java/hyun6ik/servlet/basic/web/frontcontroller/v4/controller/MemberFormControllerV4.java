package hyun6ik.servlet.basic.web.frontcontroller.v4.controller;

import hyun6ik.servlet.basic.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    private static final String VIEW_NAME = "new-form";

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return VIEW_NAME;
    }
}
