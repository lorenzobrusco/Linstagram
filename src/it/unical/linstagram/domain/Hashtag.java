package it.unical.linstagram.domain;

public class Hashtag {

	private int id;

	//@Column(unique = true, nullable = false)
	private String hashtag;
	private int count;

	public Hashtag() {
	}

	public Hashtag(String hashtag, int count) {
		this.hashtag = hashtag;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
