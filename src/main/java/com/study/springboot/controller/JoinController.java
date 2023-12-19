package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.member.dao.MemberDao;
import com.study.springboot.member.model.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/joinForm")
@Slf4j
public class JoinController {
	
    @Autowired
    private MemberDao memberDao;
    
    
    
    @GetMapping
    public String showRegisterForm(Model model) {
    	
    	Member member = new Member();
    	
    	Long nextMno = (long) memberDao.getNextMno();
    	member.setMno(nextMno);
    	
    	
        model.addAttribute("member", member);
        return "/member/joinForm"; // 회원 등록 폼을 표시할 뷰 페이지의 이름
    
    
    }

    @PostMapping
    public String registerMember(Member member) {
        memberDao.insertMember(member);
        return "redirect:/memberList";
    }
	
}
