package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//스프링 컨테이너가 @Controller가 있으면 MemberController 넣어놓고 스프링이 관리를 한다.
@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService(); 이렇게 쓸수도 있지만 매번 만들지 않고 하나만 쓰는게 좋다.
    //스프링 컨테이너에 넣어놓고 쓰면된다.

    private final MemberService memberService;

    @Autowired //스프링 컨테이너에 있는 memberService를 가져다 쓴다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm (){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
