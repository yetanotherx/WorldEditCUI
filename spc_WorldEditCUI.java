public class spc_WorldEditCUI extends SPCPlugin {
   @Override
   public String getVersion() {
      return "1.5_01";
   }
   @Override
   public String getName() 
   {
      return "WorldEditCUI";
   }
   @Override
   public void handleCUIEvent(String type, String params[]) {
      mod_WorldEditCUI.handleEvent( type, params);
   }
}