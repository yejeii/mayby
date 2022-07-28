package com.mayby.member.vo;

import java.sql.Timestamp;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Repository
@Data
public class MemberVO {
	
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_postcode;
	private String m_address;
	private String m_extraAddress;
	private String m_elseAddress;
	private String m_phone;
	private String m_email;
	private int m_coupon;
	private int m_point;
	private String m_grade;
	private Timestamp m_regdate;
	
}
