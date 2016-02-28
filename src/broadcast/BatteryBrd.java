package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.doit.MainService;

public class BatteryBrd extends BroadcastReceiver
{	@Override
	public void onReceive(Context context, Intent intent) 
	{
	if(intent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
	 {value.constant.nowBrd="btr";
	 context.startService(new Intent(context,MainService.class));
	 }
	}

}
