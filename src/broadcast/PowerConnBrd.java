package broadcast;

import com.example.doit.MainService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerConnBrd extends BroadcastReceiver
{  @Override
	public void onReceive(Context context, Intent intent)
    {String Action="android.intent.action.ACTION_POWER_CONNECTED";
	  if(intent.getAction().equals(Action))
	   {value.constant.nowBrd="pwc";
		context.startService(new Intent(context,MainService.class));
	    }
		
	}

}
