package com.omg.cmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageDTO {
	private final Logger LOG = LoggerFactory.getLogger(PageDTO.class);
	private int startPage; // 시작페이지
	private int endPage; // 끝 페이지
	private boolean prev, next; // 이전, 다음버튼 체크

	private int total; // 총 데이터 수
	private Criteria cri;

	// 페이지 계산
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;

		this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;

		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}

		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	public PageDTO() {}

	// Getter, ToString
	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getTotal() {
		return total;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageDTO [startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next
				+ ", total=" + total + ", cri=" + cri + "]";
	}

}
