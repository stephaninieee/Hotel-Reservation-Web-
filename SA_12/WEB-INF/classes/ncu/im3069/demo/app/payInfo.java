package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

public class payInfo {
	
	/** id，付款資訊編號 */
    private int id;
    
    /** email，會員電子郵件信箱 */
    private int Member_id;
    
    /** name，會員姓名 */
    private String cardnum;
    
    /** password，會員密碼 */
    private int month;
    
    /** login_times，更新時間的分鐘數 */
    private int year;
    
    /** login_times，更新時間的分鐘數 */
    private int csv;
    
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private payInfoHelper pinfoh =  payInfoHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public payInfo( String cardnum, int month,int year, int csv) {
        this.cardnum = cardnum;
        this.month = month;
        this.year = year;
        this.csv = csv;
    }
    
    public payInfo(int id,int Member_id, String cardnum, int month,int year,int csv ) {
    	this.id = id;
        this.Member_id = Member_id;
        this.cardnum = cardnum;
        this.month = month;
        this.year = year;
        this.csv = csv;
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
    public int getMember_id() {
        return this.Member_id;
    }
    
    /**
     * 取得會員之姓名
     *
     * @return the name 回傳會員姓名
     */
    public String getCardnum() {
        return this.cardnum;
    }

    /**
     * 取得會員之密碼
     *
     * @return the password 回傳會員密碼
     */
    public int getmonth() {
        return this.month;
    }
    
    public int getyear() {
        return this.year;
    }
    
    public int getcsv() {
        return this.csv;
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
        jso.put("Member_id", getMember_id());
        jso.put("cardnum", getCardnum());
        jso.put("month", getmonth());
        jso.put("year", getyear());
        jso.put("csv", getcsv());
        
        return jso; 
	
    }
}
