package it.unical.linstagram.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;

public class NotificationDTO {

	private String userPhoto;
	private String userName;
	private String context;
	private String urlPost;
	private String date;
	private boolean isPrivate;
	private boolean alreadyFollowing;
	private boolean alreadyFollowed;
	private boolean isRequest;

	public NotificationDTO(Notification notification, boolean alreadyFollowing, boolean alreadyFollowed,
			boolean isRequest) {
		this.userPhoto = notification.getUserFrom().getPhotoProfile();
		this.userName = notification.getUserFrom().getUsername();
		this.date = calculateElapsedTime(notification.getDate());
		this.alreadyFollowing = alreadyFollowing;
		this.alreadyFollowed = alreadyFollowed;
		this.isRequest = isRequest;
		this.isPrivate = notification.getUserTo().isPrivateProfile();
		if (notification.getType().equals(NotificationType.COMMENT)) {
			this.context = String.format("commented your post: %s", notification.getComment().getContent());
			this.urlPost = notification.getPost().getMedia().get(0).getUrl();
		} else if (notification.getType().equals(NotificationType.LIKE)) {
			this.context = "liked your post";
			this.urlPost = notification.getPost().getMedia().get(0).getUrl();
		} else {
			if (!this.alreadyFollowed) {
				this.context = "Asks to follow you";
			} else {
				this.context = "started following you";
			}

			this.urlPost = null;
		}

	}

	private String calculateElapsedTime(Calendar date) {
		Date currentTime = Calendar.getInstance().getTime();
		Date timePost = date.getTime();
		long diffInMillies = currentTime.getTime() - timePost.getTime();

		long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
		long hours = TimeUnit.MINUTES.toHours(minutes);
		long days = TimeUnit.HOURS.toDays(hours);

		if (days != 0) {
			if (days > 30) {
				long month = days % 30;
				if (month > 12) {
					long years = month % 12;
					return years + " Y";
				}
				return month + " M";
			}
			return days + " D";
		}

		else if (hours != 0)
			return hours + " h";
		else if (minutes != 0)
			return minutes + " m";

		return "";
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

	public String getDate() {
		return date;
	}

	public void setDate(String data) {
		this.date = data;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public boolean isAlreadyFollowing() {
		return alreadyFollowing;
	}

	public void setAlreadyFollowing(boolean alreadyFollowing) {
		this.alreadyFollowing = alreadyFollowing;
	}

	public boolean isAlreadyFollowed() {
		return alreadyFollowed;
	}

	public void setAlreadyFollowed(boolean alreadyFollowed) {
		this.alreadyFollowed = alreadyFollowed;
	}

	public boolean isRequest() {
		return isRequest;
	}

	public void setRequest(boolean isRequest) {
		this.isRequest = isRequest;
	}

}
