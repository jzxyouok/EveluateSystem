package ldu.eveluatesystem;

import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.Bmob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

public class WelcomeActivity extends Activity {
	
	public final String appId="e1d18a55311dd3b85e2f3fececcac86d";
	Handler handler=new Handler(){
		public void handleMessage(Message message){
			switch(message.what){
			case 1:
				Intent intent=new Intent();
				intent.setClass(WelcomeActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			}
		}
	};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //Ä¬ÈÏ³õÊ¼»¯
        Bmob.initialize(this, appId);
        Timer timer=new Timer();
        timer.schedule(task, 3000);
    }
    TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
		handler.sendEmptyMessage(1);	
		}
	};
}
