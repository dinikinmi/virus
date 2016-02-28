package internet;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

public class gj 
{ public static String packageSentData(Map<String,String> params)
	{StringBuffer stringBuffer=new StringBuffer();
	 String returnString=new String();
	 try
	 { for(Map.Entry<String,String> entry:params.entrySet())
	   {stringBuffer.append(entry.getKey());
	   stringBuffer.append("=");
//	   Log.v("entryGetValue",entry.getValue());
	   String enCodeU8=URLEncoder.encode(entry.getValue(),"utf-8");
	   String enCodeGbk=URLEncoder.encode(entry.getValue(),"gbk");
	   stringBuffer.append(URLEncoder.encode(entry.getValue(),"utf-8"));
	   
//	   Log.v("encode utf-8",URLEncoder.encode(entry.getValue(),"utf-8"));
//	   Log.v("encode gbk",URLEncoder.encode(entry.getValue(),"gbk"));
//	   Log.v("decode u8",URLDecoder.decode(enCodeU8,"utf-8"));
//	   Log.v("decode gbk",URLDecoder.decode(enCodeGbk,"gbk"));
	   
	   
	   stringBuffer.append("&");
	    }
	    stringBuffer.deleteCharAt(stringBuffer.length()-1);
	     returnString=stringBuffer.toString();
	    }
	    catch(Exception e)
	    {e.printStackTrace();}
	  return returnString;
	}
	 
	

	
}
