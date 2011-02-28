package se.thematrix.vehiclelocator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends Activity {
	Button btnSendSms;
	EditText txtPhoneNo;
	EditText txtMessage;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnSendSms = (Button)findViewById(R.id.btnSendSms);
        txtPhoneNo = (EditText)findViewById(R.id.txtPhoneNo);
        txtMessage = (EditText)findViewById(R.id.txtMessage);
        
        btnSendSms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String phoneNo = txtPhoneNo.getText().toString();
				String message = txtMessage.getText().toString();
				
				if(phoneNo.length() > 0 && message.length() > 0){
					sendSMS(phoneNo, message);
				} else {
					Toast.makeText(getBaseContext(), 
							R.string.enter_phone_message, 
							Toast.LENGTH_SHORT).show();
				}
			}
		});
    }


	protected void sendSMS(String phoneNo, String message) {
//		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, SMS.class), 0);
//		SmsManager sms = SmsManager.getDefault();
//		sms.sendTextMessage(phoneNo, null, message, pi, null);
		
		String sent = "SMS_SENT";
		String delivered = "SMS_DELIVERED";
		
		PendingIntent sentPi = PendingIntent.getBroadcast(this, 0, new Intent(sent), 0);
		PendingIntent deliveredPi = PendingIntent.getBroadcast(this, 0, new Intent(delivered), 0);
		
		//sms_sent
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), R.string.sms_sent, Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getBaseContext(), R.string.generic_faliure, Toast.LENGTH_LONG);
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getBaseContext(), R.string.no_service, Toast.LENGTH_LONG);
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getBaseContext(), R.string.null_pdu, Toast.LENGTH_LONG);
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getBaseContext(), R.string.radio_off, Toast.LENGTH_LONG);
					break;
				}
			}
		}, new IntentFilter(sent));

		//sms_delivered
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getBaseContext(), R.string.sms_delivered, Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getBaseContext(), R.string.sms_not_delivered, Toast.LENGTH_LONG);
					break;
				}
			}
		}, new IntentFilter(delivered));
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, sentPi, deliveredPi);
	}
}