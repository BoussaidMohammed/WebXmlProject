package org.mql.java.models;

public class GameErr {
	private String title;
	private String price;
	private String publisher;
	private String plateforme;
	
	public GameErr() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPlateforme() {
		return plateforme;
	}

	public void setPlateforme(String plateforme) {
		this.plateforme = plateforme;
	}

	public GameErr(String title, String price, String publisher, String plateforme) {
		super();
		this.title = title;
		this.price = price;
		this.publisher = publisher;
		this.plateforme = plateforme;
	}
	
}
