package joongo.vo;

import java.sql.Timestamp;
import java.util.Date;

public class BlacklistVO {
private int bid;
private int rid;
private String seconduserid;
private Timestamp bperiod;
private int breason;
private Date endate;
public int getBid() {
	return bid;
}
public void setBid(int bid) {
	this.bid = bid;
}
public int getRid() {
	return rid;
}
public void setRid(int rid) {
	this.rid = rid;
}

public Timestamp getBperiod() {
	return bperiod;
}
public String getSeconduserid() {
	return seconduserid;
}
public void setSeconduserid(String seconduserid) {
	this.seconduserid = seconduserid;
}
public Date getEndate() {
	return endate;
}
public void setEndate(Date endate) {
	this.endate = endate;
}
public void setBperiod(Timestamp bperiod) {
	this.bperiod = bperiod;
}
public int getBreason() {
	return breason;
}
public void setBreason(int breason) {
	this.breason = breason;
}

}
