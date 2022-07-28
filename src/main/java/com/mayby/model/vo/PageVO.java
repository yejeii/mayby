package com.mayby.model.vo;

import lombok.Data;

/*
 * 페이지 이동 인터페이스를 출력하는데 필요한 데이터의 모임
 */
@Data
public class PageVO {
	
	// 페이지 시작 번호
	private int pageStart;
	
	// 페이지 끝 번호
	private int pageEnd;
	
	// 이전, 다음 버튼 존재 유무
	private boolean next, prev;
	
	// 행 전체 개수
	private int total;
	
	// 현재페이지 번호(pageNm), 행 표시 수(amount), 검색 키워드(keyword), 검색 종류(type)
	private Criteria cri;

	// 생성자(각 변수 값 초기화)
	public PageVO(Criteria cri, int total) {
		
		// 초기화
		this.total = total;
		this.cri = cri;
		
		// 페이지 끝 번호
		this.pageEnd = (int)(Math.ceil(cri.getPageNm()/10.0)) * 10;
		
		// 페이지 시작 번호
		this.pageStart = this.pageEnd - 9;
		
		// 전체 마지막 페이지 번호
		int realEnd = (int)(Math.ceil(total*1.0/cri.getAmount()));
		
		// 페이지 끝 번호 유효성 검사
		if(realEnd < pageEnd) {
			this.pageEnd = realEnd;
		}
		
		// 이전 버튼 값 초기화
		this.prev = this.pageStart > 1;
		
		// 다음 버튼 값 초기화
		this.next = this.pageEnd < realEnd;
	}
	
	

}
