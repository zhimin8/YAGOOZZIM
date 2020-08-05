/**
 * @PackageName: common.util
 * @FileName : Paging.java
 * @Date : 2020. 4. 20.
 * @���α׷� ���� : 
 * @author 
 */
package common.util;

/**
 * @PackageName: common.util
 * @FileName : Paging.java
 * @Date : 2020. 4. 20.
 * @���α׷� ���� : 
 * @author 
 */
public class Paging {
	
	//���� ������
	private int currentPage;
	//��ü �Խù� ��
	private int total;
	//�������� �Խù� ��
	private int cntPerPage;
	
	//��� ���� �������ѹ� ��
	private int blockCnt = 5;
	//��� ���� ��ȣ
	private int blockStart;
	//��� �� ��ȣ
	private int blockEnd;
	//��ü ������ ��
	private int lastPage;
	
	//sql���� ����� ���� ��
	private int start;
	//sql���� ����� �� ��
	private int end;
	
	public Paging() {
		
	}
	
	public Paging(int total, int currentPage, int cntPerPage) {
		
		this.total = total;
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		//
		calAllPage(total,cntPerPage);
		calBlockEnd(currentPage, blockCnt);
		calBlockStart(blockEnd, blockCnt);
		calEnd(currentPage, cntPerPage);
		calStart();
	}
	
	//��ü ������ ���� ���ϱ�
	public void calAllPage(int total, int cntPerPage) {
		//total/cntPerPage �� �� �ø�ó�� �� �Ͱ� ����.
		lastPage = (total-1)/cntPerPage + 1;
	}
	
	//����  �� ������ �ѹ� ���ϱ�
	public void calBlockEnd(int currentPage, int blockCnt) {
		
		blockEnd = ((currentPage-1)/blockCnt+1) * blockCnt;
		if(lastPage < blockEnd) {
			blockEnd = lastPage;
		}
	}
	
	//���� ���� ������ �ѹ� ���ϱ�
	public void calBlockStart(int blockEnd, int blockCnt) {
		blockStart = ((currentPage-1)/blockCnt) * blockCnt+1;
		if(blockStart  <  1) {
			blockStart = 1;
		}
	}
	
	//DB�������� ����� ���� ���ϱ�
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
