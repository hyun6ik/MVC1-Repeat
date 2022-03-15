package hyun6ik.servlet.basic.web.springmvc.v1;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SpringMemberSaveControllerV1 {

    private static final String VIEW_NAME = "save-result";

    private final MemberRepository memberRepository;

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        final String username = request.getParameter("username");
        final int age = Integer.parseInt(request.getParameter("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        final ModelAndView mv = new ModelAndView(VIEW_NAME);
        mv.addObject("member", member);
        return mv;
    }
}
