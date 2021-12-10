package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Member
 * Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Comment {
    
    /** id，評論編號 */
    private int id;
    
    /** email，會員電子郵件信箱 */
    private int Member_id;
    
    /** name，會員姓名 */
    private int Room_id;
    
    /** password，會員密碼 */
    private int star;
    
    /** login_times，更新時間的分鐘數 */
    private String comment;
    
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private CommentHelper cmh =  CommentHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Comment(int Member_id, int Room_id, int star, String comment) {
        this.Member_id = Member_id;
        this.Room_id = Room_id;
        this.star = star;
        this.comment = comment;
    }
    
    public Comment(int id, int Member_id, int Room_id, int star, String comment) {
    	this.id = id;
        this.Member_id = Member_id;
        this.Room_id = Room_id;
        this.star = star;
        this.comment = comment;
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
    public int getRoom_id() {
        return this.Room_id;
    }

    /**
     * 取得會員之密碼
     *
     * @return the password 回傳會員密碼
     */
    public int getstar() {
        return this.star;
    }
    
    /**
     * 取得更新資料時間之分鐘數
     *
     * @return the login times 回傳更新資料時間之分鐘數
     */
    public String getcomment() {
        return this.comment;
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
        jso.put("Room_id", getRoom_id());
        jso.put("star", getstar());
        jso.put("comment", getcomment());
        
        return jso;
    }
    
    
    
}