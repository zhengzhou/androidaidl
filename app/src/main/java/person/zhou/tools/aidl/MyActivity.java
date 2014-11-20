package person.zhou.tools.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MyActivity extends Activity implements View.OnClickListener{

	public static final String TAG = "aidl";
	@InjectView(R.id.textView)
	TextView mTextView;
	@InjectView(R.id.button)
	Button mButton1;
	@InjectView(R.id.button2)
	Button mButton2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		ButterKnife.inject(this);
		mButton1.setOnClickListener(this);
		mButton2.setOnClickListener(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	IPlayService mService;
	private ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
			mService = IPlayService.Stub.asInterface(iBinder);
			d("connected");
		}

		@Override
		public void onServiceDisconnected(ComponentName componentName) {
			mService = null;
			d("Disconnected");
		}
	};

	void d(String d) {
		Log.d(TAG, d);
	}

	@Override
	public void onClick(View view) {
		if(view == mButton1){
			Intent intent = new Intent(this,MyService.class);
			bindService(intent,connection,BIND_AUTO_CREATE);
		}else if(view == mButton2){
			if(mService!=null){
				try {
					String result = mService.play();
					mTextView.setText(result);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}
}