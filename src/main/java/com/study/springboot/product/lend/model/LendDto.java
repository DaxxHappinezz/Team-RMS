package com.study.springboot.product.lend.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LendDto {
	private Integer lend_no;
	private Integer reg_no;
	private Integer pno;
	private String pname;
	private Integer mno;
	private Date order_date;
	private Date fin_date;
}
