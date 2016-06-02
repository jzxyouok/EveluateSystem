package ldu.eveluatesystem;

import java.util.List;

import bean.SelfInfo;
import bean._User;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText numEdit,passwordEdit;
	public final int sendToastWhat=1,loginResultWhat=2;
	public final String sendToastKey="sendToastKey",loginResultKey="loginResultKey";
	Handler handler=new Handler(){
		public void handleMessage(Message message){
		switch (message.what) {
		case sendToastWhat:
			String toast=message.getData().getString(sendToastKey);
			Toast.makeText(LoginActivity.this, toast, Toast.LENGTH_SHORT).show();
			break;

		case loginResultWhat:
			String loginResult=message.getData().getString(loginResultKey);
			if(loginResult.equals("success")){
				sendToast("登录成功");
				Intent intent=new Intent();
            	intent.setClass(LoginActivity.this, MainActivity.class);
            	startActivity(intent);
            	finish();
			}
			break;
		}	
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		
		
	}
	public void initView(){
		passwordEdit=(EditText)findViewById(R.id.password);
		numEdit=(EditText)findViewById(R.id.num);
		Button login=(Button)findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			String num=numEdit.getText().toString().trim();
			String password=passwordEdit.getText().toString().trim();
			login(num,password);
			}
		});
	}
	protected void login(String num, final String password) {
		// TODO Auto-generated method stub
		BmobQuery<_User> query = new BmobQuery<_User>();
		//查询playerName叫“比目”的数据
		query.addWhereEqualTo("num", num);
		query.addWhereEqualTo("password",password);
		//返回50条数据，如果不加上这条语句，默认返回10条数据
		query.setLimit(1);
		//执行查询方法
		query.findObjects(this, new FindListener<_User>() {
		        @Override
		        public void onSuccess(List<_User> object) {
		            // TODO Auto-generated method stub
//		            toast("查询成功：共"+object.size()+"条数据。");
		            for (_User user : object) {
		            
		            	SelfInfo.userId=user.getObjectId();
		            	SelfInfo.department=user.getDepartment();
		            	SelfInfo.major=user.getMajor();
		            	SelfInfo.birth=user.getBirth();
		            	SelfInfo.num=user.getNum();
		            	SelfInfo.userName=user.getUsername();
		            	sendMessage(loginResultWhat,loginResultKey,"success");
		            
		            }
		            
		        }
		        @Override
		        public void onError(int code, String msg) {
		            // TODO Auto-generated method stub
//		            toast("查询失败："+msg);
		        	sendToast("登录失败");
		        }
		});
		
	}
		
	public void sendMessage(int what,String key,String value){
		Message message=new Message();
		message.what=what;
		Bundle bundle=new Bundle();
		bundle.putString(key, value);
		message.setData(bundle);
		handler.sendMessage(message);
	}
	public void sendToast(String toast){
		Message message=new Message();
		message.what=sendToastWhat;
		Bundle bundle=new Bundle();
		bundle.putString(sendToastKey, toast);
		message.setData(bundle);
		handler.sendMessage(message);
	}
}
