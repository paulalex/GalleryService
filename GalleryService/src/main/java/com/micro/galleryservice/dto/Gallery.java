package com.micro.galleryservice.dto;


import java.util.List;


public class Gallery
{
	List<Object> images;
	int id;


	public List<Object> getImages()
	{
		return this.images;
	}


	public void setImages(final List<Object> images)
	{
		this.images = images;
	}


	public int getId()
	{
		return this.id;
	}


	public void setId(final int id)
	{
		this.id = id;
	}
}
