package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.demo.app.Manager;
import ncu.im3069.demo.app.ManagerHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class ManagerController1
 */
@WebServlet("/api/ManagerController.do")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ManagerHelper mnh =  ManagerHelper.getHelper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		JsonReader jsr = new JsonReader(request);	        
		String email = jsr.getParameter("email");
		System.out.printf("%s???",email);
        	/** 判斷該字串是否存在，若存在代表要取回個別會員之資料，否則代表要取回全部資料庫內會員之資料 */
        	if (email.isEmpty()) {
            	/** 透過MemberHelper物件之getAll()方法取回所有會員之資料，回傳之資料為JSONObject物件 */
            	JSONObject query = mnh.getAll();         
            	/** 新建一個JSONObject用於將回傳之資料進行封裝 */ 
            	JSONObject resp = new JSONObject();
            	resp.put("status", "200");
            	resp.put("message", "所有房東資料取得成功");
            	resp.put("response", query);
    
            	/** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            	jsr.response(resp, response);
        	}
        	else {                     	
        		JSONObject data = mnh.getByEmail(email);
            	/** 新建一個JSONObject用於將回傳之資料進行封裝 */
            	JSONObject resp = new JSONObject();            	            	            		
            	resp.put("status", "200");
            	resp.put("message", "房東資料取得成功");
            	resp.put("response", data);   
            	/** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            	jsr.response(resp, response);
        	
        	}
            	
        }
        
        
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        String email = jso.getString("email");
        String password = jso.getString("password");
        String name = jso.getString("name");
        String phone = jso.getString("phone");
        /** 建立一個新的會員物件 */
        Manager m = new Manager(name,email,password,phone,0);
        
        /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
        if(email.isEmpty() || password.isEmpty() || name.isEmpty()) {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
        /** 透過MemberHelper物件的checkDuplicate()檢查該會員電子郵件信箱是否有重複 */
        else if (!mnh.checkDuplicate(m)) {
            /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
            JSONObject data = mnh.create(m);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 註冊房東資料...");
            resp.put("response", data);
            
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }
        else {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'新增帳號失敗，此E-Mail帳號重複！\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
	        JsonReader jsr = new JsonReader(request);
	        JSONObject jso = jsr.getObject();
	        
	        /** 取出經解析到JSONObject之Request參數 */
	        int id = jso.getInt("id");
	        
	        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
	        JSONObject query = mnh.deleteByID(id);
	        
	        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	        JSONObject resp = new JSONObject();
	        resp.put("status", "200");
	        resp.put("message", "會員移除成功！");
	        resp.put("response", query);

	        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	        jsr.response(resp, response);
	    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
	        JsonReader jsr = new JsonReader(request);
	        JSONObject jso = jsr.getObject();
	        
	        /** 取出經解析到JSONObject之Request參數 */
	        int id = jso.getInt("id");
	        String email = jso.getString("email");
	        String password = jso.getString("password");
	        String name = jso.getString("name");
	        String phone = jso.getString("phone");

	        /** 透過傳入之參數，新建一個以這些參數之會員Member物件 */
	        Manager m = new Manager(id,email,password,name,phone);
	        
	        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
	        JSONObject data = m.update();
	        
	        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	        JSONObject resp = new JSONObject();
	        resp.put("status", "200");
	        resp.put("message", "成功! 更新房東資料...");
	        resp.put("response", data);
	        
	        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	        jsr.response(resp, response);
	    }
}
