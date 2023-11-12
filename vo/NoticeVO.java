package joongo.vo;

import java.sql.Timestamp;

public class NoticeVO {
private int nid;
private Timestamp updatedat;
private Timestamp createdat;
private String content;
private int hit;
public int getNid() {
	return nid;
}
public void setNid(int nid) {
	this.nid = nid;
}
public Timestamp getUpdatedat() {
	return updatedat;
}
public void setUpdatedat(Timestamp updatedat) {
	this.updatedat = updatedat;
}
public Timestamp getCreatedat() {
	return createdat;
}
public void setCreatedat(Timestamp createdat) {
	this.createdat = createdat;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getHit() {
	return hit;
}
public void setHit(int hit) {
	this.hit = hit;
}



}
