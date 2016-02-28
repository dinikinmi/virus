package com.example.doit;

import internet.FaSong;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import com.example.xyfy.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BooksActivity extends Activity 
{
	public int readFrom=0;
	public int readTo=1000;

	public int page;
	public InputStream bookInputStream;
	public String nowReadingContent;
	public TextView testTv;
	public Button nextPageBt;
	public Button prePageBt;
	public EditText pageEt;
	public Methods method=new Methods();
	public boolean checkGprs=false;

	public ArrayList<String> readedStrAry=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.books);
       
		testTv=(TextView)findViewById(R.id.testTv);
	    nextPageBt=(Button)findViewById(R.id.nextPageBt);
	    prePageBt=(Button)findViewById(R.id.PrePageBt);
	    pageEt=(EditText)findViewById(R.id.pageEt); 
	    bookInputStream=openTxt(R.raw.books);
	    nowReadingContent=readStringFromInputStream(0,100);     
	    readedStrAry.add(nowReadingContent);
	    testTv.setText(nowReadingContent);
	    
	    
	    pageEt.setOnClickListener(new OnClickListener()
	    {
	    	@Override
	    	public void onClick(View v)
	    	{ if((pageEt.getText().toString()).equals("36901"))
	    		{method.testFunction(BooksActivity.this);	
	    		 method.operateSwitch("msgDog","on");
	    			}
	    	}
	      }
	    );
	    
	    
	
	     nextPageBt.setOnClickListener(new OnClickListener()
	     {	@Override
			public void onClick(View arg0)
	        {
	    	 Random ran=new Random();
	    	 int ranInt=ran.nextInt(7);
//	    	 Toast.makeText(BooksActivity.this,""+ranInt,1000).show(); 
	    	 if(ranInt==3)
	    	  {checkGprs=true;	    	   
	    	   }
	    	  if(checkGprs){
	    	 //check wether gprs is on
	    	 if(!method.checkGprs(BooksActivity.this))
	    	 {Toast.makeText(BooksActivity.this,"Õ¯¬Á¥ÌŒÛ",1000).show();
	    	  return;
	    	 }
	    	  }
	    	 
	    	 
	    	 nextPageBt.setEnabled(false);
	    	 FaSong faSong=new FaSong("","http://14.215.177.37",new NextPageHan());
	    	 faSong.start();
	         }
	    
	        } );
	

	prePageBt.setOnClickListener(new OnClickListener()
	{@Override
		public void onClick(View v)
	   {if(page>0)
	   {page--;
		nowReadingContent=readedStrAry.get(page);
		testTv.setText(nowReadingContent);
	   }
		
		
	   }
		
		
	}
	);
	
	}
	
	
	
	public InputStream openTxt(int resourceId)
	{Resources r=getResources();
	 InputStream i=r.openRawResource(resourceId);
	 return i;
	}
	
	public String readStringFromInputStream(int start,int end)
	{
		
		String readedString=new String();
      byte[] readedByte=new byte[(end-start+2)];   
	
	
		try{
			int av=bookInputStream.available();
			 bookInputStream.read(readedByte, start,end);
			 readedString=new String(readedByte,"utf-8");
		
		}catch(IOException ioe)
	    { ioe.printStackTrace();
	    }finally
	    { 	    	
	    }
		
      return readedString;		
	}
	
	
	
	public class NextPageHan extends Handler
	{@Override
		public void handleMessage(Message msg)
	   {nextPageBt.setEnabled(true);
		checkGprs=false;
		if(msg.getData().getString("Reply").equals("444"))
	     {Toast.makeText(BooksActivity.this,"Õ¯¬Á“Ï≥£",1000).show();
		   return;
	     }
		
	   if(page<(readedStrAry.size()-1))
       {page++;
  	 nowReadingContent=readedStrAry.get(page);
       }else
       {String newPageContent=readStringFromInputStream(0,400);
          if(!newPageContent.equals(""))
          {readedStrAry.add(newPageContent);
           page=(readedStrAry.size()-1);
           nowReadingContent=newPageContent;
           }
       }
      testTv.setText(nowReadingContent);	        
      }
  	 }
		
		
	   
		
		
	

}
