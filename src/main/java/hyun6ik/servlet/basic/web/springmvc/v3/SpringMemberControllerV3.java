package hyun6ik.servlet.basic.web.springmvc.v3;

import hyun6ik.servlet.basic.domain.Member;
import hyun6ik.servlet.basic.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
@RequiredArgsConstructor
public class SpringMemberControllerV3 {

    private static final String FORM_VIEW_NAME = "new-form";
    private static final String SAVE_VIEW_NAME = "save-result";
    private static final String LIST_VIEW_NAME = "members";

    private final MemberRepository memberRepository;

    @GetMapping("/new-form")
    public String newForm() {
        return FORM_VIEW_NAME;
    }

    @PostMapping("/save")
    public String save(@RequestParam String username, @RequestParam int age, Model model) {
        final Member member = memberRepository.save(Member.of(username, age));
        model.addAttribute("member", member);
        return SAVE_VIEW_NAME;
    }

    @GetMapping
    public String getMembers(Model model) {
        final List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return LIST_VIEW_NAME;
    }
}
