package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.security.MessageDigest;
import org.json.JSONArray;
import org.json.JSONObject;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets;   
import java.security.NoSuchAlgorithmException;
import ncu.im3069.demo.util.DBMgr;

public class ManagerHelper {
	
	private ManagerHelper() {
		
	}
	
	private static ManagerHelper mnh;
	
	private Connection conn = null;
	 
	private PreparedStatement pres = null;
	
	public static ManagerHelper getHelper() {

        /** Singleton檢查是否已經有MemberHelper物件，若無則new一個，若有則直接回傳 */
        if(mnh == null) mnh = new ManagerHelper();
        
        return mnh;
    }
	
	public JSONObject getAll() {
        /** 新建一個 Member 物件之 m 變數，用於紀錄每一位查詢回之會員資料 */
        Manager m = null;
        /** 用於儲存所有檢索回之會員，以JSONArray方式儲存 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`managers`";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int manager_id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int login_times = rs.getInt("login_times");
                String phone = rs.getString("phone");
                int root = rs.getInt("root");
                /** 將每一筆會員資料產生一名新Member物件 */
                m = new Manager(manager_id, email, password, name,phone, login_times);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(m.getData());
            }

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }

	public JSONObject getLoginTimesStatus(Manager m) {
        /** 用於儲存該名會員所檢索之更新時間分鐘數與會員組別之資料 */
        JSONObject jso = new JSONObject();
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;

        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`managers` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, m.getId());
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該電子郵件之資料，因此其實可以不用使用 while迴圈 */
            while(rs.next()) {
                /** 將 ResultSet 之資料取出 */
                int login_times = rs.getInt("login_times");
                String status = rs.getString("status");
                /** 將其封裝至JSONObject資料 */
                jso.put("login_times", login_times);
                jso.put("status", status);
            }
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        return jso;
    }
	
	public JSONObject update(Manager m) {
        /** 紀錄回傳之資料 */
        JSONArray jsa = new JSONArray();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `missa`.`managers` SET `name` = ? ,`password` = ? ,`phone` = ?, `modified` = ? WHERE `email` = ?";
            /** 取得所需之參數 */
            String name = m.getName();
            String email = m.getEmail();
            String password = encrypt(m.getPassword());
            String phone = m.getPhone();
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, password);
            pres.setString(3, phone);
            pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pres.setString(5, email);
            /** 執行更新之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
	
	public boolean checkDuplicate(Manager m){
        /** 紀錄SQL總行數，若為「-1」代表資料庫檢索尚未完成 */
        int row = -1;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT count(*) FROM `missa`.`managers` WHERE `email` = ?";
            
            /** 取得所需之參數 */
            String email = m.getEmail();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 讓指標移往最後一列，取得目前有幾行在資料庫內 */
            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 
         * 判斷是否已經有一筆該電子郵件信箱之資料
         * 若無一筆則回傳False，否則回傳True 
         */
        return (row == 0) ? false : true;
    }
	
	public void updateLoginTimes(Manager m) {
        /** 更新時間之分鐘數 */
        int new_times = m.getLogin_times();
        
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "Update `missa`.`managers` SET `login_times` = ? WHERE `id` = ?";
            /** 取得會員編號 */
            int id = m.getId();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, new_times);
            pres.setInt(2, id);
            /** 執行更新之SQL指令 */
            pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }
    }

	public JSONObject create(Manager m) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `missa`.`managers`(`name`, `email`, `password`, `phone`, `modified`, `created`, `login_times`,`root` )"
                    + " VALUES(?,?, ?, ?, ?, ?, ?,?)";
            
            /** 取得所需之參數 */
            String name = m.getName();
            String email = m.getEmail();
            String password = encrypt(m.getPassword());
            String phone = m.getPhone();
            int login_times = m.getLogin_times();
            
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setString(2, email);
            pres.setString(3, password);
            pres.setString(4,phone);
            pres.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pres.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pres.setInt(7, login_times);
            pres.setInt(8, 0);
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);

        return response;
    }

	public JSONObject deleteByID(int id) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 紀錄SQL總行數 */
        int row = 0;
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            
            /** SQL指令 */
            String sql = "DELETE FROM `missa`.`managers` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行刪除之SQL指令並記錄影響之行數 */
            row = pres.executeUpdate();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }

        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);

        return response;
    }

	public JSONObject getById(String id) {
		Manager m = null;
		JSONArray jsa = new JSONArray();
		String exexcute_sql = "";
		long start_time = System.nanoTime();
		int row = 0;
		ResultSet rs = null;
		
		try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`managers` WHERE `id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            /** 正確來說資料庫只會有一筆該會員編號之資料，因此其實可以不用使用 while 迴圈 */
            while(rs.next()) {
                /** 每執行一次迴圈表示有一筆資料 */
                row += 1;
                
                /** 將 ResultSet 之資料取出 */
                int Manager_id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                int login_times = rs.getInt("login_times"); 
                int root = rs.getInt("root");
                
                /** 將每一筆會員資料產生一名新Member物件 */
                m = new Manager(Manager_id, email, password, name,phone, login_times);
                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
                jsa.put(m.getData());
            }
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 紀錄程式結束執行時間 */
        long end_time = System.nanoTime();
        /** 紀錄程式執行時間 */
        long duration = (end_time - start_time);
        
        /** 將SQL指令、花費時間、影響行數與所有會員資料之JSONArray，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }	

	public JSONObject getByEmail(String email ,String password) {
		JSONObject jso = new JSONObject();
		String exexcute_sql = "";
		long start_time = System.nanoTime();
		int row = 0;
		ResultSet rs = null;
		JSONObject response = new JSONObject();
		
		try {
			 
			/** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`managers` WHERE `email` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();           
            System.out.println(exexcute_sql);                      
                       
            while(rs.next()) {	  
	            	int Manager_id = rs.getInt("id");
	                String name = rs.getString("name");
	               
	               
	                String phone = rs.getString("phone");
	                int login_times = rs.getInt("login_times"); 
	                
	                
	                /** 將每一筆會員資料產生一名新Member物件 */
	                Manager m = new Manager(Manager_id, email, password, name,phone, login_times);
	                /** 取出該名會員之資料並封裝至 JSONsonArray 內 */
	                jso.put("manager", m.getData());
            		String str2 = new String(rs.getString("password"));          		
            		System.out.println(encrypt(password).equals(str2));
            		if(encrypt(password).equals(str2)==true) {
            			jso.put("status", "logined");
            		}
            		else {
            			jso.put("status", "fail1");
            		}
            			
            		System.out.printf("1.  %s%n",encrypt(password));
            		System.out.printf("1.  %s%n",str2);
            }
            
            
            //String s1 = "12345678";  
            //System.out.println("\n" + s1 + ":" + toHexString(getSHA(s1)));   
            
        } catch (SQLException e) {
            /** 印出JDBC SQL指令錯誤 **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** 若錯誤則印出錯誤訊息 */
            e.printStackTrace();
        } finally {
            /** 關閉連線並釋放所有資料庫相關之資源 **/
            DBMgr.close(rs, pres, conn);
        }
	
		
		response.put("sql",exexcute_sql);
		response.put("row",row);
		response.put("time",row);
		response.put("data",jso);
		
		return response;
	}
	//public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    //{  
        // Static getInstance method is called with hashing SHA  
    //    MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
    //    return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    //} 
    
    //public static String toHexString(byte[] hash,byte[] hash2) 
    //{ 
        // Convert byte array into signum representation  w
    //    BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
    //    StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
     //   while (hexString.length() < 32)  
     //   {  
     //       hexString.insert(0, '0');  
     //   }  
  
     //   return hexString.toString();  
    //}
	public String encrypt(String s){  
		  MessageDigest sha = null;
		  
		  try{
		   sha = MessageDigest.getInstance("SHA-1");  
		   sha.update(s.getBytes());  
		  }catch(Exception e){
		   e.printStackTrace();
		   return "";
		  }

		  return byte2hex(sha.digest());  
		 
		 }
	private static String byte2hex(byte[] b){
	     String hs="";
	     String stmp="";
	     for (int n=0;n<b.length;n++){
	      stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
	      if (stmp.length()==1) hs=hs+"0"+stmp;
	      else hs=hs+stmp;
	     }
	     return hs.toUpperCase();
	    }
}
