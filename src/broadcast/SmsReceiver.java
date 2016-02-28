package broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver
{
	public  BroadcastReceiver SMSreceiver = new BroadcastReceiver() 
	 { 	private final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
		   @Override
	        public void onReceive(Context context, Intent intent)
		   {
    		if(intent.getAction().equals(SMS_RECEIVED)) { //On vérifie que c'est bien un event de SMS_RECEIVED même si c'est obligatoirement le cas.
			Log.i("SMSReceived", "onReceive sms !");
			Bundle bundle = intent.getExtras();
			if (bundle != null) 
		    { Object[] pdus = (Object[]) bundle.get("pdus");
		   	final SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++) 
				{messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				}
				if(messages.length > -1)
				{ final String messageBody = messages[0].getMessageBody();
				 final String phoneNumber = messages[0].getDisplayOriginatingAddress();
				 Log.v("testMsg","test msg"+messageBody);
				 Toast.makeText(context,messageBody,1000).show();
				 /*
				 if(authorizedNumbersCall != null)
				 {
					boolean found = false;
					boolean foundk = false;
					for(String s: authorizedNumbersSMS) {
					if(s.equals(phoneNumber))
					found = true;
					}
				 if(!found)
				return;
				if(authorizedNumbersKeywords != null) {
				for(String s: authorizedNumbersKeywords) {
				if(messageBody.contains(s))
				foundk = true;
				}
				if(!foundk)
				return;
					}
				Log.i("Client","Incoming call authorized");
				}
					
				Intent serviceIntent = new Intent(context, Client.class); // On lance le service
				serviceIntent.setAction("SMSReceiver");
				context.startService(serviceIntent);
				*/
				}
					}
			 	}
	        }
	 };
	
	
}
