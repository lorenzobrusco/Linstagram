package it.unical.linstagram.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Media {

	public enum Media_Type {
		VIDEO(0), IMAGE(1);

		private final int value;

		private Media_Type(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}

	@Column(nullable = false)
	private Media_Type type = Media_Type.IMAGE;

	@Column
	private String url;

	public Media() {
	}

	public Media(Media_Type type, String url) {
		this.url = url;
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Media_Type getType() {
		return type;
	}

	public void setType(Media_Type type) {
		this.type = type;
	}

}
