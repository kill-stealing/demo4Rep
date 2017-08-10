package com.multiplethread;

import com.multiplethread.dao.Dao;




public class PushInfo {
	
	//a1.if_send,a1.id,a1.post_id,a2.post_title,a4.term_id,a6.name,a5.user_id
	private int ifSend;
	private int id;
	private int postId;
	private String postTitle;
	private int termId;
	private String name;
	private String userId;
	private int counts;
	private int num;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	private Dao dao;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public int getIfSend() {
		return ifSend;
	}
	public void setIfSend(int ifSend) {
		this.ifSend = ifSend;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public int getTermId() {
		return termId;
	}
	public void setTermId(int termId) {
		this.termId = termId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
