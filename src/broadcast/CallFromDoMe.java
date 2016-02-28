package broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.doit.MainService;

public class CallFromDoMe extends BroadcastReceiver
{  @Override
	public void onReceive(Context context, Intent intent)
    {if(intent.getAction().equals("CallFromDoMe"))
    	{Log.v("","DoMe Call");
    	context.startService(new Intent(context,MainService.class));
    	
    	
    	}
    	}

}
