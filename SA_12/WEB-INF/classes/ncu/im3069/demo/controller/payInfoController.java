package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ncu.im3069.demo.app.payInfo;
import ncu.im3069.demo.app.payInfoHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class payInfoController
 */
@WebServlet("/api/payInfoController.do")
public class payInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private payInfoHelper mh =  payInfoHelper.getHelper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
		System.out.print("ya");
		
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        String cardnum = jso.getString("card_no");
        int month = jso.getInt("card_month");
        int year = jso.getInt("card_year");
        int csv = jso.getInt("csv");
        
        /** 建立一個新的會員物件 */
        payInfo p = new payInfo(cardnum, month,	year,csv);
        
        JSONObject data = mh.create(p);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "成功! 註冊會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
        
	}

}

