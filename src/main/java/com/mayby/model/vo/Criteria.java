package com.mayby.model.vo;

import lombok.Data;

/*
 * 쿼리문에 생성에 필요로 한 데이터를 전달하기 위한 VO
 * 지정한 개수와 검색 조건에 따라서 테이블 데이터를 출력하는 쿼리를 실행하는 데 필요로 한 데이터의 모임
 * MySQL 쿼리의 경우 limit 활용 -> skip 변수 추가하여 생성자에서 해당 변수의 값을 초기화 되게 함
 */
@Data
public class Criteria {

	// 현재 페이지 번호
	private int pageNm;

	// 페이지 표시 개수
	private int amount;
	
	// 페이지 스킵
	private int skip;

	// 검색 타입 - 사용자가 어떠한 검색을 하는지에 대한 데이터를 저장
	/* 상품 제목 검색 - "T"
	 * 카테고리 검색 - "C"
	 * 사용자가 어떠한 검색을 하냐에 따라 앞선 String 데이터의 조합을 저장
	 * "상품 제목 + 카테고리" 조건의 검색을 할 경우 "TC"
	 */
	private String type;

	// 검색 키워드
	private String keyword;
	
	// 카테고리 코드 - 사용자가 요청하는 카테고리 번호 저장
	private String cateCode;
	
	public void setPageNm(int pageNm) {
		this.pageNm = pageNm;
		this.skip = (pageNm -1) * this.amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
		this.skip = (this.pageNm -1) * amount;
	}

	// 기본생성자
	public Criteria() {
		this(1,10);
	}
	
	/**
	 * @param pageNm
	 * @param amount
	 */
	public Criteria(int pageNm, int amount) {
		this.pageNm = pageNm;
		this.amount = amount;
		this.skip = (pageNm-1) * amount;
	}
	
	// 검색 타입 데이터 배열 반환
	// 뷰로부터 전달받은 type 값을 String의 split() 메서드로 String 배열 형태로 Mapper에 전달
	//  type 변수에 저장된 값을 단지 배열 형태로 가져만 오면 돼서
	// typeArr 변수와 Setter 메서드를 따로 사용할 상황이 없으므로 Getter 메서드만 작성
	public String[] getTypeArr() {
		return type == null? new String[] {} : type.split("");
	}
	

}
