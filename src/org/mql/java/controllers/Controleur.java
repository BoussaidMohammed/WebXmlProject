package org.mql.java.controllers;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.xquery.XQException;

import org.mql.java.dao.GamesQuery;
import org.mql.java.metier.ImpGamesMetier;
import org.mql.java.models.Game;

@WebServlet(name = "cs", urlPatterns = "*.do")
public class Controleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ImpGamesMetier metier;
	private HttpServletRequest request;
	private HttpServletResponse responce;

	@Override
	public void init() throws ServletException {
		metier = new ImpGamesMetier();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		setServlet(req, resp);
		if(!isConnected()) path = "/login.do";
		switch(path) {
			case "/index.do": index();break;
			case "/addGame.do": addGame();break;
			case "/saveGame.do": saveGame();break;
			case "/deleteGame.do": deleteGame();break;
			case "/updateGame.do": updateGame();break;
			case "/plateformes.do": plateformes();break;
			case "/plateformeGames.do": plateformeGame();break;
			case "/liste.do": gamesXsl();break;
			case "/login.do": login();break;
			case "/logout.do": logout();break;
		}
	}

	

	private void logout() throws IOException {
		request.getSession().removeAttribute("username");
		responce.sendRedirect("index.do");
	}

	private void login() throws ServletException, IOException {
		if(request.getMethod().equals("GET")) {
			request.getRequestDispatcher("login.jsp").forward(request, responce);
		}else {
			String username = request.getParameter("username");
			String pass = request.getParameter("pass");
			String errUsername = ""; String errPassword = "";String authErr = "";
			if(username.equals("")) errUsername = "Veuillez entrez l'username";
			if(pass.equals("")) errPassword = "Veuillez entrez le mot de passe";
			if(errUsername.equals("") && errPassword.equals("")) {
				if(username.equals("admin") && pass.equals("admin")) {
					request.getSession().setAttribute("username", "admin");
					responce.sendRedirect("index.do");
				}else {
					authErr = "username où mot de passe incorrect";
					request.setAttribute("authErr", authErr);
					request.getRequestDispatcher("login.jsp").forward(request, responce);
				}		
			}else {
				request.setAttribute("username", username);
				request.setAttribute("pass", pass);
				request.setAttribute("errUsername", errUsername);
				request.setAttribute("errPass", errPassword);
				request.getRequestDispatcher("login.jsp").forward(request, responce);
			}
		}
	}

	private boolean isConnected() {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		if(username == null) return false;
		if(username.equals("admin")) return true;
		else return false;
	}

	private void gamesXsl() throws ServletException, IOException {
		request.getRequestDispatcher("games.xml").forward(request, responce);
	}

	private void plateformeGame() {
		String plateforme = request.getParameter("plateforme");
		System.out.println(plateforme);
		String query = "let $doc := doc(\"D:/WorkSpaceJEE/JDK12/WebWebWeb3/WebContent/games.xml\")\r\n" + 
				"for $g in $doc//game \r\n" + 
				"where $g/plateforme = '" +plateforme + "'"+
				" return $g";
		try {
			List<Game> games = GamesQuery.executeQ(query);
			request.setAttribute("games", games);
			request.getRequestDispatcher("/games.jsp").forward(request, responce);
		} catch (Exception e) {}
		
	}

	private void setServlet(HttpServletRequest req, HttpServletResponse resp) {
		responce = resp;
		request = req;
	}

	/** Différents Méthodes de navigation **/
	
	private void updateGame() throws ServletException, IOException {
		Integer id = toInteger(request.getParameter("id"));
		Game game = metier.getGameById(id);
		request.setAttribute("game", game);
		request.getRequestDispatcher("addGame.jsp").forward(request, responce);
	}


	private void index() throws ServletException, IOException {
		List<Game> games = metier.getAllGames();
		request.setAttribute("games", games);
		request.getRequestDispatcher("/games.jsp").forward(request, responce);
	}

	private void addGame() throws ServletException, IOException {
		request.getRequestDispatcher("/addGame.jsp").forward(request, responce);
	}

	private void saveGame() throws IOException, ServletException {
		Integer id = toInteger(request.getParameter("id"));
		Game game = new Game();
		game.setId(id);
		game.setTitle(request.getParameter("title"));
		game.setPrice(toDouble(request.getParameter("price")));
		game.setPublisher(request.getParameter("publisher"));
		game.setPlateforme(request.getParameter("plateforme"));
		if(game.isValide()) {
			if (id == 0) metier.addGame(game);
			else metier.updateGame(game);
			responce.sendRedirect("index.do");
		}else {
			request.setAttribute("game", game);
			request.setAttribute("err", game.getErreurs());
			request.getRequestDispatcher("/addGame.jsp").forward(request, responce);
		}
		
	}

	private void deleteGame() throws IOException {
		metier.removeGame(toInteger(request.getParameter("id")));
		responce.sendRedirect("index.do");
	}
	
	private void plateformes() throws ServletException, IOException {
		String query = "";
		try {
			/* apres execution string query va contenir un tableaux html des platfomes
			 * qu'on va afficher dans plateformes.jsp p*/
			query = GamesQuery.execute();
		} catch (XQException e) {}
		request.setAttribute("query", query);
		request.getRequestDispatcher("plateformes.jsp").forward(request, responce);
	}

	/** des méthodes supplémentaire **/

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
}
