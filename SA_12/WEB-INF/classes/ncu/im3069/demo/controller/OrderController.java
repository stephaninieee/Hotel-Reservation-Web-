package ncu.im3069.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.Product;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.tools.JsonReader;

import javax.servlet.annotation.WebServlet;
import java.util.Date;
@WebServlet("/api/order.do")
public class OrderController extends HttpServlet {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** ph，ProductHelper 之物件與 Product 相關之資料庫方法（Sigleton） */
    private ProductHelper ph =  ProductHelper.getHelper();

    /** oh，OrderHelper 之物件與 order 相關之資料庫方法（Sigleton） */
	private OrderHelper oh =  OrderHelper.getHelper();

    public OrderController() {
        super();
    }

    /**
     * 處理 Http Method 請求 GET 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);

        /** 取出經解析到 JsonReader 之 Request 參數 */
        String id = jsr.getParameter("id");

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();

        /** 判斷該字串是否存在，若存在代表要取回個別訂單之資料，否則代表要取回全部資料庫內訂單之資料 */
        if (!id.isEmpty()) {
          /** 透過 orderHelper 物件的 getByID() 方法自資料庫取回該筆訂單之資料，回傳之資料為 JSONObject 物件 */
          JSONObject query = oh.getById(id);
          resp.put("status", "200");
          resp.put("message", "單筆訂單資料取得成功");
          resp.put("response", query);
        }
        else {
        	
          /** 透過 orderHelper 物件之 getAll() 方法取回所有訂單之資料，回傳之資料為 JSONObject 物件 */
          JSONObject query = oh.getAll();
          resp.put("status", "200");
          resp.put("message", "所有訂單資料取得成功");
          resp.put("response", query);
        }

        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}

    /**
     * 處理 Http Method 請求 POST 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();

        /** 取出經解析到 JSONObject 之 Request 參數 */

        String member_name = jso.getString("member_name");
        String room_name = jso.getString("room_name");
        String coupon_name = jso.getString("coupon_name");

        int price = jso.getInt("price");
        String status = jso.getString("status"); 
        String check_in = jso.getString("check_in");
        String check_out = jso.getString("check_out");
        

        Date checkIndate = null;
        try {
        	checkIndate = new SimpleDateFormat("yyyy-MM-dd").parse(check_in);
        } catch (ParseException e) {
            System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", "dd/MM/yyyy", check_in);
            // Parse date string [2019/12/31] with pattern [yyyy-MM-dd] error.
        }        
        
        Date checkOutdate = null;//new SimpleDateFormat("dd/MM/yyyy").parse(check_out);
        try {
        	checkOutdate = new SimpleDateFormat("yyyy-MM-dd").parse(check_out);
        } catch (ParseException e) {
            System.out.printf("Parse date string [%1$s] with pattern [%2$s] error.%n", "dd/MM/yyyy", check_out);
            // Parse date string [2019/12/31] with pattern [yyyy-MM-dd] error.
        } 
           
        //JSONArray item = jso.getJSONArray("item");
        //JSONArray quantity = jso.getJSONArray("quantity");
      
        int manager_id = jso.getInt("manager_id");
        
        /** 建立一個新的訂單物件 */
        Order od = new Order(member_name,room_name,coupon_name,price,status,checkIndate,checkOutdate, manager_id);
        
        /** 透過 orderHelper 物件的 create() 方法新建一筆訂單至資料庫 */
        JSONObject result = oh.create(od);

        /** 設定回傳回來的訂單編號與訂單細項編號 */
        od.setId((int) result.getLong("order_id"));
        

        /** 新建一個 JSONObject 用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "訂單新增成功！");
        resp.put("response", od.getOrderAllInfo());

        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        int id = jso.getInt("id");
        
        JSONObject query = oh.deleteByID(id);
        
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "會員移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
	}
}
