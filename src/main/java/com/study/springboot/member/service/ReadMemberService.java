package com.study.springboot.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.member.dao.MemberDao;
import com.study.springboot.member.model.Member;

@Service
public class ReadMemberService {
	
	@Autowired
	MemberDao memberDao;
	
	public Member readMember(long mno) {
		
		Member member = memberDao.selectByMno(mno);
		member.setDname(memberDao.selectByDeptno(member.getDeptno()).getDname());
		member.setLoc(memberDao.selectByDeptno(member.getDeptno()).getLoc());
		return member;
	}
	
}
