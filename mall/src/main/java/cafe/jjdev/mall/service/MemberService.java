package cafe.jjdev.mall.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cafe.jjdev.mall.mapper.MemberMapper;
import cafe.jjdev.mall.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	JavaMailSender javaMailSender;
	
	
	// 아이디와 이메일 체크 후 정보 일치하면 임시비밀번호 발송
	@Transactional
	public String findMemberPwByIdAndEmail(Member member) {
		System.out.println("[Method called] MemberService.getCountMemberPwByNameAndEmail");
		System.out.println("[Param] member : " + member);
		//return 초기값
		String resultSendTempMemberPw = "입력하신 내용과 일치하는 정보가 없습니다";
		// 입력한 아이디와 이메일 정보에 해당하는 비번 정보 조회
		int selectCountMemberPwByIdAndEmailResult = memberMapper.selectCountMemberPwByIdAndEmail(member);
		System.out.println("아이디와 이메일 정보에 일치하는 비번조회 쿼리실행 결과 : " + selectCountMemberPwByIdAndEmailResult);
		if(selectCountMemberPwByIdAndEmailResult != 0) { //집계count쿼리 실행시 0이 아니면 정보 일치
			// 입력정보가 일치하면 임시 비밀번호 생성
			String tempMemberPw = "";
			for (int i = 0; i < 12; i++) {
				tempMemberPw += (char) ((Math.random() * 26) + 97);
			}
			// 임시비번값 확인
			System.out.println("tempMemberPw :" + tempMemberPw);
			// 멤버객체에 셋팅
			member.setMemberPw(tempMemberPw);
			// 기존 비밀번호를 임시 비밀번호로 변경
			memberMapper.updateTempMemberPw(member);
			System.out.println("member : " + member);
			// 비밀번호 변경 메일 발송
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(member.getMemberEmail());//보낼 대상
	        message.setSubject("임시 비밀번호 알려드립니다.");//제목
	        message.setText("회원님의 임시비밀번호는 ["+tempMemberPw+"]입니다.");//내용
	        try{//예외처리
	        	javaMailSender.send(message);
	        }catch(MailException es){
	        	System.out.println("----------- 메일 발송 시 예외발생-------------");
	        	es.printStackTrace();
	        }
	        resultSendTempMemberPw = "이메일로 임시 비밀번호를 발송하였습니다.";
	        System.out.println(resultSendTempMemberPw);
		}
		
		return resultSendTempMemberPw;
	}
	
	
	//ID 중복체크 액션
	public boolean checkIdDuplicated(String memberId) {
		
		int checkIdDuplicated = memberMapper.selectCountMemberIdDuplicated(memberId);  
		
		boolean checkIdDuplicatedResult = false;
		if(checkIdDuplicated == 0) {
			checkIdDuplicatedResult = true; //중복 아님
		}
		return checkIdDuplicatedResult;
	}
	
	
	//로그인 액션
	public Member getMember(Member member) {
		System.out.println("[Method called] MemberService.getMember 메서드 호출");
		System.out.println("[Param] member : " + member);
		Member loginMember = memberMapper.selectMember(member);
		System.out.println("[return] loginMember : " + loginMember);
		return loginMember;
	}
	
	//회원 가입 액션
	public int addMember(Member member) {
		System.out.println("[Method called] MemberService.addMember 메서드 호출");
		System.out.println("[Param] member : " + member);
		int insertMemberResult = memberMapper.insertMember(member);
		System.out.println("[return] insertMemberResult 쿼리실행 결과 : " + insertMemberResult);
		
		return insertMemberResult;
	}
	
	// 개인정보 확인
	public Member getMemberInfo(String memberId) {
		//그냥 아래의 getMemberByUK를 재활용
		Member member = getMemberByUK(memberId);
		
		return member;
	}
	
	//비밀번호 변경 액션
	public void modifyMemberPw(Map<String, Object> map) {
		System.out.println("[Method called] MemberService.modifyMemberPw 메서드 호출");
		System.out.println("[Param] memberId : " + map.get("memberId")); //해당 아이디
		System.out.println("[Param] memberPw : " + map.get("memberPw")); //변경 비번
		System.out.println("[Param] memberCurrentPw : " + map.get("memberPwmemberCurrentPw")); //기존 비번
		
		System.out.println(map.get("memberId"));
		System.out.println(map.get("memberPw"));
		System.out.println(map.get("memberCurrentPw"));
		
		int updateMemberPwResult = memberMapper.updateMemberPw(map);
		System.out.println("updateMemberPwResult 쿼리실행 결과 : " + updateMemberPwResult);
	}
	
	//회원정보 수정 폼
	public Member getMemberByUK(String memberId) {
		System.out.println("[Method called] MemberService.getMemberByUK 메서드 호출");
		System.out.println("[Param] memberId : " + memberId);
		Member member = memberMapper.selectMemberByUK(memberId);
		System.out.println("[return] member : " + member);
		return member;
	}
	
	//회원정보 수정 액션
	public void modifyMember(Member member) {
		System.out.println("[Method called] MemberService.modifyMember 메서드 호출");
		System.out.println("[Param] member : " + member);
		int updateMemberResult = memberMapper.updateMember(member);
		System.out.println("[return] updateMemberResult 쿼리실행 결과 : " + updateMemberResult);
	}
	
	//회원탈퇴 액션
	public int removeMember(Member member) {
		System.out.println("[Method called] MemberService.removeMember 메서드 호출");
		System.out.println("[Param] member : " + member);
		int deleteMemberResult = memberMapper.deleteMember(member);
		System.out.println("[return] deleteMemberResult 쿼리실행 결과 : " + deleteMemberResult);
		
		return deleteMemberResult;
	}
}
