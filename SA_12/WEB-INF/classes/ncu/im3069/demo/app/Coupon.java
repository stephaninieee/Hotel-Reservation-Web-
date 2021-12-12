package ncu.im3069.demo.app;

import java.util.Calendar;

import org.json.JSONObject;

public class Coupon {
	 /** id，評論編號 */
    private int id;
    
    /** email，會員電子郵件信箱 */
    private String name;
    
    /** name，會員姓名 */
    private double discount;
    
    /** password，會員密碼 */
    private String start_time;
    
    /** login_times，更新時間的分鐘數 */
    private String end_time;
    
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private CouponHelper ch =  CouponHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Coupon(String name, double discount, String start_time, String end_time) {
        this.name = name;      
        this.discount = discount;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    
    public Coupon(int id, String name, double discount, String start_time, String end_time) {
    	this.id = id;
        this.name = name;      
        this.discount = discount;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    
    /**
     * 取得會員之編號
     *
     * @return the id 回傳會員編號
     */
    public int getID() {
        return this.id;
    }

    /**
     * 取得會員之電子郵件信箱
     *
     * @return the email 回傳會員電子郵件信箱
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 取得會員之姓名
     *
     * @return the name 回傳會員姓名
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * 取得會員之密碼
     *
     * @return the password 回傳會員密碼
     */
    public String getStart_time() {
        return this.start_time;
    }
    
    /**
     * 取得更新資料時間之分鐘數
     *
     * @return the login times 回傳更新資料時間之分鐘數
     */
    public String getEnd_time() {
        return this.end_time;
    }
    
    
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
    
        
        /** 檢查該名會員是否已經在資料庫 */
        if(this.id != 0) {
            
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = ch.update(this);
        }
        
        return data;
    }
    
    
    
    
    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("id", getID());        
        jso.put("name", getName());
        jso.put("discount", getDiscount());
        jso.put("start_time", getStart_time());
        jso.put("end_time", getEnd_time());
        
        return jso;
    }
    
}
