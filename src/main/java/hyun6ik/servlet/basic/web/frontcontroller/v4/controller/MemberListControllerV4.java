package hyun6ik.servlet.basic.web.frontcontroller.v4.controller;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import hyun6ik.servlet.basic.web.frontcontroller.ModelView;
import hyun6ik.servlet.basic.web.frontcontroller.v4.ControllerV4;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MemberListControllerV4 implements ControllerV4 {

    private static final String VIEW_NAME = "members";

    private final MemberRepository memberRepository;

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        final List<Member> members = memberRepository.findAll();

        model.put("members", members);
        return VIEW_NAME;
    }
}
