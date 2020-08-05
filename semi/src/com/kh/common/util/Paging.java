package com.kh.common.util;

public class Paging {
	
	// request로 받아와야 하는 세  가지.
	// 현재 페이지
	private int currentPage;
	// 전체 게시물 갯수
	private int total;
	// 페이지의 수
	private int cntPerPage;
	
	// 블록 안의 페이지 넘버 수
	private int blockCnt = 5;
	// 블록 시작 번호
	private int blockStart;
	// 블록 끝 번호
	private int blockEnd;
	// 전체 페이지 수
	private int lastPage;

	// sql에서 사용할 시작 값
	private int start;
	// sql에서 사용할 끝 값
	private int end;
	
	public Paging() {
		
	}

	public Paging(int total, int currentPage,int cntPerPage) {
		
		this.total = total;
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		// 추가 코드
		calAllPage(total, cntPerPage); 
		calBlockEnd(blockEnd, blockCnt);
		calBlockStart(currentPage, blockCnt);
		calEnd(currentPage,cntPerPage);
		calStart();
	}
	
	// 전체 페이지 수를 구하는 메소드 (전체 게시물 수, 한 페이지당 게시물 개수)
	public void calAllPage(int total, int cntPerPage) {
		
		lastPage = (total-1)/cntPerPage + 1; 
		
	}
	
	// 블럭당 끝페이지 넘버 구하기
	   public void calBlockEnd(int currentPage, int blockCnt) {
	      blockEnd = (((currentPage - 1) / blockCnt + 1) * blockCnt);
	      if (lastPage < blockEnd) {
	         blockEnd = lastPage;
	      }
	   }

	// 블럭당 시작페이지 넘버 구하기
	   public void calBlockStart(int blockEnd, int blockCnt) {
	      blockStart = blockEnd - blockCnt + 1;
	      if (blockStart < 1) {
	         blockStart = 1;
	      }
	   }
		
	//DB쿼리에서 사용할 시작 값 구하기
	public void calEnd(int currentPage, int cntPerPage) {
		end = currentPage * cntPerPage;
	}
	
	public void calStart() {
		start = end - cntPerPage + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getBlockCnt() {
		return blockCnt;
	}

	public void setBlockCnt(int blockCnt) {
		this.blockCnt = blockCnt;
	}

	public int getBlockStart() {
		return blockStart;
	}

	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Paging [currentPage=" + currentPage + ", total=" + total + ", cntPerPage=" + cntPerPage + ", blockCnt="
				+ blockCnt + ", blockStart=" + blockStart + ", blockEnd=" + blockEnd + ", lastPage=" + lastPage
				+ ", start=" + start + ", end=" + end + "]";
	}
	
	
	
		
		
	}
	
	
	


