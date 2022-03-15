package hyun6ik.servlet.basic.web.springmvc.v2;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
@RequiredArgsConstructor
public class SpringMemberControllerV2 {

    private static final String FORM_VIEW_NAME = "new-form";
    private static final String SAVE_VIEW_NAME = "save-result";
    private static final String LIST_VIEW_NAME = "members";

    private final MemberRepository memberRepository;

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView(FORM_VIEW_NAME);
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        final String username = request.getParameter("username");
        final int age = Integer.parseInt(request.getParameter("age"));

        final Member member = memberRepository.save(Member.of(username, age));

        final ModelAndView mv = new ModelAndView(SAVE_VIEW_NAME);
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping
    public ModelAndView getMembers() {
        final List<Member> members = memberRepository.findAll();

        final ModelAndView mv = new ModelAndView(LIST_VIEW_NAME);
        mv.addObject("members", members);
        return mv;
    }
}
