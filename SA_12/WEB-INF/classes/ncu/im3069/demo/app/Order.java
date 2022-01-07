package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

import org.json.*;

public class Order {

    /** id，訂單編號 */
    private int id;   

    /** last_name，會員姓 */
    private int room_id;

    /** email，會員電子郵件信箱 */
    private int coupon_id;

    /** address，會員地址 */
    private float price;

    /** phone，會員手機 */
    private String status;
      
    private Date check_in;
    
    private Date check_out;

    
    
    /** create，訂單創建時間 */
    private Timestamp create;
    

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
    public Order(int id, int room_id, int coupon_id, float price, String status , Date check_in ,Date check_out) {
        this.id = id;
        this.room_id = room_id;
        this.coupon_id = coupon_id;
        this.price = price;
        this.status = status;
        this.check_in = check_in;
        this.check_out = check_out;
        this.create = Timestamp.valueOf(LocalDateTime.now());   
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
    public int getRoomId() {
        return this.room_id;
    }

    /**
     * 取得訂單會員的姓
     *
     * @return String 回傳訂單會員的姓
     */
    public int getCouponId() {
        return this.coupon_id;
    }

    /**
     * 取得訂單信箱
     *
     * @return String 回傳訂單信箱
     */
    public float getPrice() {
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
    /**
     * 取得訂單基本資料
     *
     * @return JSONObject 取得訂單基本資料
     */
    public JSONObject getOrderData() {
        JSONObject jso = new JSONObject();
        jso.put("id", getId());
        jso.put("room_id", getRoomId());
        jso.put("coupon_id", getCouponId());
        jso.put("price", getPrice());
        jso.put("status", getStatus());
        jso.put("check_in", getCheckIn());
        jso.put("check_out", getCheckOut());
        

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
