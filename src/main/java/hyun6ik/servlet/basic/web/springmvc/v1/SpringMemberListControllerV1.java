package hyun6ik.servlet.basic.web.springmvc.v1;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class SpringMemberListControllerV1 {

    private static final String VIEW_NAME = "members";

    private final MemberRepository memberRepository;

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        final List<Member> members = memberRepository.findAll();

        final ModelAndView mv = new ModelAndView(VIEW_NAME);
        mv.addObject("members", members);
        return mv;
    }
}
