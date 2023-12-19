package com.study.springboot.member.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.study.springboot.member.service.WrongIdPasswordException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
public class Member {
    private Long mno;
    private String name;
    private String password;
    private String phone;
    private LocalDateTime hiredate;
    private String job;
    private Integer deptno;
    private String dname;
    private String loc;
    
    // 검색 필터
    private String type;
    private String keyword;
    
    public Member(Long mno, String name, String password, String phone, LocalDateTime hiredate, String job, Integer deptno) {
        this.mno = mno;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job = job;
        this.deptno = deptno;

        		
    }
    
    
    public String getFormattedHiredate() {
        if (hiredate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return hiredate.format(formatter);
        }
        return null; // 혹은 다른 기본값을 반환할 수 있습니다.
    }
    
    

    // setHiredate 메소드를 수정
    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }

    // setDepartment 메소드를 수정
    public void setDepartment(Department department) {
        this.deptno = department.getDeptno();
    }
    


    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword)) {
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }

    public void setMno(Long mno) {
        this.mno = mno;
    }
}
