package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.linstagram.dto.CommentDTO;
import it.unical.linstagram.dto.PostDTO;
import it.unical.linstagram.dto.PostPreviewDTO;
import it.unical.linstagram.dto.UserDTO;
import it.unical.linstagram.dto.UserPrivateDTO;
import it.unical.linstagram.helper.HashtagFinder;
import it.unical.linstagram.helper.TagFinder;
import it.unical.linstagram.model.Comment;
import it.unical.linstagram.model.Hashtag;
import it.unical.linstagram.model.Post;
import it.unical.linstagram.model.User;
import it.unical.linstagram.persistence.HashtagDAO;
import it.unical.linstagram.persistence.ModelDAO;
import it.unical.linstagram.persistence.PostDAO;
import it.unical.linstagram.persistence.UserDAO;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;
	@Autowired
	private ModelDAO modelDao;
	@Autowired
	private HashtagDAO hashtagDAO;
	@Autowired
	private UserDAO userDAO;

	public List<Post> getPosts() {
		return postDAO.getPosts();
	}

	public Post getPost(int idPost) {
		return postDAO.getPostById(idPost);
	}

	public PostDTO getPostDTO(int idPost, User user) {
		final Post post = this.getPost(idPost);
		if (post != null) {
			final Set<Post> bookmarks = user.getBookmarks();
			List<User> likes = null;
			boolean hasLike = false;
			boolean hasBookmark = false;
			likes = postDAO.getLikesByPostId(post.getId());
			if (likes.contains(user))
				hasLike = true;
			if (bookmarks.contains(post))
				hasBookmark = true;
			return new PostDTO(post, hasLike, hasBookmark,postDAO.getCommentByPostId(post.getId(), 0, 4));
		}
		return null;
	}

	public List<PostDTO> getPostsbyHashtag(User user, String hashtag, int last) {
		List<Post> posts = postDAO.getPublicPostsByHashtag(user, hashtag, null, last);
		List<PostDTO> postsDTO = new ArrayList<>();

		for (Post post : posts) {
			// System.out.println("creo il post "+post.getContent());
			postsDTO.add(new PostDTO(post, postDAO.doesTheUserLikeThePost(post.getId(), user),
					user.getBookmarks().contains(post),postDAO.getCommentByPostId(post.getId(), 0, 4)));
		}
		return postsDTO;
	}

	public List<PostDTO> getPopularPosts(User user, Calendar date, int last) {

		List<Post> posts = postDAO.getPopularPosts(user.getUsername(), date, last);
		List<PostDTO> postsDTO = new ArrayList<>();

		for (Post post : posts) {
			postsDTO.add(new PostDTO(post, postDAO.doesTheUserLikeThePost(post.getId(), user),
					user.getBookmarks().contains(post),postDAO.getCommentByPostId(post.getId(), 0, 4)));
		}

		return postsDTO;
	}

	public List<PostDTO> getLatestPost(User user, Calendar date, int last) {
		List<Post> posts = postDAO.getLastPosts(user.getUsername(), date, last);

		List<PostDTO> postsDTO = new ArrayList<>();
		Set<Post> bookmarks = user.getBookmarks();
		List<User> likes = null;
		for (Post post : posts) {
			boolean hasLike = false;
			boolean hasBookmark = false;
			likes = postDAO.getLikesByPostId(post.getId());

			if (likes.contains(user))
				hasLike = true;
			if (bookmarks.contains(post))
				hasBookmark = true;

			postsDTO.add(new PostDTO(post, hasLike, hasBookmark,postDAO.getCommentByPostId(post.getId(), 0, 4)));
		}

		return postsDTO;
	}

	public List<PostPreviewDTO> getPopularPostsExplorePage(User user, Calendar date, int last) {

		List<Post> posts = postDAO.getPostsExplorePage(date, last);
		List<PostPreviewDTO> postsDTO = new ArrayList<>();

		for (Post post : posts) {
			postsDTO.add(
					new PostPreviewDTO(post,
							modelDao.getCount("p.likes", Post.class, "p.id="+post.getId()),
					modelDao.getCount("p.comments", Post.class, "p.id="+post.getId())));
		}

		return postsDTO;
	}

	public boolean insertLike(int idPost, String username) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		if (post.getLikes().add(u)) {
			if (modelDao.merge(post))
				return true;
		}
		return false;
	}

	public boolean removeLike(String username, int idPost) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		post.getLikes().remove(u);

		if (modelDao.merge(post))
			return true;
		return false;
	}

	public boolean insertComment(int idPost, String username, String contentComment, Calendar date) {
		Post post = postDAO.getPostById(idPost);
		User user = userDAO.getUserByUsername(username);

		Comment comment = new Comment(contentComment, user, post, date);

		if (modelDao.merge(comment))
			return true;
		return false;
	}

	public User insertBookmark(String username, int idPost) {
		User u = userDAO.getUserByUsername(username);
		Post post = postDAO.getPostById(idPost);
		u.getBookmarks().add(post);

		if (modelDao.merge(u))
			return u;
		return null;
	}

	public User removeBookmark(String username, int idPost) {
		Post post = postDAO.getPostById(idPost);
		User u = userDAO.getUserByUsername(username);
		u.getBookmarks().remove(post);

		if (modelDao.merge(u))
			return u;
		return null;
	}

	public void savePost(Post post) {
		Set<String> findHashtags = HashtagFinder.findHashtags(post.getContent());
		Set<String> findTags = TagFinder.findTags(post.getContent());

		for (String fh : findHashtags) {

			post.setContent(post.getContent().replaceAll("#"+fh, "#"+fh.toLowerCase()));

			Hashtag hashtagByValue = hashtagDAO.getHashtagByValue(fh);
			if (hashtagByValue != null && !post.getUser().isPrivateProfile()) {
				hashtagByValue.setCount(hashtagByValue.getCount() + 1);
			} else {
				if (!post.getUser().isPrivateProfile())
					hashtagByValue = new Hashtag(fh.toLowerCase(), 1);
				else
					hashtagByValue = new Hashtag(fh.toLowerCase(), 0);

			}
			post.getHashtags().add(hashtagByValue);
		}

		for (String tag : findTags) {
			post.setContent(post.getContent().replaceAll("@"+tag, "@"+tag.toLowerCase()));
			User userByUsername = userDAO.getUserByUsername(tag);
			if (userByUsername != null) {
				post.getTags().add(userByUsername);
			}
		}

		modelDao.merge(post);
	}

	public List<Post> getFollowedPosts(String username) {
		return postDAO.getFollowedPosts(username);
	}

	public List<UserDTO> getLikesOfPost(int idPost) {

		List<User> likes = postDAO.getLikesByPostId(idPost);

		List<UserDTO> likesDTO = new ArrayList<>();
		for (User user : likes) {
//			System.out.println(user.getUsername());
			likesDTO.add(new UserPrivateDTO(user, false, false, false));
		}

		return likesDTO;
	}

	public List<CommentDTO> getPostComment(int idPost, int index) {
		List<Comment> comments = postDAO.getCommentByPostId(idPost, index);
		List<CommentDTO> commentDTOs = new ArrayList<>();
		
		for (Comment comment : comments) {
			commentDTOs.add(new CommentDTO(comment));
		}
		return commentDTOs;
	}

}