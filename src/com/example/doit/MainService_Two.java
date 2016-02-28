package com.example.doit;

import internet.FaSong;
import internet.gj;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dataBase.Sqlite;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import broadcast.LockBrd;

public class  MainService_Two extends Service
{ ServiceHan serhan;
  boolean siLe=true;
  boolean meiSi=false;
  String yongHu=value.constant.yongHu;
  String tomcatPort=":8080";
  String jiaoZuoYeUrl="http://"+value.constant.zhuJiUrl+":"+value.constant.tomcatPort+"/after/JiaoZuoYe";
  String baoDaoUrl="http://"+value.constant.zhuJiUrl+value.constant.tomcatPort+"/after/BaoDao";
  public SQLiteDatabase sqliteDatabase;
  public Methods method=new Methods();
  public int service_One_LifeLight_Old=0;
  
  @Override
   public void onCreate()
    {method.makeDbAndTable(getApplicationContext());
	 setFore();
//	Log.v("onCreate","onCreate"); 
   }
  @Override
  public int onStartCommand(Intent intent,int flags,int startId)
  {method.makeDbAndTable(getApplicationContext());
   method.baoDaoGetMission(value.constant.baoDaoDiZhi,getApplicationContext());
   serhan=new ServiceHan();
   ServiceThread serviceThread=new ServiceThread();
   serviceThread.start();
  // method.makeDbAndTable(getApplicationContext());
   return super.onStartCommand(intent,START_STICKY,startId);
  }	   
  
@Override
public IBinder onBind(Intent intent)
	{return null;
	}

public void regSreenBrd()
	{IntentFilter intentFil=new IntentFilter();
	 intentFil.addAction(Intent.ACTION_SCREEN_OFF);
	 intentFil.addAction(Intent.ACTION_SCREEN_ON);
	 getApplicationContext().registerReceiver(new LockBrd(),intentFil);
 	}
public class ServiceHan extends Handler
	{@Override 
		public void handleMessage(Message msg)
	     {if(value.constant.service_Two_LifeLight<10001)
		   {value.constant.service_Two_LifeLight++;}
	     else{value.constant.service_One_LifeLight=0;}
	      if(!(service_One_LifeLight_Old==value.constant.service_One_LifeLight))
		  {service_One_LifeLight_Old=value.constant.service_One_LifeLight;}
	      else
	      {///if it dosest change ,means it is dead
	    	  MainService mainService=new MainService();
	    	  Intent intent=new Intent(getApplicationContext(),com.example.doit.MainService.class);
	    	  startService(intent);  }
	       }
	}
public class ServiceThread extends Thread
	  {	@Override 
		public void run()
     	{ while(true)
      {serhan.sendEmptyMessage(1);  
      } 	     
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

@Override
public void onDestroy()
{MainService mainService=new MainService();
 Intent intent=new Intent(getApplicationContext(),com.example.doit.MainService.class);
 startService(intent);
}
	
}









