package com.mayby.model.vo;

import lombok.Data;

@Data
public class MMCriteria {

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
		
		public void setPageNm(int pageNm) {
			this.pageNm = pageNm;
			this.skip = (pageNm -1) * this.amount;
		}
		
		public void setAmount(int amount) {
			this.amount = amount;
			this.skip = (this.pageNm -1) * amount;
		}

		// 기본생성자
		public MMCriteria() {
			this(1,10);
		}
		
		/**
		 * @param pageNm
		 * @param amount
		 */
		public MMCriteria(int pageNm, int amount) {
			this.pageNm = pageNm;
			this.amount = amount;
			this.skip = (pageNm-1) * amount;
		}
		
}
