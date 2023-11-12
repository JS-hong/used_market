package joongo.vo;

import java.util.Date;

public class ChatVO {
	private String chat_room_id;	
	private int product_id;
	private String product_image;
	private String product_status;
	private String first_user_id;
	private String second_user_id;
	private int participant_id;
	private String user_id;
	private Date created_at;
	private String chat_message;
	
	
	public ChatVO() {
		super();
	}
	public ChatVO(String chat_room_id, int product_id, String product_image, String product_status, String first_user_id,
			String second_user_id, int participant_id, String user_id, Date created_at, String chat_message) {
		super();
		this.chat_room_id = chat_room_id;
		this.product_id = product_id;
		this.product_image = product_image;
		this.product_status = product_status;
		this.first_user_id = first_user_id;
		this.second_user_id = second_user_id;
		this.participant_id = participant_id;
		this.user_id = user_id;
		this.created_at = created_at;
		this.chat_message = chat_message;
	}
	public String getChat_room_id() {
		return chat_room_id;
	}
	
	public void setChat_room_id(String chat_room_id) {
		this.chat_room_id = chat_room_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getProduct_status() {
		return product_status;
	}
	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}
	public String getFirst_user_id() {
		return first_user_id;
	}
	public void setFirst_user_id(String first_user_id) {
		this.first_user_id = first_user_id;
	}
	public String getSecond_user_id() {
		return second_user_id;
	}
	public void setSecond_user_id(String second_user_id) {
		this.second_user_id = second_user_id;
	}
	public int getParticipant_id() {
		return participant_id;
	}
	public void setParticipant_id(int participant_id) {
		this.participant_id = participant_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getChat_message() {
		return chat_message;
	}
	public void setChat_message(String chat_message) {
		this.chat_message = chat_message;
	}
	
	
	
}
