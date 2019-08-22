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
  color c = color(200);

  //class constructor
  Brush(int x, int y, color col) {
    posx = x;
    posy = y;
    c = col;
  }

  //main drawing method
  void paint() {

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
  void setColor(color col) {
    c = col;
  }

  //allows the program to change the radius using the method
  void setSize(int radius) {
    r = radius;
  }

  //enables and disables the smooth drawing apparatus
  void setMode(int point) {
    if (point == 1) {
        pointMode = true;
      }
      else pointMode = false;
  }
}
