public class ManagerPage extends ManagerCatalogPage {
  public void init() {
  	String[] ids = { "PS2", "PS3", "PS4" ,"PSX","XBox1", "XBox2", "XBox3","WII-1X", "WII-2X", "WIIU"};
    setItems(ids);
    setTitle("Store Products");
  }
}