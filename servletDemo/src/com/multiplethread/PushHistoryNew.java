package com.multiplethread;

public class PushHistoryNew {
	private String pushId;
	private String postId;
	private String pushTime;
	private String message;
	private String summary;
	private String userId;
	private int subscriptionId;
	private int status;
	
	@Override
	public String toString() {
		return "PushHistory [pushId=" + pushId + ", postId="+postId+", pushTime=" + pushTime
				+ ", message=" + message + ", summary=" + summary + ", userId="
				+ userId + ", subscriptionId=" + subscriptionId + ", status="
				+ status + "]";
	}
	public String getPushId() {
		return pushId;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public void setPushId(String pushId) {
		this.pushId = pushId;
	}
	public String getPushTime() {
		return pushTime;
	}
	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
