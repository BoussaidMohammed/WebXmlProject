package org.mql.java.metier;

import java.util.List;

import org.mql.java.dao.GamesDao;
import org.mql.java.models.Game;

public class ImpGamesMetier implements IGamesMetier{
	private GamesDao dataSource;
	
	public ImpGamesMetier() {
		dataSource = new GamesDao();
	}
	
	
	@Override
	public void addGame(Game game) {
		dataSource.addGameElement(game);
	}
	
	@Override
	public void removeGame(Integer id) {
		dataSource.removeGameElementById(id);
	}
	
	@Override
	public List<Game> getAllGames() {
		return dataSource.getAllGames();
	}

	@Override
	public Game getGameById(Integer id) {
		return dataSource.getGameById(id);
	}

	@Override
	public void updateGame(Game game) {
		dataSource.updateGameElement(game);
		System.out.println("update 2");
	}
	
	
}
