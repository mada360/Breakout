package breakout;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import static breakout.Global.*;

/**
 * Displays a graphical view of the game of breakout
 *  Uses Garphics2D would need to be re-implemented for Android
 */
public class ViewBreakout extends JFrame implements Observer
{ 
  private ControllerBreakout controller;
  private GameObject   ball;             // The ball
  private GameObject[] bricks;           // The bricks
  private GameObject   bat;              // The bat
  private int          score =  0;       // The score
  private int          frames = 0;
  
  public ViewBreakout()
  {
    setSize( W, H );                        // Size of window
    addKeyListener( new Transaction() );    // Called when key press
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  /**
   *  Code called to draw the current state of the game
   *   Uses draw:       Draw a shape
   *        fill:       Fill the shape
   *        setPaint:   Colour used
   *        drawString: Write string on display
   *  @param g Graphics context to use
   */
  public void drawActualPicture( Graphics2D g )
  {
    frames++;
    // White background

    g.setPaint( Color.white );
    g.fill( new Rectangle2D.Float( 0, 0, W, H ) );

    Font font = new Font("Monospaced",Font.BOLD,24); 
    g.setFont( font ); 

    // Blue playing border

    g.setPaint( Color.blue );              // Paint Colour
    g.draw( new Rectangle2D.Float( B, M, W-B*2, H-M-B ) );
    
    // Display the ball
    displayR(g, ball);


    // Display the bricks that make up the game
          for(int j=0; j<bricks.length; j++){
          if(bricks[j] != null){
          display(g, bricks[j]);
          }
      }
     
    // Display the bat
    display( g, bat );
    
    // Display state of game
    g.setPaint( Color.green );
    FontMetrics fm = getFontMetrics( font );
    String fmt = "BreakOut: Score = [%6d] fps=%5.1f";
    String text = String.format(fmt, score,
                    frames/(Timer.readTimeTaken()/1000.0 )
                   );
    if ( frames > 100 ) { frames = 0; Timer.startTimer(); }
    g.drawString( text, W/2-fm.stringWidth(text)/2, (int)M*2 );
  }
  
  private void display( Graphics2D g, GameObject go )
  {
    switch( go.getColour() )
    {
      case GRAY: g.setColor( Color.gray );
                 break;
      case BLUE: g.setColor( Color.blue );
                 break;
      case RED:  g.setColor( Color.red );
                 break;
    }
    g.fill( new Rectangle2D.Float( go.getX(),     go.getY(),
                                   go.getWidth(), go.getHeight() ) );
  }

    private void displayR( Graphics2D g, GameObject go )
    {
        switch( go.getColour() )
        {
            case GRAY: g.setColor( Color.gray );
                break;
            case BLUE: g.setColor( Color.blue );
                break;
            case RED:  g.setColor( Color.red );
                break;
        }
        g.fill( new Ellipse2D.Float( go.getX(),     go.getY(),
                go.getWidth(), go.getHeight() ) );
    }
  
  /**
   * Called from the model when its state has changed
   */

  @Override
  public void update( Observable aModel, Object arg )
  {
    ModelBreakout model = (ModelBreakout) aModel;
    // Get from the model the ball, bat, bricks & score
    ball    = model.getBall();              // Ball
    bricks  = model.getBricks();            // Bricks
    bat     = model.getBat();               // Bat
    score   = model.getScore();             // Score
    Debug.trace("Update");
    repaint();                              // Re draw game
  }

  @Override
  public void update( Graphics g )          // Called by repaint
  {
    drawPicture( (Graphics2D) g );          // Draw Picture
  }

  @Override
  public void paint( Graphics g )           // When 'Window' is first
  {                                         //  shown or damaged
    drawPicture( (Graphics2D) g );          // Draw Picture
  }

  private BufferedImage theAI;              // Alternate Image
  private Graphics2D    theAG;              // Alternate Graphics

  public void drawPicture( Graphics2D g )   // Double buffer
  {                                         //  to avoid flicker
    if (  theAG == null )
    {
      Dimension d = getSize();              // Size of curr. image
      theAI = (BufferedImage) createImage( d.width, d.height );
      theAG = theAI.createGraphics();
    }
    drawActualPicture( theAG );             // Draw Actual Picture
    g.drawImage( theAI, 0, 0, this );       //  Display on screen
  }

  /**
   * Need to be told where the controller is
   */
  public void setController(ControllerBreakout aPongController)
  {
    controller = aPongController;
  }

  /**
   * Methods Called on a key press 
   *  calls the controller to process
   */
  class Transaction implements KeyListener  // When character typed
  {
    @Override
    public void keyPressed(KeyEvent e)      // Obey this method
    {
      // Make -ve so not confused with normal characters
      controller.userKeyInteraction( -e.getKeyCode() );
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
      // Called on key release including specials
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
      // Send internal code for key
      controller.userKeyInteraction( e.getKeyChar() );
    }
  }
}
