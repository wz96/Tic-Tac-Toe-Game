public class Tile {
	private Player whoCurrentlyOccupies;
	private String position;
	
	public Tile(String givenPos)
	{
		position = givenPos;
		whoCurrentlyOccupies = null;
	}
	
	public void setTile(Player p)
	{
		whoCurrentlyOccupies = p;
	}
	
	public Player getOccupiedPlayer()
	{
		return whoCurrentlyOccupies;
	}
}
