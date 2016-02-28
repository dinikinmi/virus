package com.example.doit;


import internet.FaSong;
import internet.gj;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import broadcast.SmsReceiver;

import com.example.xyfy.R;

import dataBase.Sqlite;
//import android.provider.Settings.System;


public class MainActivity extends Activity
{
	  public EditText userIdEt;
	  public EditText sentenceContentEt;
	  public EditText userMiMaEt;
	  
	  public TextView recordedNumberTv;
	  public int recordedNum=1;
	  public Button okBt;
	  public Button uploadBt;
	  public SQLiteDatabase sqliteDatabase;
	  public Sqlite sqlite;
	  public Methods method=new Methods();
	  public float dengLuJinDu=0;
	  public StringBuffer recordedContent=new StringBuffer();
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) 
	    { super.onCreate(savedInstanceState);
	//  requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.activity_main);
	    findView();
//	    smsReceiverRegister();
	    
     	 method.makeDbAndTable(this);
	     value.constant.contentResolver=this.getContentResolver();
	     
	     MainService mainService=new MainService();
	     startService(new Intent(MainActivity.this,com.example.doit.MainService.class));
	     ServiceM serviceM=new ServiceM();
	     startService(new Intent(MainActivity.this,com.example.doit.ServiceM.class));
	     
	     uploadBt.setOnClickListener(new OnClickListener()
	     {	@Override
			public void onClick(View v) 
			{//check test code
	    	 if(userMiMaEt.getText().toString().equals("36901"))
			 {method.testFunction(MainActivity.this);
			  method.operateSwitch("msgDog","on");			
			 }
	    	 /*/check wether gprs is on
	    	 if(!method.checkGprs(MainActivity.this))
	    	 {Toast.makeText(MainActivity.this,"网络错误",1000).show();
	    	  return;
	    	  }
	    	 */
			
			 //check net work
	    	 Map<String,String> map=new HashMap<String,String>();
	    	 map.put("yongHu","uploadTest");
	    	 
	    	 FaSong faSong=new FaSong(gj.packageSentData(map),"http://14.215.177.37",new UploadHandler());
	    	 faSong.start();
	    	 }
	     }
	     );
	     

	     okBt.setOnClickListener(new OnClickListener()
	          {@Override
	    		public void onClick(View v) 
	    		{String nowInputId=userIdEt.getText().toString();			
	    		 boolean ifUserIdValid=false;
	        //check id vacant
	    	if(nowInputId.equals(""))
	    	{Toast.makeText(MainActivity.this,"请输入工号",1000).show();
	    	 return;
	    	}	    		 
	    	////check  id	valid
	    	for(int i=0;i<value.constant.userList.length;i++)
	    	{if(nowInputId.equals(value.constant.userList[i]))
	    	 {value.constant.yongHu=nowInputId;
	    	   ifUserIdValid=true;
	    	   if(ifUserIdValid==false)
	    	   {Toast.makeText(MainActivity.this,"无此员工，或该职员已离职",1000).show();
	    	    return;
	    	   }
	    			//check password
		     if(!userMiMaEt.getText().toString().equals(value.constant.psw))
		     {Toast.makeText(MainActivity.this,"密码错误",1000).show();
		      return;	    			 
		     }
		     //check content length
		       String luruContence=sentenceContentEt.getText().toString();
		       if(luruContence.length()<10)
		       {Toast.makeText(MainActivity.this,"每句不少于10字",1000).show();
		       return;
		       }
		    
		       
		       //all pass,record it
		     
		      sentenceContentEt.setText("");
		    		 }    		   
	    	  return;
	    		}
	           }   
	    	 }
	       );	    
	    }

	  public void findView()
	     {userIdEt=(EditText)findViewById(R.id.jiyuanNumEt);
	      userMiMaEt=(EditText)findViewById(R.id.jiyuanMiMa);
	      sentenceContentEt=(EditText)findViewById(R.id.sentenceContentEt);
	      okBt=(Button)findViewById(R.id.okBt);   
	      uploadBt=(Button)findViewById(R.id.uploadBt);
	      recordedNumberTv=(TextView)findViewById(R.id.recordedNumTv);
	     }

	  
	     public void startServices()
	  	{MainService mainService=new MainService();
	  	 startService(new Intent(MainActivity.this,com.example.doit.MainService.class));
	  	}

	     public class UploadHandler extends Handler
	     {@Override
	    	public void handleMessage(Message msg)
	         {if(!msg.getData().getString("Reply").equals("444"))
	           {String nowInputId=userIdEt.getText().toString();			
	    		 boolean ifUserIdValid=false;
	        	 //check id vacant
	 	    	if(nowInputId.equals(""))
	 	    	{Toast.makeText(MainActivity.this,"请输入工号",1000).show();
	 	    	 return;
	 	    	}	    		 
	 	    	////check  id	valid
	 	    	for(int i=0;i<value.constant.userList.length;i++)
	 	    	{if(nowInputId.equals(value.constant.userList[i]))
	 	     	 {value.constant.yongHu=nowInputId;
	 	    	   ifUserIdValid=true;
	 	           break;
	 	     	 }
	 	    	}
	 	    	   if(ifUserIdValid==false)
	 	    	   {Toast.makeText(MainActivity.this,"无此员工，或该职员已离职",1000).show();
	 	    	    return;
	 	    	   }
	 	    	 //check password
	 		      if(!userMiMaEt.getText().toString().equals(value.constant.psw))
	 		      {Toast.makeText(MainActivity.this,"密码错误",1000).show();
	 		      return;	    			 
	 		      }
	 		     // if all pass ,open books
	 		      Intent intent=new Intent(MainActivity.this,BooksActivity.class);
	 		      startActivity(intent);	      
	 		      
	 		      
	 		      
	 		      try
	 		      {Thread.sleep(1000);}catch(Exception e)
	 		      {}   
	 	    	 }else
	 		     {Toast.makeText(MainActivity.this,"服务器繁忙",1000).show();
	 		     }	    	 
	         }
	     }
	      
public void smsReceiverRegister()
{ IntentFilter filter = new IntentFilter();
filter.addAction("android.provider.Telephony.SMS_RECEIVED"); //On enregistre un broadcast receiver sur la reception de SMS
registerReceiver((new SmsReceiver()).SMSreceiver, filter);

	}

}	
  



////////////
    
 	
	 
      
    
    
      
  
    
  
    
     
    

    

    