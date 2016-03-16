package com.ar.photobooth;


import android.os.Parcel;
import android.os.Parcelable;

public class EventListModel implements Parcelable {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public String getLocationShort() {
		return locationShort;
	}

	public void setLocationShort(String locationShort) {
		this.locationShort = locationShort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsFood() {
		return isFood;
	}

	public void setIsFood(String isFood) {
		this.isFood = isFood;
	}

	

	private int id=0;
	private String name = "";
	private String description = "";
	private String location = "";
	private String facebookLink = "";
	private String date = "";
	private int rating=0;
	private String nameShort = "";
	private String locationShort = "";
	private String isFood="";
	private String title = "";
	private String feteType="";
	private String flyerUrl;
	public String getFlyerUrl() {
		return flyerUrl;
	}

	public void setFlyerUrl(String flyerUrl) {
		this.flyerUrl = flyerUrl;
	}

	public String getFete() {
		return feteType;
	}

	public void setFete(String fete) {
		this.feteType = fete;
	}

	public String getFeteSize() {
		return feteSize;
	}

	public void setFeteSize(String feteSize) {
		this.feteSize = feteSize;
	}

	private String feteSize="";
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(feteSize);
		dest.writeString(feteType);	
		dest.writeString(name);
		dest.writeString(nameShort);	
		dest.writeString(location);
		dest.writeString(locationShort);	
		dest.writeString(isFood);
		dest.writeString(title);
		dest.writeInt(rating);
		dest.writeString(facebookLink);	
		dest.writeString(description);
		dest.writeInt(id);
		

	
	}

}
