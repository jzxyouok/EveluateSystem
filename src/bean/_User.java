package bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

public class _User extends BmobUser{


	private  String birth;
	private  String major;//专业
	private  String department;//院系
	private  String num;//学号

	
	public  String getNum() {
		return num;
	}
	public  void setNum(String num) {
		this.num = num;
	}
	public  String getDepartment() {
		return department;
	}
	public  void setDepartment(String department) {
		this.department = department;
	}
	
	public  String getBirth() {
		return birth;
	}
	public  void setBirth(String birth) {
		this.birth = birth;
	}
	public  String getMajor() {
		return major;
	}
	public  void setMajor(String major) {
		this.major = major;
	}
	
}
