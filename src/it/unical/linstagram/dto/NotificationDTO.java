package it.unical.linstagram.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import it.unical.linstagram.model.Media.Media_Type;
import it.unical.linstagram.model.Notification;
import it.unical.linstagram.model.NotificationType;

public class NotificationDTO {

	private int idNotification;
	private String userPhoto;
	private String userName;
	private String context;
	private String urlPost;
	private int idPost;
	private String date;
	private boolean isVideo;
	private boolean isPrivateFrom;
	private boolean isPrivateTo;
	private boolean alreadyFollowing;
	private boolean alreadyFollowed;
	private boolean isRequestFrom;
	private boolean isRequestTo;

	public NotificationDTO(Notification notification, boolean alreadyFollowing, boolean alreadyFollowed,
			boolean isRequestTo, boolean isRequestFrom) {
		this.idNotification = notification.getId();
		this.userPhoto = notification.getUserFrom().getPhotoProfile();
		this.userName = notification.getUserFrom().getUsername();
		this.date = calculateElapsedTime(notification.getDate());
		this.alreadyFollowing = alreadyFollowing;
		this.alreadyFollowed = alreadyFollowed;
		this.isRequestTo = isRequestTo;
		this.isRequestFrom = isRequestFrom;
		this.isPrivateTo = notification.getUserTo().isPrivateProfile();
		this.isPrivateFrom = notification.getUserFrom().isPrivateProfile();
		if (notification.getPost() != null) {
			this.urlPost = notification.getPost().getMedia().get(0).getUrl();
			this.idPost = notification.getPost().getId();
			this.isVideo = notification.getPost().getMedia().get(0).getType() == Media_Type.VIDEO;
		}
		if (notification.getType().equals(NotificationType.COMMENT)) {
			this.context = "commented your post";

		} else if (notification.getType().equals(NotificationType.LIKE)) {
			this.context = "liked your post";
		} else if (notification.getType().equals(NotificationType.TAG)) {
			this.context = "tagged you";
		} else {
			if (!this.alreadyFollowed) {
				this.context = "Asks to follow you";
			} else {
				this.context = "started following you";
			}
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

	public int getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
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

	public boolean isVideo() {
		return isVideo;
	}

	public void setVideo(boolean isVideo) {
		this.isVideo = isVideo;
	}

	public boolean isPrivateFrom() {
		return isPrivateFrom;
	}

	public void setPrivateFrom(boolean isPrivateFrom) {
		this.isPrivateFrom = isPrivateFrom;
	}

	public boolean isPrivateTo() {
		return isPrivateTo;
	}

	public void setPrivateTo(boolean isPrivateTo) {
		this.isPrivateTo = isPrivateTo;
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

	public boolean isRequestFrom() {
		return isRequestFrom;
	}

	public void setRequestFrom(boolean isRequestFrom) {
		this.isRequestFrom = isRequestFrom;
	}

	public boolean isRequestTo() {
		return isRequestTo;
	}

	public void setRequestTo(boolean isRequestTo) {
		this.isRequestTo = isRequestTo;
	}

	public int getIdPost() {
		return idPost;
	}

	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}

}
