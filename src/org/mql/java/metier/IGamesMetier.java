package org.mql.java.metier;

import java.util.List;

import org.mql.java.models.Game;

public interface IGamesMetier {
	public void addGame(Game doc);
	public void removeGame(Integer id);
	public List<Game> getAllGames();
	public Game getGameById(Integer id);
	public void updateGame(Game doc);
}
