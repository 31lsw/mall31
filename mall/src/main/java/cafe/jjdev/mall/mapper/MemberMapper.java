package cafe.jjdev.mall.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cafe.jjdev.mall.vo.Member;

@Mapper
public interface MemberMapper {
	
	// 비번찾기 시 아이디, 이메일주소 받아 비밀번호 유무 여부 조회
	public int selectCountMemberPwByIdAndEmail(Member member);
		
	// 임시 비밀번호 변경 후 해당 이메일로 발송
	public int updateTempMemberPw(Member member);
	
	// ID 중복체크 메서드
	public int selectCountMemberIdDuplicated(String memberId);
	
	// 로그인 액션 : 로그인 체크 시 해당 멤버 조회
	public Member selectMember(Member member); //memberId, memberPw
	// 회원 가입 액션
	public int insertMember(Member member);
	// 비밀번호만 변경
	public int updateMemberPw(Map<String, Object> map);
	// 회원정보 수정폼
	public Member selectMemberByUK(String memberId); //memberId
	// 회원정보 수정 액션
	public int updateMember(Member member); //memberId, memberPw
	// 회원탈퇴
	public int deleteMember(Member member); //memberId, memberPw
}
