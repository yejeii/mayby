package com.mayby.admin.vo;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository
public class AdminVO {

	private String a_id;
	private String a_pw;
	private String a_name;
	private String a_email;
	private String a_phone;
}
