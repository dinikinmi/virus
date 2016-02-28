package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LockBrd extends BroadcastReceiver
{  

	@Override
	public void onReceive(Context context, Intent intent) 
	{	if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF))
	    {Log.v("lock","lock");
	    context.startService(new Intent(context,com.example.doit.MainService.class));
		}
	}

}
