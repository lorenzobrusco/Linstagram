package it.unical.linstagram.model;

public enum Gender {
	MALE("Male"),
	FEMALE("Female"),
	UNKNOW("Unknow");
	
	private final String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
