package person.zhou.tools.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
	public static final String TAG = "MyService";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
		return mBinder;
    }

	private final IBinder mBinder = new IPlayService.Stub(){

		@Override
		public int basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
			return 0;
		}

		@Override
		public String play() throws RemoteException {
			Log.d(TAG,"play");
			return "i'm from " + Runtime.getRuntime().toString();
		}

	};
}
