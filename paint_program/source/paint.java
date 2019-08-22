import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class paint extends PApplet {

//images to bring into the program
PImage imgbrush;
PImage img1dot;
PImage img3dot;
PImage img5dot;
PImage img9dot;
PImage imgeraser;
PImage imgline;
PImage imgnew;
PImage imgsave;
PImage imgscreen;

//other variables
boolean showSplash = true;
float curTime = 0;
float prevTime = 0;
int interval = 3000;
float fade = 0;
int transparency = 255;

//starts as mode 1
int m = 1;

//number of buttons and brushes
int numB = 18;
int numBr = 9;

//arrays and instances of other classes
Button[] button = new Button[numB];
Brush[] brush = new Brush[numBr];
Save saves;

//sets up the original parameters 
public void setup() 
{

  //basic setup
  
  background(200);

  //loading images for the buttons
  imgbrush = loadImage("brush.png");
  img1dot = loadImage("1dot.png");
  img3dot = loadImage("3dot.png");
  img5dot = loadImage("5dot.png");
  img9dot = loadImage("9dot.png");
  imgeraser = loadImage("eraser.png");
  imgline = loadImage("line.png");
  imgnew = loadImage("new.png");
  imgsave = loadImage("save.png");
  imgscreen = loadImage("splashscreen.png");


  //initializing constructors
  button[9] = new Button(0, 0, color(255), 60, 60, 1);
  button[10] = new Button(0, 60, color(255), 60, 60, 1);
  button[11] = new Button(0, 120, color(255), 60, 60, 1);
  button[12] = new Button(0, 180, color(255), 60, 60, 1);
  button[13] = new Button(0, 240, color(255), 60, 60, 1);
  button[14] = new Button(0, 300, color(255), 60, 60, 1);
  button[15] = new Button(0, 360, color(255), 60, 60, 1);
  button[16] = new Button(0, 420, color(255), 60, 60, 1);
  button[17] = new Button(0, 480, color(255), 60, 60, 1);
  button[0] = new Button(width-60, 0, color(random(255), random(255), random(255)), 60, 60, 1);
  button[1] = new Button(width-60, 60, color(random(255), random(255), random(255)), 60, 60, 1);
  button[2] = new Button(width-60, 120, color(random(255), random(255), random(255)), 60, 60, 1);
  button[3] = new Button(width-60, 180, color(random(255), random(255), random(255)), 60, 60, 1);
  button[4] = new Button(width-60, 240, color(random(255), random(255), random(255)), 60, 60, 1);
  button[5] = new Button(width-60, 300, color(random(255), random(255), random(255)), 60, 60, 1);
  button[6] = new Button(width-60, 360, color(random(255), random(255), random(255)), 60, 60, 1);
  button[7] = new Button(width-60, 420, color(random(255), random(255), random(255)), 60, 60, 1);
  button[8] = new Button(width-60, 480, color(random(255), random(255), random(255)), 60, 60, 1);
  brush[0] = new Brush(mouseX, mouseY, color(button[0].c));
  brush[1] = new Brush(0, 0, color(button[1].c));
  brush[2] = new Brush(0, 0, color(button[2].c));
  brush[3] = new Brush(0, 0, color(button[3].c));
  brush[4] = new Brush(0, 0, color(button[4].c));
  brush[5] = new Brush(0, 0, color(button[5].c));
  brush[6] = new Brush(0, 0, color(button[6].c));
  brush[7] = new Brush(0, 0, color(button[7].c));
  brush[8] = new Brush(0, 0, color(button[8].c));
  saves = new Save();
}

//drawing functions and things that need to be constantly updated
public void draw()
{

  //live locations for brushes
  if (m == 1) {
    brush[0].posx = mouseX;
    brush[0].posy = mouseY;
  }
  if (m == 2) {
    brush[0].posx = mouseX + 20;
    brush[0].posy = mouseY + 20;
    brush[1].posx = mouseX - 20;
    brush[1].posy = mouseY + 20;
    brush[2].posx = mouseX;
    brush[2].posy = mouseY - 20;
  }
  if (m == 3) {
    brush[0].posx = mouseX - 40;
    brush[0].posy = mouseY;
    brush[1].posx = mouseX - 20;
    brush[1].posy = mouseY;
    brush[2].posx = mouseX;
    brush[2].posy = mouseY;
    brush[3].posx = mouseX + 20;
    brush[3].posy = mouseY;
    brush[4].posx = mouseX + 40;
    brush[4].posy = mouseY;
  }
  if (m == 4) {
    brush[0].posx = mouseX;
    brush[0].posy = mouseY;
    brush[1].posx = mouseX;
    brush[1].posy = mouseY - 40;
    brush[2].posx = mouseX + 20;
    brush[2].posy = mouseY - 20;
    brush[3].posx = mouseX + 40;
    brush[3].posy = mouseY;
    brush[4].posx = mouseX + 20;
    brush[4].posy = mouseY + 20;
    brush[5].posx = mouseX;
    brush[5].posy = mouseY + 40;
    brush[6].posx = mouseX - 20;
    brush[6].posy = mouseY + 20;
    brush[7].posx = mouseX - 40;
    brush[7].posy = mouseY;
    brush[8].posx = mouseX - 20;
    brush[8].posy = mouseY - 20;
  }


  //decal rectangles for the UI
  fill(100, 100, 255);
  rect(0, 0, 100, height);
  rect(width-100, 0, 100, height);
  fill(100, 255, 150);
  rect(0, 0, 80, height);
  rect(width-80, 0, 80, height);

  //calling the buttons
  for (int i = 0; i < numB; i++) {
    button[i].update();
    button[i].display();
  }

  //drawing button images
  image(imgbrush, button[15].posx, button[15].posy, button[15].w, button[15].h);
  image(img1dot, button[11].posx, button[11].posy, button[11].w, button[11].h);
  image(img3dot, button[12].posx, button[12].posy, button[12].w, button[12].h);
  image(img5dot, button[13].posx, button[13].posy, button[13].w, button[13].h);
  image(img9dot, button[14].posx, button[14].posy, button[14].w, button[14].h);
  image(imgeraser, button[16].posx, button[16].posy, button[16].w, button[16].h);
  image(imgline, button[17].posx, button[17].posy, button[17].w, button[17].h);
  image(imgnew, button[10].posx, button[10].posy, button[10].w, button[10].h);
  image(imgsave, button[9].posx, button[9].posy, button[9].w, button[9].h);

  //divider lines for left buttons
  line(button[9].posx, button[9].posy, button[9].posx+60, button[9].posy);
  line(button[10].posx, button[10].posy, button[10].posx+60, button[10].posy);
  line(button[11].posx, button[11].posy, button[11].posx+60, button[11].posy);
  line(button[12].posx, button[12].posy, button[12].posx+60, button[12].posy);
  line(button[13].posx, button[13].posy, button[13].posx+60, button[13].posy);
  line(button[14].posx, button[14].posy, button[14].posx+60, button[14].posy);
  line(button[15].posx, button[15].posy, button[15].posx+60, button[15].posy);
  line(button[16].posx, button[16].posy, button[16].posx+60, button[16].posy);
  line(button[17].posx, button[17].posy, button[17].posx+60, button[17].posy);

  //splash screen
  curTime = millis();
  if (showSplash == true) {
    background(200);
    image(imgscreen, 0, 0, width, height);
    fade = 1.0f - ((curTime - prevTime) / (float) interval);
    fill(color(255, 255, 255, fade*255.0f));
    rect(0, 0, width, height);
  }

  curTime = millis();
  if (showSplash == true) {
    if ((curTime - prevTime) > interval) {
      prevTime = curTime;
      showSplash = false;
      background(200);
    }
  }



  //uses the classes save method
  if (button[9].clicked == true) {
    saves.savefile();
  }

  //wipe page button to clear the screen to the background
  if (button[10].clicked == true) {
    fill(200);
    rect(100, 0, width-200, height);
  }

  //paintbrush button to reset the squares on the right to random colors
  if ((button[15].clicked == true) && (button[15].blocked == true)) {
    for (int i = 0; i < 9; i++) {
      button[i].randomColor();
      brush[i].setColor(color(button[i].c));
    }
  }

  //eraser button to set the squares on the right to the background
  if (button[16].clicked == true) {
    for (int i = 0; i < 9; i++) {
      button[i].c = color(200);
      brush[i].setColor(color(button[i].c));
    }
  }

  //using the buttons to change the colour of the brushes
  for (int i = 0; i < 9; i++) {
    if ((button[i].clicked == true) && (button[i].blocked == true)) {
      button[i].randomColor();
      brush[i].setColor(color(button[i].c));
    }
  }

  //button to change from points to line mode
  if (button[17].clicked == true) {
    for (int i=0; i < numBr; i++) {
      brush[i].setMode(0);
    }
  }

  //button to create a single dot
  if (button[11].clicked == true) {
    setDots(1);
  }

  //button to create a triple dot
  if (button[12].clicked == true) {
    setDots(2);
  }

  //button to create a 5 dots array
  if (button[13].clicked == true) {
    setDots(3);
  }

  //button to create a the nonagon dot
  if (button[14].clicked == true) {
    setDots(4);
  }

  //different painting modes enabling specific brushes
  if (mousePressed) {
    if (m == 1) {
      brush[0].paint();
    }
  }

  if (mousePressed) {
    if (m == 2) {
      for (int i = 0; i<3; i++) {
        brush[i].paint();
      }
    }
  }

  if (mousePressed) {
    if (m == 3) {
      for (int i = 0; i<5; i++) {
        brush[i].paint();
      }
    }
  }

  if (mousePressed) {
    if (m == 4) {
      for (int i = 0; i<9; i++) {
        brush[i].paint();
      }
    }
  }
}

//mousewheel changes brush radius
public void mouseWheel(MouseEvent event) {
  float e = event.getCount();
  for (int i=0; i < numBr; i++) {
    if (e == 1) {
      brush[i].r--;
    } else if (e == -1) {
      brush[i].r++;
    }
  }
}

//sets the variable to change the drawing type
public void setDots(int mode) {
  m = mode;
}

//when q is pressed it changes the drawing type to dots
public void keyPressed() {
  if (key == 'q') {
    for (int i=0; i < numBr; i++) {
      brush[i].setMode(1);
    }
  }
}
class Brush {

  //class parameters
  int r = 20;
  float nextMouseX;
  float nextMouseY;
  float prevMouseX;
  float prevMouseY;
  Boolean pointMode = false;
  int posx = mouseX;
  int posy = mouseY;

  //default brush colour (background)
  int c = color(200);

  //class constructor
  Brush(int x, int y, int col) {
    posx = x;
    posy = y;
    c = col;
  }

  //main drawing method
  public void paint() {

    noStroke();
    fill(c);
    ellipse(posx, posy, r, r);

    //smooth drawing apparatus, using for loops
    int numFill = 100; 
    nextMouseX = posx;
    nextMouseY = posy;
    float destX = nextMouseX - prevMouseX;
    float destY = nextMouseY - prevMouseY;

    if (pointMode == false) {
      if (destX < 200) {
        if (destY < 200) {
          for (int i= 0; i < numFill; i++) {
            ellipse(prevMouseX + (destX/numFill) *i, prevMouseY + (destY/numFill)*i, r, r);
          }
        }
      }
    }

    prevMouseX = nextMouseX;
    prevMouseY = nextMouseY;
  }

  //allows the program to change the color using the method
  public void setColor(int col) {
    c = col;
  }

  //allows the program to change the radius using the method
  public void setSize(int radius) {
    r = radius;
  }

  //enables and disables the smooth drawing apparatus
  public void setMode(int point) {
    if (point == 1) {
        pointMode = true;
      }
      else pointMode = false;
  }
}

class Button {

  //timer parameters
  float curTime = 0;
  float prevTime = 0;
  int interval = 10;

  //class parameters
  int w = 40;
  int h = 40;
  int posx = 100;
  int posy = 100;
  int c = color(255, 0, 255);
  Boolean clicked = false;
  int t = 1;
  Boolean blocked = false;

  //class constructor and its many parameters
  Button(int posX, int posY, int clr, int Width, int Height, int type) {
    c = clr;
    posx = posX;
    posy = posY;
    w = Width;
    h = Height;
    t = type;
  }

  //to run continuous checks inside draw of paint
  public void update() {

    //mousepress boolean check to turn the button on when pressing
    curTime = millis();
    if (((curTime - prevTime) > interval) && (blocked == true)) {
      blocked = false;
    }

    if ((mousePressed) && (!blocked)) {
      if   ((mouseX > posx) && (mouseX < (posx+w)) && (mouseY > posy) && (mouseY < (posy+h))) {
        clicked = true;
        blocked = true;
        prevTime = curTime;
      } else { 
        clicked = false;
      }
    }
  }

  //enables the visuals in the draw method
  public void display()
  {
    //button visuals
    fill(c);
    if (t == 1) {
      rect(posx, posy, w, h);
    } else if (t == 2) {
      ellipse(posx, posy, w, h);
    }
  }

  //random color method to reduce code written in other areas
  public void randomColor() {
    c = color(random(255), random(255), random(255));
  }
}

class Save {

  //file extension addition
  int fextension = 0;

  //saves the file incrementally increasing the extension
  public void savefile() {

    save("nonagonfile" + fextension + ".png");
    fextension++;
  }
}

  public void settings() {  size(1200, 540); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "paint" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
