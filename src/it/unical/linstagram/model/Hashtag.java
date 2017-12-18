package it.unical.linstagram.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="hashtag")
public class Hashtag {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(unique = true, nullable = false)
	private String hashtag;

	@Column(name="counter")
	private int count;

	@ManyToMany(mappedBy="hashtags")
	@Cascade(value=CascadeType.ALL)
	private Set<Post> posts = new HashSet<Post>();

	public Hashtag() {
	}
	
	public Hashtag(String hashtag) {
		this.hashtag = hashtag;
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
