package org.mql.java.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import org.mql.java.models.Game;
import org.w3c.dom.Element;

import com.saxonica.xqj.SaxonXQDataSource; 

public class GamesQuery {
	// Create a new connection to an XML database
	
	 public static String execute() throws FileNotFoundException, XQException{  
	      InputStream inputStream = new FileInputStream(new File("D:/WorkSpaceJEE/JDK12/WebWebWeb3/WebContent/plateformes.xquery"));  
	      XQDataSource ds = new SaxonXQDataSource();  
	      XQConnection conn = ds.getConnection();  
	      XQPreparedExpression exp = conn.prepareExpression(inputStream);  
	      XQResultSequence result = exp.executeQuery(); 
	      String resultat = "";
	       while (result.next()) {  
	         resultat = result.getItemAsString(null);  
	      }  
	      return resultat;
	  }
	 
	 public static String execute(String query) throws FileNotFoundException, XQException{  
	      XQDataSource ds = new SaxonXQDataSource();  
	      XQConnection conn = ds.getConnection();
	      XQPreparedExpression exp = conn.prepareExpression(query);  
	      XQResultSequence result = exp.executeQuery();
	      String resultat = "";
	       while (result.next()) {  
	         resultat += result.getItemAsString(null);
	       }
	       return resultat;
	  }
	 
	 public static List<Game> executeQ(String query) throws FileNotFoundException, XQException{  
	      XQDataSource ds = new SaxonXQDataSource();  
	      XQConnection conn = ds.getConnection();
	      XQPreparedExpression exp = conn.prepareExpression(query);  
	      XQResultSequence result = exp.executeQuery();
	      Vector<Game> games = new Vector<Game>();
	      GamesDao dao = new GamesDao();
	       while (result.next()) {  
	         Element e = (Element)result.getNode();
	         games.add(dao.toGame(e));
	       }
	       return games;
	  }
	 
}
