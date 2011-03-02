package se.thematrix.vehiclelocator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMS extends Activity {
	
	public static final String TAG = "SMS";
	private static final int PICK_CONTACT = 0;
	
	Button btnSendSms;
	Button btnShowLatLon;
	Button btnSelectContact;
	
	EditText txtPhoneNo;
	EditText txtMessage;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//        						WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.main);
        
        btnSendSms = (Button)findViewById(R.id.btnSendSms);
        btnShowLatLon = (Button)findViewById(R.id.btnShowLatLon);
        btnSelectContact = (Button)findViewById(R.id.btnSelectContact);
        
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
        
        btnShowLatLon.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
				Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				
				Log.d(TAG, "location fetched!!");
				
				if(location != null){
					Toast.makeText(getBaseContext(), "location är inte null!!", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getBaseContext(), "location är null!!", Toast.LENGTH_SHORT).show();
				}
				
//				String message = "lat: "+location.getLatitude()+", lon: "+location.getLongitude();
//				
//				Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
			}
		});
        
        btnSelectContact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_PICK);
			intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
			startActivityForResult(intent, PICK_CONTACT);
			}
		});
    }


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(this, "requestCode: "+requestCode+" resultCode: "+resultCode, Toast.LENGTH_LONG).show();
		if(requestCode == PICK_CONTACT){
			if(resultCode == RESULT_OK){
				Cursor contact = managedQuery(data.getData(), null, null, null, null);
				contact.moveToFirst();
				String name = contact.getString(contact.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
				
				txtPhoneNo.setText(name);
				
				Toast.makeText(this, "name: "+name, Toast.LENGTH_LONG).show();
				
			}
		}
//		super.onActivityResult(requestCode, resultCode, data);
	}


	protected void sendSMS(String phoneNo, String message) {
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


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.torsk:
			Toast.makeText(getBaseContext(), R.string.sms_delivered, Toast.LENGTH_SHORT);
			return true;
		case R.id.kolja:
			Toast.makeText(getBaseContext(), R.string.sms_not_delivered, Toast.LENGTH_LONG);
			return true;
		default:
			super.onOptionsItemSelected(item);
		}
		return false;
	}
}