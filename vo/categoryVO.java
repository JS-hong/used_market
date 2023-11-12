package joongo.vo;

import java.sql.Date;

public class categoryVO {
	private int cid;//pk seq
	private String cname;//카테고리 명들
	private Date cdate;
	public categoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public categoryVO(int cid, String cname, Date cdate) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.cdate = cdate;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	
}
