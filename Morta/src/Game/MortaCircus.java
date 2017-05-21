/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;    //import the sun.audio package
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author HP-
 */
public class MortaCircus implements World {
    boolean played=false;
    private static World circusInstance;
    private static int MAX_TIME = 1* 60  * 1000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private int width = 500;
    private int height = 600;
    private final List<GameObject> moving = new ArrayList<>();
    private final List<GameObject> control = new ArrayList<>();
    private List<GameObject> constant = new ArrayList<>();
    private int speed;
    private int controlSpeed;    
    static Color[] colorPalette = {Color.BLUE, Color.RED, Color.GREEN};
    static final CDFactory factory = new CDFactory();
    private long markTime=0;
    public MortaCircus() {
        speed = 10;
        controlSpeed = 10;
        control.add(new Morta(width / 3, (int) (height * 0.8), "/morta.png"));
        Iterator<GameObject> movingIterator = moving.iterator();

        //Iterator<GameObject> movingIterator = moving.iterator();
        for (int i = 0; i < 10; i++) {
            moving.add(factory.getPlate(ColorFactory.getRandomColor()));
        }
        constant.add(new ImageObject(width / 3, (int) (height * 0.8), "/cd.png"));
        //((ImageObject)constant.get(0)).setVisible(true);*/
        // constants objects (gold)
    }

    public static World getInstance() {
        circusInstance = (circusInstance == null) ? new MortaCircus() : circusInstance;
        return circusInstance;
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return new ArrayList<>();
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    private boolean intersect(GameObject o1, GameObject o2) {
        return ((Math.abs((o1.getX() + o1.getWidth()) - (o2.getX() + 5)) <= o1.getWidth()) || Math.abs((o1.getX() + o1.getWidth()) - (o2.getX() + o2.getWidth() - 5)) <= o1.getWidth()) && (Math.abs((o1.getY() + o1.getHeight() / 2) - (o2.getY() + o2.getHeight() / 2)) <= o1.getHeight());
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        GameObject clown = control.get(0);
        // moving starts
        ArrayList<GameObject> movedAlready= new ArrayList<>();
        //constant=new ArrayList<>();
        ArrayList<GameObject> toBeRemoved = new ArrayList<>();
        /*if(((ImageObject)constant.get(0)).isVisible() && System.currentTimeMillis()-markTime>=2000)
            ((ImageObject)constant.get(0)).setVisible(false);*/
        for (int i = 1; i < control.size(); i++) {
            ((CDObject) control.get(i)).visible = true;
            
            //Color color = right_side_plates.get(i);
            //CDObject plate=factory.getPlate(clown.getX(), clown.getY()-(i*(2+moving.get(0).getHeight())+2), true, true, color);
            if (i > 1) {
                if (((CDObject) control.get(i)).getColor() == (((CDObject) control.get(i - 1)).getColor())) {
                    score++;
                    /*((ImageObject)constant.get(0)).setVisible(true);
                    markTime=System.currentTimeMillis();*/
                    toBeRemoved.add(control.get(i));
                    toBeRemoved.add(control.get(i - 1));
                }
            }

            if (i < 5) {
                control.get(i).setX(clown.getX());
                control.get(i).setY(clown.getY() - (i * (30) + 33));
            } else {
                control.get(i).setX(clown.getX() + clown.getWidth() - 75);
                control.get(i).setY(clown.getY() - ((i - 4) * (30) + 30));
            }
        }
        for (GameObject m : toBeRemoved) {
            control.remove(m);
            moving.add(m);
            m.setY((int) Math.random() * 50);
        }
        toBeRemoved = new ArrayList<>();

        for (GameObject m : moving) {
            if(!movedAlready.contains(m)){
                m.setY((m.getY() + 1));
                movedAlready.add(m);
            }
            ((CDObject) m).visible = true;
            
            if (m.getY() == getHeight()) {
                // reuse the star in another position
                m.setY(-1 * (int) (Math.random() * getHeight()));
                m.setX((int) (Math.random() * getWidth()));
                ((CDObject) m).setColor(ColorFactory.getRandomColor());
            }
            m.setX(m.getX() + (Math.random() > 0.5 ? 1 : -1));
            if (!timeout & intersect(m, clown)) {
                toBeRemoved.add(m);
                control.add(m);

            }
        }
        for (GameObject m : toBeRemoved) {
            moving.remove(m);
        }
        // collecting astronauts
        synchronized(this){
        if (timeout && !played) {
            played=true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final JFXPanel fxPanel = new JFXPanel();
                    Media audio = new Media(Morta.class.getClassLoader().getResource("anaEstafadtEh.wav").toExternalForm());
                    MediaPlayer mediaPlayer = new MediaPlayer(audio);
                    mediaPlayer.play();
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MortaCircus.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mediaPlayer.stop();
                    mediaPlayer.dispose();
                }
            }).start();}
        }
        return !timeout;
    }

    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis() - startTime)) / 1000);	// update status
    }

    @Override
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    @Override
    public int getControlSpeed() {
        return controlSpeed;
    }

}
