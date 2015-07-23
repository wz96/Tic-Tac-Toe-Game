/*
 * GameScreen paints GameBoard and SouthPanel together into one JFrame that is presented to the
 * user.
 * */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class GameScreen extends JFrame implements ActionListener {
	private JPanel titlePanel;
	private JPanel gamePanel; 
	private JPanel southPanel;
	private JButton nextMove;
	
	private JButton[] buttonList;
	
	private GameBoard currentGame;
	
	public GameScreen()
	{
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Welcome to Tic Tac Toe");
		currentGame = new GameBoard();
		
		//Set titlePanel
		this.setTitlePanel();
		
		//Set gamePanel's layout
		gamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		GroupLayout layout = new GroupLayout(gamePanel);
		gamePanel.setLayout(layout);
		
		this.setUpButtons();
		
		//Create input fields for names
		JLabel enterPlayer1Name = new JLabel("Enter player 1's name: ");
		JLabel enterPlayer2Name = new JLabel("Enter player 2's name: ");
		JTextField p1Name = new JTextField(20);
		JTextField p2Name = new JTextField(20);
		
		//Create button
		nextMove = new JButton("Start Game!");
		nextMove.setPreferredSize(new Dimension(100,40));
		nextMove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				currentGame.setPlayerNames(p1Name.getText().toString(),p2Name.getText().toString());
				currentGame.getPlayerOne().setType("X");
				currentGame.getPlayerTwo().setType("O");
				currentGame.startGame();
				currentGame.setCurrentAndNextPlayer(currentGame.getPlayerOne(), currentGame.getPlayerTwo());
				setNextGame();
			}
		});
		
		//Add button to southScreen
		southPanel = new JPanel();
		southPanel.add(nextMove);
		
		//Set up layout of gamePanel
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(enterPlayer1Name).addComponent(enterPlayer2Name));
		hGroup.addGroup(layout.createParallelGroup().addComponent(p1Name).addComponent(p2Name));
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(enterPlayer1Name).addComponent(p1Name));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(enterPlayer2Name).addComponent(p2Name));
		layout.setVerticalGroup(vGroup);
		
		//Add padding to gamePanel
		gamePanel.setBorder(new EmptyBorder(20,40,10,40));
		
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(southPanel,BorderLayout.SOUTH);
		this.add(titlePanel,BorderLayout.NORTH);
		this.pack();
	}
	
	public void showGameScreen()
	{
		this.setVisible(true);
	}
	
	public void setTitlePanel()
	{
		this.titlePanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Tic Tac Toe");
		title.setFont(new Font(title.getFont().getName(),Font.PLAIN,22));
		title.setHorizontalAlignment(JLabel.CENTER);
		this.titlePanel.add(title,BorderLayout.NORTH);
		
		//if game has started
		if(currentGame.isGameOver())
		{
			for(int i = 0; i<buttonList.length; i++)
			{
				buttonList[i].setEnabled(false);
			}
			
			currentGame.getCurrentPlayer().increaseWins();
			JLabel winner = new JLabel("Winner is " + currentGame.getCurrentPlayer().getName() + "!");
			winner.setHorizontalAlignment(JLabel.CENTER);
			this.titlePanel.add(winner,BorderLayout.SOUTH);
		}
		else
		{
			if(currentGame.hasGameStarted())
			{
				JLabel whosTurn = new JLabel(currentGame.getCurrentPlayer().getName() + "'s Turn");
				whosTurn.setHorizontalAlignment(JLabel.CENTER);
				this.titlePanel.add(whosTurn,BorderLayout.SOUTH);
			}
		}
	}
	
	public void setNextGame()
	{
		this.setPreferredSize(new Dimension(400,400));
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Tic Tac Toe Game");
		
		/* Reset the Title Panel */
		this.setTitlePanel();
		
		/* Reset the Game Panel */
		this.setGamePanel();
		
		/* Reset the South Panel */
		this.setSouthPanel();
	
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(southPanel,BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
	public void setSouthPanel()
	{
		southPanel.setLayout(new BorderLayout());
		southPanel.removeAll();
		
		JLabel player1Name = new JLabel(currentGame.getPlayerOne().getName() + "'s Wins: " + currentGame.getPlayerOne().getWins());
		JLabel player2Name = new JLabel(currentGame.getPlayerTwo().getName() + "'s Wins: " + currentGame.getPlayerTwo().getWins());
		player1Name.setPreferredSize(new Dimension(150,40));
		player2Name.setPreferredSize(new Dimension(150,40));
		player1Name.setHorizontalAlignment(JLabel.CENTER);
		player2Name.setHorizontalAlignment(JLabel.CENTER);
		
		southPanel.add(player1Name,BorderLayout.WEST);
		southPanel.add(player2Name,BorderLayout.EAST);
		
		if(currentGame.isGameOver())
		{
			nextMove = new JButton("Play Again?");
			nextMove.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					currentGame = new GameBoard(currentGame.getPlayerOne(),currentGame.getPlayerTwo());
					setUpButtons();
					currentGame.setCurrentAndNextPlayer(currentGame.getPlayerOne(),currentGame.getPlayerTwo());
					currentGame.startGame();
					setNextGame();
				}
			});
			southPanel.add(nextMove, BorderLayout.CENTER);
		}
		
		
		
	}
	
	public void setUpButtons()
	{
		buttonList = new JButton[9];
		for(int i=0; i<buttonList.length; i++)
		{
			final int index = i;
			buttonList[index] = new JButton();
			buttonList[index].setPreferredSize(new Dimension(50,50));
			buttonList[index].addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed (ActionEvent event){
						currentGame.getTileArray()[index].setTile(currentGame.getCurrentPlayer());
						buttonList[index].setText(currentGame.getTileArray()[index].getOccupiedPlayer().getType());
						currentGame.setCurrentAndNextPlayer(currentGame.getNextPlayer(), currentGame.getCurrentPlayer());
						currentGame.checkGameOver();
						buttonList[index].setEnabled(false);
						setNextGame();
					}
				}
			);
		}
		
	}
	
	public void setGamePanel()
	{
		gamePanel.setLayout(new GridBagLayout());
		gamePanel.removeAll();
		gamePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		JPanel tBoard = new JPanel(new BorderLayout());
		JPanel upper = new JPanel(new BorderLayout());
		JPanel middle = new JPanel(new BorderLayout());
		JPanel lower = new JPanel(new BorderLayout());
		
		upper.add(buttonList[0],BorderLayout.WEST);
		upper.add(buttonList[1],BorderLayout.CENTER);
		upper.add(buttonList[2],BorderLayout.EAST);
		
		middle.add(buttonList[3],BorderLayout.WEST);
		middle.add(buttonList[4],BorderLayout.CENTER);
		middle.add(buttonList[5],BorderLayout.EAST);
		
		lower.add(buttonList[6],BorderLayout.WEST);
		lower.add(buttonList[7],BorderLayout.CENTER);
		lower.add(buttonList[8],BorderLayout.EAST);
		
		tBoard.add(upper,BorderLayout.NORTH);
		tBoard.add(middle,BorderLayout.CENTER);
		tBoard.add(lower,BorderLayout.SOUTH);
	
		gamePanel.add(tBoard);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paintComponents(Graphics g)
	{
		super.paintComponents(g);
	}
}
