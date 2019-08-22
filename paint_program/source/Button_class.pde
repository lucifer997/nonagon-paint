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
  color c = color(255, 0, 255);
  Boolean clicked = false;
  int t = 1;
  Boolean blocked = false;

  //class constructor and its many parameters
  Button(int posX, int posY, color clr, int Width, int Height, int type) {
    c = clr;
    posx = posX;
    posy = posY;
    w = Width;
    h = Height;
    t = type;
  }

  //to run continuous checks inside draw of paint
  void update() {

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
  void display()
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
  void randomColor() {
    c = color(random(255), random(255), random(255));
  }
}
