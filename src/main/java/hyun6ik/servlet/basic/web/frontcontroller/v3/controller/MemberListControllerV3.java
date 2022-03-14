package hyun6ik.servlet.basic.web.frontcontroller.v3.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.MyView;
import hyun6ik.servlet.basic.web.frontcontroller.v2.ControllerV2;
import hyun6ik.servlet.basic.web.frontcontroller.v3.ControllerV3;
import lombok.RequiredArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerV3 implements ControllerV3 {
    private static final String VIEW_NAME = "members";

    private final MemberRepository memberRepository;


    @Override
    public ModelView process(Map<String, String> paramMap) {
        final List<Member> members = memberRepository.findAll();
        final ModelView mv = ModelView.of(VIEW_NAME);

        mv.getModel().put("members", members);
        return mv;
    }
}
