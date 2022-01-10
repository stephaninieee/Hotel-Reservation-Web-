package ncu.im3069.demo.controller;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Comment;
import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Product;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.tools.JsonReader;

@MultipartConfig
@WebServlet("/api/product.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductHelper ph =  ProductHelper.getHelper();

    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */

        String id = jsr.getParameter("id");
        

        JSONObject resp = new JSONObject();
        /** 判斷該字串是否存在，若存在代表要取回購物車內產品之資料，否則代表要取回全部資料庫內產品之資料 */
        if (!id.isEmpty()) {
          System.out.print("H1");
          JSONObject query = ph.getById(Integer.parseInt(id));
         
          resp.put("status", "200");
          resp.put("message", "所有購物車之商品資料取得成功");
          resp.put("response", query);
        }
        else {
        	
          JSONObject query = ph.getAll();
          

          resp.put("status", "200");
          resp.put("message", "所有商品資料取得成功");
          resp.put("response", query);
        }

        jsr.response(resp, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
		/*Part filePart = request.getPart("file");
        System.out.print(filePart);        
        String image = filePart.getSubmittedFileName();
<<<<<<< HEAD
        //System.out.print(image);
        filePart.write("D:\\github\\SA\\SA_12\\SA_12\\images" + image);
=======
        filePart.write("D:\\github\\SA_12\\SA_12\\images\\" + image);*/
		
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
		
//>>>>>>> 
        
		
        //JSONObject jso = jsr.getObject();
      
        /** 取出經解析到JSONObject之Request參數 */
        //String name = jso.getString("name");
        //String price = jso.getString("price");
        //String name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");  
       // String price = request.getParameter("price");
        
        //String describe = new String(request.getParameter("describe").getBytes("iso-8859-1"), "utf-8");  
        
        
        /** 建立一個新的會員物件 */
       
       
    	/** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
        JSONObject data = ph.create(jso);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 註冊會員資料...");
        resp.put("response", data);
        
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        //jsr.response(resp, response);
        response.getWriter().print(resp);
                          
	}
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	    /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
	    JsonReader jsr = new JsonReader(request);
	    JSONObject jso = jsr.getObject();
	    
	    
	    /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
	    JSONObject data = ph.update(jso);
	    
	    /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	    JSONObject resp = new JSONObject();
	    resp.put("status", "200");
	    resp.put("message", "成功! 更新會員資料...");
	    resp.put("response", data);
	    
	    /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	    jsr.response(resp, response);
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int id = jso.getInt("id");
        
        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = ph.deleteByID(id);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "商品移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }

}
