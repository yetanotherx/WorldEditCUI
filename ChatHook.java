import java.util.ArrayList;


public class ChatHook {
	protected static ArrayList<ChatHookable> hooks = new ArrayList<ChatHookable>(); 
	public static boolean runhooks(String chat)
	{
		for (int i=0; i<hooks.size(); i++)
		{
			if (hooks.get(i).processChat(chat))
			{
				return true;
			}
		}
		return false;
	}
	public static void addHook(ChatHookable hook)
	{
		hooks.add(hook);
	}
	public static void delHook(ChatHookable hook)
	{
		hooks.remove(hook);
	}
}
