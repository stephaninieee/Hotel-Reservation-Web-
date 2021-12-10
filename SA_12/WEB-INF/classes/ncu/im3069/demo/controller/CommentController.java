package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.tools.JsonReader;
import ncu.im3069.demo.app.Comment;
import ncu.im3069.demo.app.CommentHelper;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("/api/CommentController.do")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommentHelper cmh =  CommentHelper.getHelper();

    public CommentController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String Room_id = jsr.getParameter("Room_id");

        JSONObject resp = new JSONObject();
        /** 判斷該字串是否存在，若存在代表要取回購物車內產品之資料，否則代表要取回全部資料庫內產品之資料 */
        
        JSONObject query = cmh.getAllByRoom_id(Integer.parseInt(Room_id));
        resp.put("status", "200");
        resp.put("message", "所有評論資料取得成功");
        resp.put("response", query);
        

        jsr.response(resp, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        String Room_id = jso.getString("Room_id");
        String Member_id = jso.getString("Member_id");
        String star = jso.getString("star");
        String comment = jso.getString("comment");
        
        
        /** 建立一個新的會員物件 */
        Comment c = new Comment(Integer.parseInt(Room_id), Integer.parseInt(Member_id), Integer.parseInt(star), comment);
        
        /** 後端檢查是否有欄位為空值，若有則回傳錯誤訊息 */
        if(star.isEmpty() || Member_id.isEmpty() || Room_id.isEmpty()) {
            /** 以字串組出JSON格式之資料 */
            String resp = "{\"status\": \'400\', \"message\": \'欄位不能有空值\', \'response\': \'\'}";
            /** 透過JsonReader物件回傳到前端（以字串方式） */
            jsr.response(resp, response);
        }
        else {
        	/** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
            JSONObject data = cmh.create(c);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            JSONObject resp = new JSONObject();
            resp.put("status", "200");
            resp.put("message", "成功! 註冊會員資料...");
            resp.put("response", data);
            
            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
            jsr.response(resp, response);
        }                     
	}
	
	
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("id");
        
        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = cmh.deleteByID(id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "評論移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

}
