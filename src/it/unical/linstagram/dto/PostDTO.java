package it.unical.linstagram.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Media;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;

public class PostDTO {

	private int id;
	private User user;
	private Calendar postDate;
	private String content;

	private String elapsedTime;


	private List<Media> media = new ArrayList<Media>();

	private Set<User> likes = new HashSet<User>();
	
	private Set<Comment> comments = new HashSet<Comment>();

	private boolean likeUser;
	private boolean bookmarkUser;

	public PostDTO(Post post, boolean likeUser, boolean bookmarkUser) {
		this.id = post.getId();
		this.user = post.getUser();
		this.postDate = post.getPostDate();
		this.media = post.getMedia();
		this.likes = post.getLikes();
		this.content = getConvertedContent(post.getContent(), post.getTags(), post.getHashtags());
		this.setElapsedTime(calculateElapsedTime());

		this.comments = post.getComments();
		
		this.likeUser = likeUser;
		this.bookmarkUser = bookmarkUser;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getPostDate() {
		return postDate;
	}

	public void setPostDate(Calendar postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Media> getMedia() {
		return media;
	}

	public void setMedia(List<Media> media) {
		this.media = media;
	}

	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public boolean isLikeUser() {
		return likeUser;
	}

	public void setLikeUser(boolean likeUser) {
		this.likeUser = likeUser;
	}

	public boolean isBookmarkUser() {
		return bookmarkUser;
	}

	public void setBookmarkUser(boolean bookmarkUser) {
		this.bookmarkUser = bookmarkUser;
	}
	public String getElapsedTime() {
		return elapsedTime;
	}
	
	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	private String calculateElapsedTime ()
	{
		Date currentTime = Calendar.getInstance().getTime();
		Date timePost = postDate.getTime();
		long diffInMillies = currentTime.getTime() - timePost.getTime();

		long minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
		long hours = TimeUnit.MINUTES.toHours(minutes);
		long days = TimeUnit.HOURS.toDays(hours);

		if (days != 0)
		{
			if (days > 30)
			{
				long month = days%30;
				if (month > 12)
				{
					long years = month %12;
					return years + " YEARS AGO";
				}
				return month + " MONTH AGO";
			}
			return days+ " DAYS AGO";
		}

		else if (hours!= 0)
			return hours+" HOURS AGO";
		else if (minutes!= 0)
			return minutes+" MIN AGO";

		return "";
	}
	
	private String getConvertedContent (String content, Set<User> tags, List<Hashtag> hashtags)
	{
		
		//TODO E SE UN TAG FOSSE UNA SOTTOSTRINGA DI UN ALTRO
		if (tags.size() != 0)
		{
			List<User> tagsSorted = tags.stream().collect(Collectors.toList());
			Collections.sort(tagsSorted, (o1, o2) -> o2.getUsername().length()-o1.getUsername().length());
			
			for (User u : tags) {
				content = content.replaceAll("@"+u.getUsername(), "<a href='userPage?usernameOther="+u.getUsername()+"'>"+"@"+u.getUsername()+"</a>");
			}
		}
		if (hashtags.size() != 0)
		{
			
			for (Hashtag hashtag : hashtags) {
				System.out.println(hashtag.getHashtag());
			}
			Collections.sort(hashtags, (o1, o2) -> o2.getHashtag().length()-o1.getHashtag().length());
			for (Hashtag h : hashtags) {
				
				content = content.replaceAll("#"+h.getHashtag(), "<a href='hashtags?hashtag="+h.getHashtag()+"'>"+"#"+h.getHashtag()+"</a>");				
//				content = content.replaceAll("#"+h.getHashtag(), "<a href='javascript:getPostsByHashtags(\""+h.getHashtag()+"\")'>"+"#"+h.getHashtag()+"</a>");
			}
			
		}
		return content;
	}
	

}
