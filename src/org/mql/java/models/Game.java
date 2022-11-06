package org.mql.java.models;


public class Game {
	private Integer id;
	private String title;
	private Double price;
	private String publisher;
	private String plateforme;
	private GameErr erreur;
	
	public Game() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", title=" + title + ", price=" + price + ", publisher=" + publisher + ", plateforme="
				+ plateforme + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public Game(Integer id, String title, Double price, String publisher, String plateforme) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.publisher = publisher;
		this.plateforme = plateforme;
	}

	public Game(String title, Double price, String publisher, String plateforme) {
		super();
		this.title = title;
		this.price = price;
		this.publisher = publisher;
		this.plateforme = plateforme;
	}

	public boolean isValide() {
		erreur = new GameErr();
		if(title.equals("")) {
			erreur.setTitle("Veuillez entrer le titre");
			return false;
		}
		if(price == 0.0) {
			erreur.setPrice("Veuillez entrer le prix");
			return false;
		}
		if(publisher.equals("")) {
			erreur.setPublisher("Veuillez entrer l'éditeur");
			return false;
		}
		if(plateforme.equals("")) {
			erreur.setPlateforme("Veuillez entrer la plateforme");
			return false;
		}
		return true;
	}

	public Object getErreurs() {
		return erreur;
	}
	
	
	
	

	
}
