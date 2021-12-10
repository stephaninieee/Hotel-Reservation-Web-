package ncu.im3069.demo.app;

import org.json.*;

public class Manager {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private int login_times;
	private String status;
	private ManagerHelper mnh =  ManagerHelper.getHelper();
	
	public Manager(String name,String email,String password,String phone){
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		update();
	}
	
	public Manager(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        getLoginTimesStatus();
        /** 計算會員之組別 */
        calcAccName();
    }
	
	public Manager(int id, String email, String password, String name, int login_times, String status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.login_times = login_times;
        this.status = status;
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
	public String getStatus() {

		return this.status;
	}

	public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("login_times", getLogin_times());
        jso.put("status", getStatus());
        
        return jso;
    }

	
}
