package broadcast;

import com.example.doit.MainService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartupBrd extends BroadcastReceiver
{public static String ACTION="android.intent.action.BOOT_COMPLETED";

	@Override
	public void onReceive(Context context, Intent intent) 
	{	if(intent.getAction().equals(ACTION))
	    {value.constant.nowBrd="stb";
		  context.startService(new Intent(context,MainService.class));
		 
	    //Toast.makeText(context,"brc",10000).show();
	    }
	}

}
