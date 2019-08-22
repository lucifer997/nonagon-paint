class Save {

  //file extension addition
  int fextension = 0;

  //saves the file incrementally increasing the extension
  void savefile() {

    save("nonagonfile" + fextension + ".png");
    fextension++;
  }
}
