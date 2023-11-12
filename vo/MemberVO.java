package joongo.vo;

import java.sql.Date;

public class MemberVO {

	private String mid;
	private String mpw;
	private String mname;
	private String mbirth;
	private String email;
	private String addr;
	private String addrdetail;
	private String mphone;
	private String gender;
	private String mimage;
	private Date mdate;
	private double manner;
	private String mcondition;
	
	public String getMcondition() {
		return mcondition;
	}
	public void setMcondition(String mcondition) {
		this.mcondition = mcondition;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMbirth() {
		return mbirth;
	}
	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMimage() {
		return mimage;
	}
	public void setMimage(String mimage) {
		this.mimage = mimage;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	public double getManner() {
		return manner;
	}
	public void setManner(double manner) {
		this.manner = manner;
	}
	
	public MemberVO(String mid, String mpw, String mname, String mbirth, String email, String addr, String addrdetail,
			String mphone, String gender, String mimage, Date mdate, double manner, String mcondition) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mbirth = mbirth;
		this.email = email;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.mphone = mphone;
		this.gender = gender;
		this.mimage = mimage;
		this.mdate = mdate;
		this.manner = manner;
		this.mcondition = mcondition;
	}
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
