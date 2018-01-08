package it.unical.linstagram.dto;

import it.unical.linstagram.model.Comment;

public class CommentDTO {
	private String username;
	private String comment;

	public CommentDTO(Comment comment) {
		this.comment = comment.getContent();
		this.username = comment.getUser().getUsername();
	}
	public String getComment() {
		return comment;
	}
	public String getUsername() {
		return username;
	}
}
