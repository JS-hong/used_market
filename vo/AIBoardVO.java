package joongo.vo;

import java.sql.Timestamp;

//문의사항 Any Inquiries
public class AIBoardVO {
	
	private int any_inquiries_id; //문의사항 게시글ID
	private String uid; //게시글 작성자ID
	private Timestamp created_at; //생성일자
	private Timestamp updated_at; //수정일자
	private String content; //내용
	private Integer comment_id; // 댓글인 경우 해당 댓글이 속한 게시물의 번호를 나타냄 (NULL이면 게시물)
	private String mname; //닉네임 ( member 테이블에서 OUTER JOIN )
	private int hit; // 조회수
	private String subject; // 게시물 제목
	private String ip; // 아이피 주소
	
	
	

	//생성자
	public AIBoardVO(int any_inquiries_id, String uid, Timestamp created_at, Timestamp updated_at, String content, Integer comment_id,
			String mname,int hit, String subject, String ip) {
		super();
		this.any_inquiries_id = any_inquiries_id;
		this.uid = uid;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.content = content;
		this.comment_id = comment_id;
		this.mname = mname;
		this.hit = hit;
		this.subject = subject;
		this.ip = ip;
	}
	
	
	//getter&&setter
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public int getAny_inquiries_id() {
		return any_inquiries_id;
	}


	public void setAny_inquiries_id(int any_inquiries_id) {
		this.any_inquiries_id = any_inquiries_id;
	}


	public int getHit() {
		return hit;
	}


	public void setHit(int hit) {
		this.hit = hit;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getIp() {
		return ip;
	}


	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
