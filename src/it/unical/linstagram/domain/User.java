package it.unical.linstagram.domain;

import java.util.Date;

public class User {

	private int id;

	private String email;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date birthdate;
	private Gender gender;
	private String biography;
	private boolean privateProfile;
	private Media profilePic;
	
	public User(int id, String email, String username, String password, String name, String surname, Date birthdate,
			Gender gender, String biography, boolean privateProfile, Media profilePic) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.gender = gender;
		this.biography = biography;
		this.privateProfile = privateProfile;
		this.profilePic = profilePic;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isPrivateProfile() {
		return privateProfile;
	}

	public void setPrivateProfile(boolean privateProfile) {
		this.privateProfile = privateProfile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public Media getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(Media profilePic) {
		this.profilePic = profilePic;
	}

	
	
}
