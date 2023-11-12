package joongo.vo;

import java.sql.Date;

public class favoriteproductVO {
	private int fpnum;//관심목록 pk  seq
	private String mid;//맴버아이디fk
	private int pid;//물품아이디fk
	private int isvaild;//유효여부default true->1 
	private Date fpdate;//생성일시 sysdate
	
	
	public favoriteproductVO(int fpnum, String mid, int pid, int isvaild, Date fpdate) {
		super();
		this.fpnum = fpnum;
		this.mid = mid;
		this.pid = pid;
		this.isvaild = isvaild;
		this.fpdate = fpdate;
	}
	public favoriteproductVO() {
		super();
	}
	public int getFpnum() {
		return fpnum;
	}
	public void setFpnum(int fpnum) {
		this.fpnum = fpnum;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int isIsvaild() {
		return isvaild;
	}
	public void setIsvaild(int isvaild) {
		this.isvaild = isvaild;
	}
	public Date getFpdate() {
		return fpdate;
	}
	public void setFpdate(Date fpdate) {
		this.fpdate = fpdate;
	}
	
}
