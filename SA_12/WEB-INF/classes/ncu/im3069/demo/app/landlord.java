package ncu.im3069.demo.app;

import java.sql.Array;

import org.json.JSONObject;

public class landlord extends Manager{	
	int root;
	public landlord(int id, String email, String password, String name,String phone, int login_times,int root) {		
		super(id, email, password, name, phone, login_times);
		this.root = root;
	}
	public int getRoot() {
		return this.root;
	}
	
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("name", getName());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("login_times", getLogin_times());        
        jso.put("root", root);
        return jso;
	}
}
