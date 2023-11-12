package joongo.vo;

import java.sql.Timestamp;

public class SaleReviewsVO {
	
	private int sale_review_id; //후기글 번호
	private int pid; // 물품 ID
	private String reviewer_id; // 리뷰 작성자 ID
	private String reviewee_id; // 리뷰 대상자 ID
	private boolean sale_review_type; // 거래 후기 타입 ( 판매자, 구매자 )
	private String content; // 후기 내용
	private Timestamp created_at; // 생성 일자
	private Timestamp updated_at; // 수정 일자
	private String ptitle; // 상품 글제목
	private int score; //매너점수
	
	
	public SaleReviewsVO(int sale_review_id, int pid, String reviewer_id, String reviewee_id, boolean sale_review_type, String content,
			Timestamp created_at, Timestamp updated_at,String ptitle,int score) {
		super();
		this.sale_review_id = sale_review_id;
		this.pid = pid;
		this.reviewer_id = reviewer_id;
		this.reviewee_id = reviewee_id;
		this.sale_review_type = sale_review_type;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.ptitle = ptitle;
		this.score = score;
	}
	public int getSale_review_id() {
		return sale_review_id;
	}


	public void setSale_review_id(int sale_review_id) {
		this.sale_review_id = sale_review_id;
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getReviewer_id() {
		return reviewer_id;
	}


	public void setReviewer_id(String reviewer_id) {
		this.reviewer_id = reviewer_id;
	}


	public String getReviewee_id() {
		return reviewee_id;
	}


	public void setReviewee_id(String reviewee_id) {
		this.reviewee_id = reviewee_id;
	}


	public boolean isSale_review_type() {
		return sale_review_type;
	}


	public void setSale_review_type(boolean sale_review_type) {
		this.sale_review_type = sale_review_type;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Timestamp getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}


	public Timestamp getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}


	public String getPtitle() {
		return ptitle;
	}


	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	
}
