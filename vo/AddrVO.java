package joongo.vo;

public class AddrVO {
	
	private String mid;
	private String addr;
	private String addr2;
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public AddrVO(String mid, String addr, String addr2) {
		super();
		this.mid = mid;
		this.addr = addr;
		this.addr2 = addr2;
	}
	
	public AddrVO() {
		super();
		
	}
	
	
	
}
