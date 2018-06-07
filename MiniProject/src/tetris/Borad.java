package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import Games.SmallGames;

import javax.swing.JLabel;
import javax.swing.JPanel;
import tetris.Shape.Tetrominoes;

public class Borad extends JPanel implements ActionListener{
	
	Borad br = this;
    final int BoardWidth = 10;
    final int BoardHeight = 22;
    
    static boolean isend = false;
    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    boolean isFirst ;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    int counter = 0;
    JLabel statusbar;
    JLabel statusbar2;
    Shape curPiece ;
    Tetrominoes[] board;
    GameOver gosco = new GameOver();
    
   
    ScoreRanking srk ;
    Tetris te ;
    int countfirst = 0;
    
    public Borad(Tetris parent) {
    	if(countfirst == 0) {
    		isFirst = true;
    	}
    	countfirst++;
    	 setFocusable(true);
         curPiece = new Shape();
         timer = new Timer(400, this);
         timer.start(); 
         statusbar =  parent.getStatusBar();
         statusbar2 =  parent.getStatusBar2();
         int ac8 = Integer.parseInt(statusbar2.getText());
         te = parent;
         board = new Tetrominoes[BoardWidth * BoardHeight];
         addKeyListener(new TAdapter());
         clearBoard();  
         }
    
    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    Tetrominoes shapeAt(int x, int y) { return board[(y * BoardWidth) + x]; }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		  if (isFallingFinished) {
	            isFallingFinished = false;
	            newPiece(te);
	        } else {
	            oneLineDown();
	        }
	}
	
	public void start()
    {
		if(isFirst = false) {
			if (isPaused)
	            return;
	        isStarted = true;
	        isFallingFinished = false;
	        numLinesRemoved = 0;
	        clearBoard();
	        newPiece(te);
	        timer.start();
		}else if(isFirst = true) {
			isStarted = true;
	        isFallingFinished = false;
	        numLinesRemoved = 0;
	        clearBoard();
	        newPiece(te);
	        timer.stop();
	        statusbar.setText("스페이스바를 누르면 시작합니다");
	        statusbar2.setText("0");
		}
    }
	
	private void pause()
    {
        if (!isStarted)
            return;
        isPaused = !isPaused;
        isFirst = false;
        if (isPaused) {
            timer.stop();
            statusbar.setText("paused");
            statusbar2.setText(String.valueOf(numLinesRemoved));
        } else {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
            statusbar2.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }
	
	
	private void restart() {
		statusbar.setText("0");
		statusbar2.setText("0");
	}
	
	public void paint(Graphics g)
    { 
        super.paint(g);
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();


        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape);
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (BoardHeight - y - 1) * squareHeight(),
                           curPiece.getShape());
            }
        }
    }
	
	private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    private void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = Tetrominoes.NoShape;
    }
    
    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece(te);
    }

    private void newPiece(Tetris tetries)
    {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            statusbar.setText("game over  r 누르면 재시작");
            //gosco.rankDialog();
            int theScore = Integer.parseInt(statusbar2.getText()); 
          //  godsco = new GameOverDialog(theScore);
            isend = true;
            new GameOverDialog(theScore , tetries);
        }
    }
        
        private boolean tryMove(Shape newPiece, int newX, int newY)
        {
            for (int i = 0; i < 4; ++i) {
                int x = newX + newPiece.x(i);
                int y = newY - newPiece.y(i);
                if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                    return false;
                if (shapeAt(x, y) != Tetrominoes.NoShape)
                    return false;
            }

            curPiece = newPiece;
            curX = newX;
            curY = newY;
            repaint();
            return true;
        }
        
        private void removeFullLines()
        {
            int numFullLines = 0;

            for (int i = BoardHeight - 1; i >= 0; --i) {
                boolean lineIsFull = true;

                for (int j = 0; j < BoardWidth; ++j) {
                    if (shapeAt(j, i) == Tetrominoes.NoShape) {
                        lineIsFull = false;
                        break;
                    }
                }

                if (lineIsFull) {
                    ++numFullLines;
                    for (int k = i; k < BoardHeight - 1; ++k) {
                        for (int j = 0; j < BoardWidth; ++j)
                             board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                    }
                }
            }

            if (numFullLines > 0) {
                numLinesRemoved += numFullLines;
                statusbar.setText(String.valueOf(numLinesRemoved));
                statusbar2.setText(String.valueOf(numLinesRemoved));
                isFallingFinished = true;
                curPiece.setShape(Tetrominoes.NoShape);
                repaint();
            }
         }
        
        private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
        {
            Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
                new Color(102, 204, 102), new Color(102, 102, 204), 
                new Color(204, 204, 102), new Color(204, 102, 204), 
                new Color(102, 204, 204), new Color(218, 170, 0)
            };


            Color color = colors[shape.ordinal()];

            g.setColor(color);
            g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

            g.setColor(color.brighter());
            g.drawLine(x, y + squareHeight() - 1, x, y);
            g.drawLine(x, y, x + squareWidth() - 1, y);

            g.setColor(color.darker());
            g.drawLine(x + 1, y + squareHeight() - 1,
                             x + squareWidth() - 1, y + squareHeight() - 1);
            g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                             x + squareWidth() - 1, y + 1);
        }   
	
	 class TAdapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {

//             if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  //<- 게임종료
//                 return; 
//             }

             int keycode = e.getKeyCode();
             
//             if (keycode == 'r' || keycode == 'R') {
//
//            	 if(!tryMove(curPiece, curX, curY)) {
//                	 start();
//                	 restart();
//                 }else {
//                	 System.out.println("게임 진행중일때 cmd 창에 이 메세지 보이신다면 제대로 처리된것");
//                	 return;
//                 }
//                 
//             }
             

             if (keycode == 'p' || keycode == 'P') {
                 pause();
                 return;
             }

             if (isPaused)
                 return;

             switch (keycode) {
             case KeyEvent.VK_LEFT:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape&&isFirst == true) {      
            		 System.out.println("어림없는 소리");
            	 }else {
            		 tryMove(curPiece, curX - 1, curY);
            	 }
                 break;
             case KeyEvent.VK_RIGHT:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape&&isFirst == true) {	
            		 System.out.println("어림없는 소리");
            	 }else {
            		 tryMove(curPiece, curX + 1, curY);
            	 }
                 break;
             case KeyEvent.VK_DOWN:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape&&isFirst == true) {    
            		 System.out.println("어림없는 소리");
            	 }else {
            		 tryMove(curPiece.rotateRight(), curX, curY);
            	 }               
                 break;
             case KeyEvent.VK_UP:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape&&isFirst == true) {
            		 System.out.println("동작그만 밑장빼기");
            	 }else {
            		 tryMove(curPiece.rotateLeft(), curX, curY);
            	 }
                 break;
             case KeyEvent.VK_SPACE:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
            		 System.out.println("어림없는 소리");
            	 }else if(isFirst == true) {
            		 isFirst = false;
            		 timer.start();
            		 statusbar.setText("0");
            		 statusbar2.setText("0");
            	 }else if(isFirst == false){
                     dropDown(); 
            	 }
                 break;
             case KeyEvent.VK_R:
            	 if(!isStarted || curPiece.getShape() == Tetrominoes.NoShape||isFirst == false) {
            		 isFirst = false;
            		 isPaused = false;
            		 restart();
            		 start();
            		 timer.start();
            		 statusbar.setText("0");
            		 isend = false;
            	 }else {
            		 System.out.println("어림없는 소리!");
            	 }
            	 break;
             case 'd':
                 oneLineDown();
                 break;
             case 'D':
                 oneLineDown();
                 break;
             }

         }
     }
}
