
public class Player {
	
	private String type;
	private String name;
	private int numOfWins;
	
	public Player()
	{
		type = null;
		name = null;
		numOfWins = 0;
	}
	
	/*
	 * Increases the number of wins of Player by one. Occurs when
	 * Player has won the game.
	 * */
	public void increaseWins()
	{
		numOfWins++;
	}
	
	/*
	 * Sets the Player's name at the beginning of the game.
	 * Should not reset the name throughout the game - one -time setting. 
	*/
	public void setName(String inputName)
	{
		name = inputName;
	}
	
	public void setType(String xOro)
	{
		type = xOro;
	}
	
	public int getWins()
	{
		return numOfWins;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}
}
