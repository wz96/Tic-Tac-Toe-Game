/*
 * GameBoard is composed of 9 tiles - each tile a section of
 * the tic-tac-toe board. Each tile is occupied by a single
 * Player, or null if no Player has played that spot yet.
 * 
 * Game is drawn out in a JFrame, then placed into AGame
 */
public class GameBoard{
	private Player currentPlayer;
	private Player nextPlayer;
	
	private Player p1;
	private Player p2;
	
	private boolean started;
	private boolean gameOver;
	private boolean draw;
	
	private Tile[] TileArray;
	
	public GameBoard()
	{
		p1 = new Player();
		p2 = new Player();
		
		TileArray = new Tile[9];
		
		TileArray[0] = new Tile("tl");
		TileArray[1] = new Tile("tm");
		TileArray[2] = new Tile("tr");
		
		TileArray[3] = new Tile("ml");
		TileArray[4] = new Tile("mm");
		TileArray[5] = new Tile("mr");
		
		TileArray[6] = new Tile("bl");
		TileArray[7] = new Tile("bm");
		TileArray[8] = new Tile("br");
		
		gameOver = false;
		draw = false;
	}
	
	public GameBoard(Player player1, Player player2)
	{
		p1 = player1;
		p2 = player2;
		
		TileArray = new Tile[9];
		
		TileArray[0] = new Tile("tl");
		TileArray[1] = new Tile("tm");
		TileArray[2] = new Tile("tr");
		
		TileArray[3] = new Tile("ml");
		TileArray[4] = new Tile("mm");
		TileArray[5] = new Tile("mr");
		
		TileArray[6] = new Tile("bl");
		TileArray[7] = new Tile("bm");
		TileArray[8] = new Tile("br");
		
		
		gameOver = false;
	}
	
	public void checkGameOver()
	{
		// Across top
		if(TileArray[0].getOccupiedPlayer() != null && TileArray[1].getOccupiedPlayer() != null && TileArray[2].getOccupiedPlayer() != null)
		{
			if(TileArray[0].getOccupiedPlayer().getType() == TileArray[1].getOccupiedPlayer().getType()
					&& TileArray[1].getOccupiedPlayer().getType() == TileArray[2].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		// Across middle
		if(TileArray[3].getOccupiedPlayer() != null && TileArray[4].getOccupiedPlayer() != null && TileArray[5].getOccupiedPlayer() != null)
		{
			if(TileArray[3].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType() 
				&& TileArray[4].getOccupiedPlayer().getType() == TileArray[5].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//across bottom
		if(TileArray[6].getOccupiedPlayer() != null && TileArray[7].getOccupiedPlayer() != null && TileArray[8].getOccupiedPlayer() != null)
		{
			if (TileArray[6].getOccupiedPlayer().getType() == TileArray[7].getOccupiedPlayer().getType() 
					&& TileArray[7].getOccupiedPlayer().getType() == TileArray[8].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//from top left corner to bottom right
		if(TileArray[0].getOccupiedPlayer() != null && TileArray[4].getOccupiedPlayer() != null && TileArray[8].getOccupiedPlayer() != null)
		{
			if(TileArray[0].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType() 
				&& TileArray[4].getOccupiedPlayer().getType()==TileArray[8].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//from top right corner to bottom left
		if(TileArray[2].getOccupiedPlayer() != null && TileArray[4].getOccupiedPlayer() != null && TileArray[6].getOccupiedPlayer() != null)
		{
			if(TileArray[2].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType() 
				&& TileArray[6].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//down from left
		if(TileArray[0].getOccupiedPlayer() != null && TileArray[3].getOccupiedPlayer() != null && TileArray[6].getOccupiedPlayer() != null)
		{
			if(TileArray[0].getOccupiedPlayer().getType() == TileArray[3].getOccupiedPlayer().getType() 
				&& TileArray[3].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//down from middle
		if(TileArray[1].getOccupiedPlayer() != null && TileArray[4].getOccupiedPlayer() != null && TileArray[7].getOccupiedPlayer() != null)
		{
			if(TileArray[1].getOccupiedPlayer().getType() == TileArray[4].getOccupiedPlayer().getType() 
				&& TileArray[4].getOccupiedPlayer().getType() == TileArray[7].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		//down from right
		if(TileArray[2].getOccupiedPlayer() != null && TileArray[5].getOccupiedPlayer() != null && TileArray[8].getOccupiedPlayer() != null)
		{
			if(TileArray[2].getOccupiedPlayer().getType() == TileArray[5].getOccupiedPlayer().getType() 
				&& TileArray[5].getOccupiedPlayer().getType() == TileArray[8].getOccupiedPlayer().getType())
			{
				gameOver = true;
			}
		}
		
		//all are filled
		if(TileArray[0].getOccupiedPlayer()!=null && TileArray[1].getOccupiedPlayer()!=null && TileArray[2].getOccupiedPlayer() !=null
				&& TileArray[3].getOccupiedPlayer()!=null && TileArray[4].getOccupiedPlayer()!=null && TileArray[5].getOccupiedPlayer()!=null
				&& TileArray[6].getOccupiedPlayer()!=null && TileArray[7].getOccupiedPlayer()!=null && TileArray[8].getOccupiedPlayer()!=null)
		{
			gameOver = true;
			draw = true;
		}
	}
	
	public void setPlayerNames(String n1, String n2)
	{
		p1.setName(n1);
		p2.setName(n2);
	}
	
	public Tile[] getTileArray()
	{
		return TileArray;
	}
	
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	public boolean hasGameStarted()
	{
		return started;
	}
	
	public void startGame()
	{
		started = true;
	}
	
	public Player getPlayerOne()
	{
		return p1;
	}
	
	public Player getPlayerTwo()
	{
		return p2;
	}
	
	public void setCurrentAndNextPlayer(Player newCurrentPlayer, Player newNextPlayer)
	{
		currentPlayer = newCurrentPlayer;
		nextPlayer = newNextPlayer;
	}
	
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public boolean isDraw()
	{
		return draw;
	}
	
	public Player getNextPlayer()
	{
		return nextPlayer;
	}
}
