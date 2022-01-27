package jin.mv.model;

import java.sql.Date;

public class boardDTO {
	
	private int seq;
	private String name;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	
	public boardDTO() {
		super();
	}
	public boardDTO(int seq, String name, String email, String subject, String content, Date rdate) {
		this.seq = seq;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
