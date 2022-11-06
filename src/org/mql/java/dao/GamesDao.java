package org.mql.java.dao;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mql.java.models.Game;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GamesDao {
	private Document document;// c'est le document XML ouvert au niveau de la memoire
	private Transformer transformer;// Utiliser pour rendre les modification permanante dans le document XML
	private String path = "D:/WorkSpaceJEE/JDK12/WebWebWeb3/WebContent/games.xml";// Chemin du fichier
	private StreamResult file;// Utiliser par transformer pour pointer sur le fichier physique
	private DOMSource source;// Utiliser pour transformer  le document de la memoire vers le document de disque

	public GamesDao() {
		try {

			document = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().parse(path);

			transformer = TransformerFactory.newDefaultInstance().newTransformer();

			file = new StreamResult(path);

			source = new DOMSource(document);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** Méthodes opérationnelle sur les élements **/

	public void addGameElement(Game game) {
		/** Création de l'element game */
		Element gameElement = document.createElement("game");
		gameElement.setAttribute("id", generateId().toString());
		/** association du titre au gameElement **/
		Element title = document.createElement("title");
		title.setTextContent(game.getTitle());
		gameElement.appendChild(title);

		/** association du titre au gameElement **/
		Element price = document.createElement("price");
		price.setTextContent(stringValue(game.getPrice()));
		gameElement.appendChild(price);

		/** association du publisher au gameElement **/
		Element publisher = document.createElement("publisher");
		publisher.setTextContent(game.getPublisher());
		gameElement.appendChild(publisher);

		/** association du plateforme au gameElement **/
		Element plateforme = document.createElement("plateforme");
		plateforme.setTextContent(game.getPlateforme());
		gameElement.appendChild(plateforme);

		/** associer le nouveau jeux á la racine **/
		document.getDocumentElement().appendChild(gameElement);
		save();
	}

	public void updateGameElement(Game game) {
		/**
		 * Récuperation de l'element á modifier á l'aide de la méthode
		 * getGameElementById()
		 **/
		Element gameElement = getGameElementById(game.getId());

		/** Si l'element n'existe pas, il faut quité immediatement **/
		if (gameElement == null)
			return;
		/** ci-dessous la mis á jour de l'id du game **/
		gameElement.setAttribute("id", stringValue(game.getId()));

		/**
		 * childsElements est une map qui contient les élements fils du gameElement
		 * qu'on doit modifier element par element pour que tout l'objet gameElement
		 * sera modifier
		 */
		Hashtable<String, Element> childsElements = getChildsElements(gameElement);
		childsElements.get("title").setTextContent(game.getTitle());
		childsElements.get("price").setTextContent(stringValue(game.getPrice()));
		childsElements.get("publisher").setTextContent(game.getPublisher());
		childsElements.get("plateforme").setTextContent(game.getPlateforme());
		save();
	}

	private Integer generateId() {
		/** Ici je récupere l'id maximum que je retournerai en lui ajoutant 1 **/
		Integer max = 1;
		NodeList games = document.getElementsByTagName("game");

		/** Si la liste des games ne contient aucun element je retounre 1 **/
		if (games.getLength() == 0)
			return max;

		/** Si non, extraire le maximum id **/
		for (int i = 0; i < games.getLength(); i++) {
			Element game = (Element) games.item(i);
			Integer id = toInteger(game.getAttribute("id"));
			if (id > max)
				max = id;
		}
		return max + 1;
	}

	private Hashtable<String, Element> getChildsElements(Element gameElement) {
		/**
		 * cette méthode crée une map des fils de l'element game du fichier games.xml,
		 * elle contient comme clé un chaine de caractères et comme valeur l'element qui
		 * lui correspond
		 **/
		Hashtable<String, Element> childElements = new Hashtable<String, Element>();
		NodeList childNodes = gameElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node n = childNodes.item(i);

			/** Ce test est nécessaire pour éxtraire que les noeud de type element **/
			if (n.getNodeType() == Node.ELEMENT_NODE) {

				/**
				 * Pour chaque noeud de type element on crée une entrée dans la map
				 * childsElements avec le nom du noeud comme clé, et le noeud casté en element
				 * commme valeur
				 **/
				childElements.put(n.getNodeName(), (Element) n);
			}
		}
		return childElements;
	}

	public Element getGameElementById(Integer id) {
		/**
		 * Pour récuperer un game par son id, je récuper la liste de tous les games et
		 * pour chaque game je test son attribut id est ce qu'il égal au id passer en
		 * paramétre si oui je retourne l'element courant de la boucle
		 **/
		NodeList games = document.getElementsByTagName("game");
		for (int i = 0; i < games.getLength(); i++) {
			Node game = games.item(i);
			NamedNodeMap attributes = game.getAttributes();
			String value = attributes.getNamedItem("id").getNodeValue();
			if (value.equals(id.toString())) {
				return (Element) game;
			}
		}
		return null;
	}

	public void removeGameElementById(Integer id) {
		/**
		 * la suppréssion ce fait par la récuperation du noeud qui a l'id égal au id
		 * passé en arguments, et en utilisant se noeud comme argument de la méthode
		 * removeChild() qui est appliquer sur le noeud racine du document
		 **/
		Node game = getGameElementById(id);
		if (game != null)
			document.getDocumentElement().removeChild(game);
		save();
	}

	/** Méthode de travaille sur les elements **/

	/** Méthodes de conversion **/
	private Integer toInteger(String value) {
		try {
			Integer nb = Integer.parseInt(value);
			return nb;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	private String stringValue(Object o) {
		return String.valueOf(o);
	}

	private Double toDouble(String value) {
		try {
			Double nb = Double.parseDouble(value);
			return nb;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0.0;
		}
	}

	private void save() {
		try {
			transformer.transform(source, file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		GamesDao gamesDao = new GamesDao();
		System.out.println(gamesDao.getAllGames());
	}

	/** méthode spécifique aux models **/
	public List<Game> getAllGames() {
		Vector<Game> gamesList = new Vector<>();
		NodeList games = document.getElementsByTagName("game");
		if (games.getLength() == 0)
			return null;
		for (int i = 0; i < games.getLength(); i++) {
			Element gameElement = (Element) games.item(i);
			Game game = toGame(gameElement);
			gamesList.add(game);
		}
		return gamesList;
	}

	public Game getGameById(Integer id) {
		Element gameElement = getGameElementById(id);
		if (gameElement == null)
			return null;
		Game game = toGame(gameElement);
		return game;
	}

	public Game toGame(Element gameElement) {
		Game game = new Game();
		Hashtable<String, Element> childs = getChildsElements(gameElement);
		System.out.println(childs.get("plateforme").getTextContent());
		game.setId(toInteger(gameElement.getAttribute("id")));
		game.setTitle(childs.get("title").getTextContent());
		game.setPrice(toDouble(childs.get("price").getTextContent()));
		game.setPublisher(childs.get("publisher").getTextContent());
		game.setPlateforme(childs.get("plateforme").getTextContent());
		return game;
	}
}
