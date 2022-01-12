package ncu.im3069.demo.app;

import java.util.Calendar;

import org.json.*;

public class Manager {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private int login_times;
	private ManagerHelper mnh =  ManagerHelper.getHelper();
	
	
	
	public Manager(String name,String email,String password,String phone,int root){
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		update();
	}
	
	public Manager(int id, String email, String password, String name,String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone= phone;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        getLoginTimesStatus();
        
    }
	
	public Manager(int id, String email, String password, String name,String phone, int login_times) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.login_times = login_times;  
    }
	
	public int getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getEmail() {
		return this.email;
	}
	public String getPassword() {
		return this.password;
	}
	public String getPhone() {
		return this.phone;
	}
	public int getLogin_times() {
		return this.login_times;
	}
	

	public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("phone", getPhone());
        jso.put("login_times", getLogin_times());        
        
        return jso;
    }

	private void getLoginTimesStatus() {
        /** 透過MemberHelper物件，取得儲存於資料庫的更新時間分鐘數與會員組別 */
        JSONObject data = mnh.getLoginTimesStatus(this);
        /** 將資料庫所儲存該名會員之相關資料指派至Member物件之屬性 */
        this.login_times = data.getInt("login_times");
        
    }

	public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
        Calendar calendar = Calendar.getInstance();
        this.login_times = calendar.get(Calendar.MINUTE);
        
        if(this.id != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
            mnh.updateLoginTimes(this);
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = mnh.update(this);
        }
        
        return data;
    }
}
