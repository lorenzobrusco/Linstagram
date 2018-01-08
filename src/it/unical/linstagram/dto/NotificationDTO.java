package it.unical.linstagram.dto;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;

public class NotificationDTO {

	private String userPhoto;
	private String userName;
	private String context;
	private String urlPost;
	private String data;

	public NotificationDTO(Notification notification) {
		this.userPhoto = notification.getUserFrom().getPhotoProfile();
		this.userName = notification.getUserFrom().getUsername();
		this.data = notification.getDate().toString();
		if (notification.getType().equals(NotificationType.COMMENT)) {
			this.context = String.format("commented your post: %s",notification.getComment().getContent());
			this.urlPost = notification.getPost().getMedia().get(0).getUrl();
		} else if (notification.getType().equals(NotificationType.LIKE)) {
			this.context ="liked your post";
			this.urlPost = notification.getPost().getMedia().get(0).getUrl();
		} else {
			this.context ="started following you";
			this.urlPost = null;
		}

	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getUrlPost() {
		return urlPost;
	}

	public void setUrlPost(String urlPost) {
		this.urlPost = urlPost;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
