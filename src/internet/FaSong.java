package internet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class  FaSong extends Thread
{	public String sentString ;
    public String hostURL;
    public Handler handler;
    public Handler processHandler;
    public URL url;
    public boolean successFlag=true;
    
	 public FaSong(String sentString,String URL,Handler handler)
	 {this.sentString=sentString;
	  this.hostURL=URL;
	  this.handler=handler;
	 }
	 
	 

	 public FaSong(String sentString,String URL,
			 Handler handler,
			 Handler proccessHan)
	 {this.sentString=sentString;
	  this.processHandler=processHandler;
	  this.hostURL=URL;
	  this.handler=handler;
	 } 
	 
   	@Override
	public void run()
	{   try {   
		int sleepTime=(int) (Math.random()*500);
		sleep(3000);
		byte data[]=sentString.getBytes();
//		Log.v("data",new String(data,"utf-8"));
		url=new URL(hostURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setConnectTimeout(5000); //设置连接超时时间
        httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");  //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data); 
            //wait for the sever to reply
            InputStream inputStream=httpURLConnection.getInputStream();
            Reader reader=new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferReader=new BufferedReader(reader);
            String Line="";
            StringBuffer stringBuffer=new StringBuffer();
            while((Line=bufferReader.readLine())!=null)
            	{stringBuffer.append(Line);
            	};
            	
           String Reply=stringBuffer.toString();
//             Log.v("","sag  "+Reply);  
          if(!(Reply.equals("null")))
          {	Message msg=handler.obtainMessage();         
            Bundle b=new Bundle();
            b.putString("Reply",Reply);
            msg.setData(b);
            msg.sendToTarget();
          }
            }
		    catch(MalformedURLException e)
		    {e.printStackTrace();
		     successFlag=false; 
		    }
		    catch(IOException e)
		    {successFlag=false;
		     e.printStackTrace(); }
			catch(Exception e)
		   {successFlag=false;}
		
		  if(successFlag==false)
		   {Message msg=handler.obtainMessage();         
           Bundle b=new Bundle();
           b.putString("Reply","444");
           msg.setData(b);
           msg.sendToTarget();
		   }
      }
     	
     	
     	
     	
     	
}