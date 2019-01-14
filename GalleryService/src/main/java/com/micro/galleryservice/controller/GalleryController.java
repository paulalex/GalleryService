package com.micro.galleryservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.micro.galleryservice.dto.Gallery;


@RestController
public class GalleryController
{
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment env;

	@Value("${message:Hello default}")
	private String message;


	@RefreshScope
	@RequestMapping("/")
	public String home()
	{
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return this.message + "from Gallery Service running at port: " + this.env.getProperty("local.server.port");
	}


	@SuppressWarnings("unchecked")
	@RequestMapping("/{id}")
	public Gallery getGallery(@PathVariable final int id)
	{
		// create gallery object
		final Gallery gallery = new Gallery();
		gallery.setId(id);

		// get list of available images
		final List<Object> images = this.restTemplate.getForObject("http://image-service/images", List.class);
		gallery.setImages(images);

		return gallery;
	}


	// -------- Admin Area --------
	// This method should only be accessed by users with role of 'admin'
	// We'll add the logic of role based auth later
	@RequestMapping("/admin")
	public String homeAdmin()
	{
		return "This is the admin area of Gallery service running at port: "
				+ this.env.getProperty("local.server.port");
	}
}
