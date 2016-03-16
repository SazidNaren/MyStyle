package com.ar.mystyle;

public class FacebookFeedModel {

	private String type="";
	private String name="";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public String getVedioLink() {
		return vedioLink;
	}

	public void setVedioLink(String vedioLink) {
		this.vedioLink = vedioLink;
	}

	public String getVedioSource() {
		return vedioSource;
	}

	public void setVedioSource(String vedioSource) {
		this.vedioSource = vedioSource;
	}

	public String getPreviousLink() {
		return previousLink;
	}

	public void setPreviousLink(String previousLink) {
		this.previousLink = previousLink;
	}

	public String getNextLink() {
		return nextLink;
	}

	public void setNextLink(String nextLink) {
		this.nextLink = nextLink;
	}

	private String message = "";
	private String iconLink = "";
	private String vedioLink = "";
	private String vedioSource = "";
	private String previousLink = "";
	private String nextLink = "";
}
