package joongo.vo;

import java.sql.Timestamp;
import java.util.Date;

public class ReportVO {
	private int rid;
    private Integer pid; // Integer로 선언하여 null 허용
    private Integer chatRoomId; // Integer로 선언하여 null 허용
    private Timestamp rdate;
    private String firstUserId;
    private String secondUserId;
    private int reason;
    private int processing;
    private String OtherDescription;
    
	public String getOtherDescription() {
		return OtherDescription;
	}
	public void setOtherDescription(String otherDescription) {
		OtherDescription = otherDescription;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(Integer chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	public String getFirstUserId() {
		return firstUserId;
	}
	public void setFirstUserId(String firstUserId) {
		this.firstUserId = firstUserId;
	}
	public String getSecondUserId() {
		return secondUserId;
	}
	public void setSecondUserId(String secondUserId) {
		this.secondUserId = secondUserId;
	}
	public int getReason() {
		return reason;
	}
	public void setReason(int reason) {
		this.reason = reason;
	}
	public int getProcessing() {
		return processing;
	}
	public void setProcessing(int processing) {
		this.processing = processing;
	}
	
	}


