package hyun6ik.servlet.basic.web.frontcontroller.v4.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.v4.ControllerV4;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSaveControllerV4 implements ControllerV4 {

    private static final String VIEW_NAME = "save-result";

    private final MemberRepository memberRepository;

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        final String username = paramMap.get("username");
        final int age = Integer.parseInt(paramMap.get("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        model.put("member", member);
        return VIEW_NAME;
    }
}
