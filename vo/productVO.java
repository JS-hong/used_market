package joongo.vo;

import java.sql.Date;

public class productVO {
	
	private int pid;//상품 게시물 아이디
	private	String sellerid;//판매자 아이디
	private	String buyerid;//구매자 아이디
	private	int cid;//카테고리
	private	int price;//가격
	private	String ptitle;//제목
	private	String pcontent;//게시물 내용
	private	String plocation;//지역정보
	private	int pstate;//물품의 상태(삭제상품,예약중, 판매중, 판매완료)
	private int viewcnt;//조회수
	private int chatcnt;//채팅수
	private Date pdate;//생성 일자
	private String thumbnail;
	private String cname;
	public productVO() {
		super();
	}
	
	
	public productVO(int pid, String sellerid, String buyerid, int cid, int price, String ptitle, String pcontent,
			String plocation, int pstate, int viewcnt, int chatcnt, Date pdate, String thumbnail,String cname) {
		super();
		this.pid = pid;
		this.sellerid = sellerid;
		this.buyerid = buyerid;
		this.cid = cid;
		this.price = price;
		this.ptitle = ptitle;
		this.pcontent = pcontent;
		this.plocation = plocation;
		this.pstate = pstate;
		this.viewcnt = viewcnt;
		this.chatcnt = chatcnt;
		this.pdate = pdate;
		this.thumbnail = thumbnail;
		this.cname=cname;
	}

	
	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public int getChatcnt() {
		return chatcnt;
	}
	public void setChatcnt(int chatcnt) {
		this.chatcnt = chatcnt;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}
	public String getPlocation() {
		return plocation;
	}
	public void setPlocation(String plocation) {
		this.plocation = plocation;
	}
	public int getPstate() {
		return pstate;
	}
	public void setPstate(int pstate) {
		this.pstate = pstate;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	
	
}
