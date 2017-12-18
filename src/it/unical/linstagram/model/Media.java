package it.unical.linstagram.model;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CollectionId;

@Embeddable
public class Media{
	
	public enum Media_Type{
		VIDEO,IMAGE
	}
	
	@Column(nullable=false)
	private Media_Type type;
	
	@Column
	private String url;
	
	public Media() {
	}
	
	public Media(Media_Type type, String url) {
		this.url=url;
		this.type=type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

	
}
