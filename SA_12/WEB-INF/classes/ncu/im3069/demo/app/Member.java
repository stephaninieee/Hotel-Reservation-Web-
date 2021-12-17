package ncu.im3069.demo.app;

import org.json.*;
import java.util.Calendar;

public class Member {
    
    private int id;
    private String email;
    private String name;
    private String password;
    private String phone;
    private int login_times;
    private MemberHelper mh =  MemberHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(String name, String email, String password,String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone=phone;
        update();
    }
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(int id, String name,String email, String password, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone=phone;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        getLoginTimesStatus();

    }
    
    public Member(int id, String name, String email, String password, String phone, int login_times) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone=phone;
        this.login_times = login_times;
       
    }
    
    public int getID() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }
 
    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
    public String getPhone() {
    	return this.phone;
    }
    
    public int getLoginTimes() {
        return this.login_times;
    }

    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
        Calendar calendar = Calendar.getInstance();
        this.login_times = calendar.get(Calendar.MINUTE);
      
        /** 檢查該名會員是否已經在資料庫 */
        if(this.id != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
            mh.updateLoginTimes(this);
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = mh.update(this);
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
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("phone", getPhone());
        jso.put("login_times", getLoginTimes());
    
        return jso;
    }
    
    /**
     * 取得資料庫內之更新資料時間分鐘數與會員組別
     *
     */
    private void getLoginTimesStatus() {
        /** 透過MemberHelper物件，取得儲存於資料庫的更新時間分鐘數與會員組別 */
        JSONObject data = mh.getLoginTimesStatus(this);
        /** 將資料庫所儲存該名會員之相關資料指派至Member物件之屬性 */
        this.login_times = data.getInt("login_times");
       
    }

}