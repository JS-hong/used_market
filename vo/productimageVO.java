package joongo.vo;

import java.sql.Date;

public class productimageVO {
	private int pimgid;//이미지 번호id  seq
	private int pid;//상품아이디
	private String imageurl;//이미지URL
	private Date pidate;//이미지 생성날짜
	
	public productimageVO() {
		super();
		
	}
	public productimageVO(int pimgid, int pid, String imageurl, Date pidate) {
		super();
		this.pimgid = pimgid;
		this.pid = pid;
		this.imageurl = imageurl;
		this.pidate = pidate;
	}
	
	public int getPimgid() {
		return pimgid;
	}
	public void setPimgid(int pimgid) {
		this.pimgid = pimgid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public Date getPidate() {
		return pidate;
	}
	public void setPidate(Date pidate) {
		this.pidate = pidate;
	}
	
}
