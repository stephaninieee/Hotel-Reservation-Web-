package ncu.im3069.demo.app;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

import org.json.*;

public class Order {

    /** id，訂單編號 */
    private int id;   

    private String member_name;
    
    /** last_name，會員姓 */
    private String room_name;

    /** email，會員電子郵件信箱 */
    private String coupon_name;

    /** address，會員地址 */
    private int price;

    /** phone，會員手機 */
    private String status;
      
    private Date check_in;
    
    private Date check_out;

    
    
    /** create，訂單創建時間 */
    private Timestamp create;
    private int manager_id;
    

    /** oph，OrderItemHelper 之物件與 Order 相關之資料庫方法（Sigleton） */
    private OrderItemHelper oph = OrderItemHelper.getHelper();

    /**
     * 實例化（Instantiates）一個新的（new）Order 物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立訂單資料時，產生一個新的訂單
     *
     * @param first_name 會員名
     * @param last_name 會員姓
     * @param email 會員電子信箱
     * @param address 會員地址
     * @param phone 會員姓名
     */

    public Order(int id,String member_name, String room_name, String coupon_name, int price, String status , Date check_in ,Date check_out,Timestamp create,int manager_id) {
        this.id = id;
        this.member_name=member_name;
        this.room_name = room_name;
        this.coupon_name = coupon_name;
        this.price = price;
        this.status = status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.create = create;   
        this.manager_id = manager_id;
    }
    
    public Order(String member_name, String room_name, String coupon_name, int price, String status , Date check_in ,Date check_out,int manager_id) {
       
        this.member_name=member_name;
        this.room_name = room_name;
        this.coupon_name = coupon_name;
        this.price = price;
        this.status = status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.create = Timestamp.valueOf(LocalDateTime.now());  
        this.manager_id = manager_id;
    }
    
    

    /**
     * 實例化（Instantiates）一個新的（new）Order 物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改訂單資料時，新改資料庫已存在的訂單
     *
     * @param first_name 會員名
     * @param last_name 會員姓
     * @param email 會員電子信箱
     * @param address 會員地址
     * @param phone 會員姓名
     * @param create 訂單創建時間
     * @param modify 訂單修改時間
     */
    public Order() {
    
    }
    
    /**
     * 設定訂單編號
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 取得訂單編號
     *
     * @return int 回傳訂單編號
     */
    public int getId() {
        return this.id;
    }

    /**
     * 取得訂單會員的名
     *
     * @return String 回傳訂單會員的名
     */
    public String getMemberName() {
        return this.member_name;
    }
    
    public String getRoomName() {
        return this.room_name;
    }

    /**
     * 取得訂單會員的姓
     *
     * @return String 回傳訂單會員的姓
     */
    public String getCouponName() {
        return this.coupon_name;
    }

    /**
     * 取得訂單信箱
     *
     * @return String 回傳訂單信箱
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * 取得訂單創建時間
     *
     * @return Timestamp 回傳訂單創建時間
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * 取得訂單修改時間
     *
     * @return Timestamp 回傳訂單修改時間
     */
    public Date getCheckIn() {
        return this.check_in;
    }

    /**
     * 取得訂單地址
     *
     * @return String 回傳訂單地址
     */
    public Date getCheckOut() {
        return this.check_out;
    }

    public Timestamp getCreate() {
    	return this.create;
    }
    
    public int getManager_id() {
    	return this.manager_id;
    }
    /**
     * 取得訂單基本資料
     *
     * @return JSONObject 取得訂單基本資料
     */
    public JSONObject getOrderData() {
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("member_name", getMemberName());
        jso.put("room_name", getRoomName());
        jso.put("coupon_name", getCouponName());
        jso.put("price", getPrice());
        jso.put("status", getStatus());
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");
    	String for_check_in = myFmt.format(getCheckIn());
    	String for_check_out = myFmt.format(getCheckOut());
        jso.put("check_in", for_check_in);
        jso.put("check_out", for_check_out);
        jso.put("create", getCreate());
        jso.put("manager_id", getManager_id());
        

        return jso;
    }

    

    /**
     * 取得訂單所有資訊
     *
     * @return JSONObject 取得訂單所有資訊
     */
    public JSONObject getOrderAllInfo() {
        JSONObject jso = new JSONObject();
        jso.put("order_info", getOrderData());
        

        return jso;
    }

    /**
     * 設定訂單產品編號
     */
   

}
