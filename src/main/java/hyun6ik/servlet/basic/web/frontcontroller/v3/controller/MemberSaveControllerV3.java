package hyun6ik.servlet.basic.web.frontcontroller.v3.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.v3.ControllerV3;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerV3 implements ControllerV3 {

    private static final String VIEW_NAME = "save-result";

    private final MemberRepository memberRepository;


    @Override
    public ModelView process(Map<String, String> paramMap) {
        final String username = paramMap.get("username");
        final int age = Integer.parseInt(paramMap.get("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        final ModelView mv = ModelView.of(VIEW_NAME);
        mv.getModel().put("member", member);

        return mv;
    }
}
