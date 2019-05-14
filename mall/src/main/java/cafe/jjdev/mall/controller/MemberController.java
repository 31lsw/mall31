package cafe.jjdev.mall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cafe.jjdev.mall.service.MemberService;
import cafe.jjdev.mall.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	
	// 비밀번호 찾기 폼
	@GetMapping("/member/findMemberPw")
	public String findMemberPwByNameAndEmail() {
		System.out.println("@GetMapping(\"/member/findMemberPw\")");
		
		return "member/findMemberPw";
	}
	// 아이디와 이메일 일치하면 임시비번 발송
	@PostMapping("/member/findMemberPw")
	public String findMemberPwByNameAndEmail(Member member) {
		System.out.println("@PostMapping(\"/member/findMemberPw\")");
		System.out.println("[param] member : " + member);
		memberService.findMemberPwByIdAndEmail(member);
		return "redirect:/";
	}
	
	
	/*
	 * 로그인 처리(실무에서 가장많이 쓰는방법)
	 * 방법1. 서블릿의 세션을 이용하는 방법 (*수업에서는 이방법으로 다룬다. 프로젝트 진행 시에는 3가지에서 선택)
	 * 방법2. 스프링에서 제공하는 기능을 사용하는 방법
	 * 방법3. 세션을 처리하는 프레임워크 : Spring Security라는 Framework
	 */
	
	// 1. 로그인 폼 -> index.jsp(로그인 상태) OR member/login.jsp(비로그인)
	@GetMapping("/member/login")
	public String getMember(HttpSession session) { //스프링이 주입할 수 있는 HttpSession은 스프링이 부팅할 때 생성했던 세션
		if(session.getAttribute("loginMember") != null) { //이미 로그인 돼있는 상태
			return "redirect:/"; //index 화면으로 리다이렉트
		} else {
			return "member/login"; // /member/login.jsp
		}
		
	}
	
	// 2. 로그인 액션 -> memberService.getMember -> memberMapper.getMember -> memberMapper.selectMember -> 비로그인 시 /member/login.jsp , 로그인상태일시 index.jsp  
	@PostMapping("/member/login")
	public String getMember(HttpSession session, Member member) {
		Member loginMember = memberService.getMember(member);
		if (loginMember == null) {
			return "redirect:/member/login";

		} else {
			session.setAttribute("loginMember", loginMember);
			return "redirect:/";
		}

	}

	// 3. 로그아웃 -> 세션 무효화 후 -> index.jsp 
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 4. 회원가입 폼 GET addMember -> member/addMember.jsp
	@GetMapping("/member/addMember")
	public String addMember() {
		System.out.println("@GetMapping(\"/member/addMember\")");
		System.out.println("[Method called] MemberController.addMember -> GET");
		System.out.println("[Param] Nothing....");
		
		return "member/addMember";
	}
	
	
	// ID 중복체크
	@GetMapping("/member/checkIdDuplicated")
	public String checkIdDuplicated(Model model, String memberId) {
		boolean checkIdDuplicatedResult = memberService.checkIdDuplicated(memberId);
		
		String messagecheckIdDuplicated = null;
		if(checkIdDuplicatedResult) {
			messagecheckIdDuplicated = "입력한 ID로 가입이 가능합니다";
		}else {
			messagecheckIdDuplicated = "중복된 ID로 가입이 불가능합니다";
		}
		
		model.addAttribute("messagecheckIdDuplicated", messagecheckIdDuplicated);
		
		return "/member/addMember";
	}
	
	
	
	// 5. 회원가입 액션 POST addMember -> memberService.addMember -> memberMapper.insertMember -> index.jsp 
	@PostMapping("/member/addMember")
	//public String addMember(Member member) {
	public String addMember(HttpSession session, Member member) {
		System.out.println("@GetMapping(\"/member/addMember\")");
		System.out.println("[Method called] MemberController.addMember -> POST");
		System.out.println("[Param] member : " + member);
		//return할 문자열을 담은 변수
		String result = null;
		
		//ID 중복체크 
		boolean checkIdDuplicatedResult = memberService.checkIdDuplicated(member.getMemberId());
		
		//checkIdDuplicatedResult가 true(중복아님)/false(중복임)
		if(checkIdDuplicatedResult) {
			System.out.println("입력한 id로 가입 가능");
			// 회원등록
			int addMemberResult = memberService.addMember(member);
			
			// 회원등록 성공 시 가입ID로 즉시 로그인 하기 위한 코드
			if(addMemberResult != 0) {
				result = getMember(session, member);
			}
		} else {
			System.out.println("중복된 id로 가입 불가능");
			result = "/member/addMember";
		}
		
		System.out.println("result : " + result);
		return result; // 기존코드 : return "redirect:/";
		
	}
	
	// 6. 개인정보확인 -> memberService.getMemberInfo -> memberMapper.getMemberInfo(-> getMemberByUK) -> memberMapper.selectMemberByUK -> member/getMemberInfo.jsp
	@GetMapping("/member/getMemberInfo")
	public String getMemberInfo(Model model, HttpSession session) {
		System.out.println("@GetMapping(\"/member/getMemberInfo\")");
		System.out.println("[Method called] MemberController.getMemberInfo -> GET");
		
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		Member member = memberService.getMemberByUK(loginMember.getMemberId());
		//Member member = memberService.getMemberInfo(loginMember.getMemberId());
		System.out.println("member : " + member);
		model.addAttribute("member", member);
		
		return "member/getMemberInfo";
		
	}
	
	// 7. 비밀번호 수정 폼 -> memberService.getMemberByUK -> memberService.selectMemberByUK -> member/modifyMemberPw.jsp
	@GetMapping("/member/modifyMemberPw")
	public String modifyMemberPw(Model model, HttpSession session) {
		System.out.println("@GetMapping(\"/member/modifyMemberPw\")");
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		Member member = memberService.getMemberByUK(loginMember.getMemberId());
		System.out.println("member : " + member);
		model.addAttribute("member", member);
	
		return "member/modifyMemberPw";
	}
	// 8. 비밀번호 수정 액션 -> memberService.modifyMemberPw -> memberMapper.updateMemberPw -> index.jsp
	@PostMapping("/member/modifyMemberPw")
	public String modifyMemberPw(Member member, @RequestParam(value="memberCurrentPw", required=true) String memberCurrentPw) {
		System.out.println("@PostMapping(\"/member/modifyMemberPw\")");
		System.out.println("[Method called] MemberController.modifyMemberPw -> POST");
		System.out.println("[Param 1] memberId : " + member.getMemberId()); //해당 아이디 들어온다
		System.out.println("[Param 1] memberPw : " + member.getMemberPw()); //변경 비번 들어온다
		System.out.println("[Param 2] memberCurrentPw : " + memberCurrentPw); //기존 비번 들어온다
		//맵에 셋팅한다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", member.getMemberId());
		map.put("memberPw", member.getMemberPw());
		map.put("memberCurrentPw", memberCurrentPw);
		memberService.modifyMemberPw(map);
		
		return "redirect:/";
	}
	
	// 9. 회원정보 수정 폼 -> memberController.modifyMember -> memberService.getMemberByUK -> member/modifyMember.jsp
	@GetMapping("/member/modifyMember")
	public String modifyMember(Model model, HttpSession session) {
		System.out.println("@GetMapping(\"/member/modifyMember\")");
		System.out.println("[Method called] MemberController.modifyMember -> GET");
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println("[Session getters return] loginMember : " + loginMember);
		System.out.println(loginMember.getMemberId() + "<---- loginMember.getMemberId()");
		Member member = memberService.getMemberByUK(loginMember.getMemberId());
		System.out.println("[return] member : " + member);
		
		model.addAttribute("member", member);
		return "member/modifyMember";
	}
	
	// 10. 회원정보 수정 액션 -> MemberController.modifyMember -> MemberService.modifyMember -> MemberMapper.updateMember -> member/getMemberInfo.jsp
	@PostMapping("/member/modifyMember")
	public String modifyMember(Member member) {
		System.out.println("@PostMapping(\"/member/modifyMember\")");
		System.out.println("[Method called] MemberController.modifyMember -> POST");
		
		memberService.modifyMember(member);
		
		return "member/getMemberInfo";
		
	}
	// 11. 회원탈퇴 폼 MemberController.removeMember -> removeMember.jsp
	@GetMapping("/member/removeMember")
	public String removeMember() {
		System.out.println("@GetMapping(\"/member/removeMember\")");
		System.out.println("[Method called] MemberController.removeMember -> GET");
		
		return "member/removeMember";
	}
	// 12. 회원탈퇴 액션 MemberController.removeMember -> MemberService.removeMember -> MemberMapper.deleteMember -> 삭제성공 시 index.jsp / 실패시 원위치 removeMember.jsp
	@PostMapping("/member/removeMember")
	public String removeMember(Member member, HttpSession session) {
		System.out.println("@PostMapping(\"/member/removeMember\")");
		System.out.println("[Method called] MemberController.removeMember -> POST");
		int removeMemberResult = memberService.removeMember(member);
		
		if(removeMemberResult != 0) {
			
			session.invalidate();
			return "redirect:/";
		
		} else {
			return "/member/removeMember";
		}
	
	}	
	
	// * 팀과제 : 회원탈퇴 시 회원탈퇴를 먼저하는 게 아니고, 그 친구가 사용하던 ID를 다른 테이블(ex.MemberId 테이블 생성)에 저장하고,
	// 그 ID를 Member 테이블에서 완전히 삭제한다 -> 내일 15번 과제
	
	//2019-05-09 과제
	// 13. 아이디 찾기
	// 14. 비밀번호 찾기
	// 15. member_out_id 테이블 생성 후 탈퇴시 사용ID 저장 : 트랜잭션 처리
	
	
}
