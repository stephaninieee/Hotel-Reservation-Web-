package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.*;

import ncu.im3069.demo.util.DBMgr;
import ncu.im3069.demo.app.Product;

public class ProductHelper {
    private ProductHelper() {
        
    }
    
    private static ProductHelper ph;
    private Connection conn = null;
    private PreparedStatement pres = null;
    
    public static ProductHelper getHelper() {
        /** Singleton檢查是否已經有ProductHelper物件，若無則new一個，若有則直接回傳 */
        if(ph == null) ph = new ProductHelper();
        
        return ph;
    }
    
    public JSONObject getAll() {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
    	Product p = null;
        /** 用於儲存所有檢索回之商品，以JSONArray方式儲存 */
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
            String sql = "SELECT * FROM `missa`.`products`";
            
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
                int product_id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String address = rs.getString("address");
                String avgrate = rs.getString("avgrate");
                String describe = rs.getString("describe");
                double lng = rs.getDouble("lng");
                double lat = rs.getDouble("lat");
                int manager_id = rs.getInt("manager_id");
                System.out.print(manager_id);
                
                
                
                
                JSONObject product_tag = new JSONObject();
                
                
                /** 將每一筆商品資料產生一名新Product物件 */
                p = new Product(product_id, name, price, image, address, avgrate, describe, lng, lat, manager_id);
                product_tag = p.getData();
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                              
                jsa.put(putTag(product_tag, rs));
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
    

    
    public JSONObject getById(int id) {
        /** 新建一個 Product 物件之 m 變數，用於紀錄每一位查詢回之商品資料 */
        Product p = null;
        JSONArray jsa = new JSONArray();
        int row = 1;
        /** 紀錄程式開始執行時間 */
        long start_time = System.nanoTime();
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        /** 儲存JDBC檢索資料庫後回傳之結果，以 pointer 方式移動到下一筆資料 */
        ResultSet rs = null;
        
        try {
        	
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "SELECT * FROM `missa`.`products` WHERE `products`.`id` = ? LIMIT 1";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** 執行查詢之SQL指令並記錄其回傳之資料 */
            rs = pres.executeQuery();

            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** 透過 while 迴圈移動pointer，取得每一筆回傳資料 */
            while(rs.next()) {
            	
                /** 將 ResultSet 之資料取出 */
                int product_id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String address = rs.getString("address");
                String avgrate = rs.getString("avgrate");
                String describe = rs.getString("describe");
                double lng = rs.getDouble("lng");
                double lat = rs.getDouble("lat");
                int manager_id = rs.getInt("manager_id");
                
                JSONObject product_tag = new JSONObject();
                
                
                /** 將每一筆商品資料產生一名新Product物件 */
                p = new Product(product_id, name, price, image, address, avgrate, describe, lng, lat, manager_id);
                product_tag = p.getData();
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                              
                jsa.put(putTag(product_tag, rs));
                
                
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
    
    
    /**
     * 更新一名會員之會員資料
     *
     * @param m 一名會員之Member物件
     * @return the JSONObject 回傳SQL指令執行結果與執行之資料
     */
    public JSONObject update(JSONObject jso) {
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
            String sql ="Update `missa`.`products` SET `name` = ? , `price`= ? ,`image` = ? , `address` = ? , `avgrate` = ? , `baby` = ? , `breakfast` = ? , `wifi` = ? , `smoking` = ? , `shower` = ? , `KTV` = ? , `van` = ? , `parking` = ? , `bath` = ? , `swimming` = ? , `beach` = ? , `TV` = ? , `air` = ? , `laundry` = ? , `bar` = ? , `business` = ? , `game` = ? , `SPA` = ? , `describe` = ? , `lng` = ? , `lat` = ? , `manager_id` = ? WHERE `id` = ?";
          
            /** 取得所需之參數 */
            String name = jso.getString("name");
            double price = jso.getDouble("price");
            String image = jso.getString("image");    
            String address = jso.getString("address"); 
            int avgrate = Integer.parseInt(jso.getString("star"));
            boolean baby = jso.getBoolean("baby");
            boolean breakfast = jso.getBoolean("breakfast");
            boolean wifi = jso.getBoolean("wifi");
            boolean smoking = jso.getBoolean("smoking");
            boolean shower = jso.getBoolean("shower");
            boolean ktv = jso.getBoolean("ktv");
            boolean van = jso.getBoolean("van");
            boolean parking = jso.getBoolean("parking");
            boolean bath = jso.getBoolean("bath");
            boolean swimming = jso.getBoolean("swimming");
            boolean beach = jso.getBoolean("beach");
            boolean tv = jso.getBoolean("tv");
            boolean air = jso.getBoolean("air");
            boolean landuary = jso.getBoolean("landuary");
            boolean bar = jso.getBoolean("bar");
            boolean business = jso.getBoolean("business");
            boolean play = jso.getBoolean("play");
            boolean spa = jso.getBoolean("spa");
            String describe = jso.getString("description");
            double lng = jso.getDouble("lng");
            double lat = jso.getDouble("lat");
            int manager_id = jso.getInt("manager_id");
            int id = jso.getInt("room_id");
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, name);
            pres.setDouble(2, price);
            pres.setString(3, image);
            pres.setString( 4, address);
            pres.setInt( 5, avgrate);
            pres.setBoolean( 6, baby);
            pres.setBoolean( 7, breakfast);
            pres.setBoolean( 8, wifi);
            pres.setBoolean( 9, smoking);
            pres.setBoolean( 10, shower);
            pres.setBoolean( 11, ktv);
            pres.setBoolean( 12, van);
            pres.setBoolean( 13, parking);
            pres.setBoolean( 14, bath);
            pres.setBoolean( 15, swimming);
            pres.setBoolean( 16, beach);
            pres.setBoolean( 17, tv);
            pres.setBoolean( 18, air);
            pres.setBoolean( 19, landuary);
            pres.setBoolean( 20, bar);
            pres.setBoolean( 21, business);
            pres.setBoolean( 22, play);
            pres.setBoolean( 23, spa);
            pres.setString( 24, describe);
            pres.setDouble( 25, lng);
            pres.setDouble(26, lat);
            pres.setInt(27, manager_id);
            pres.setInt(28, id);
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
            String sql = "DELETE FROM `missa`.`products` WHERE `id` = ? LIMIT 1";
            
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
    
    public JSONObject create(JSONObject jso) {
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
            String sql = "INSERT INTO `missa`.`products`(`name`, `price`, `image`, `address`, `avgrate`, `baby`, `breakfast`, `wifi`, `smoking`, `shower`, `KTV`, `van`, `parking`, `bath`, `swimming`, `beach`, `TV`, `air`, `laundry`, `bar`, `business`, `game`, `SPA`, `describe`, `lng`, `lat`, `manager_id`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String name = jso.getString("name");
            double price = jso.getDouble("price");
            String image = jso.getString("image");    
            String address = jso.getString("address"); 
            int avgrate = Integer.parseInt(jso.getString("star"));
            boolean baby = jso.getBoolean("baby");
            boolean breakfast = jso.getBoolean("breakfast");
            boolean wifi = jso.getBoolean("wifi");
            boolean smoking = jso.getBoolean("smoking");
            boolean shower = jso.getBoolean("shower");
            boolean ktv = jso.getBoolean("ktv");
            boolean van = jso.getBoolean("van");
            boolean parking = jso.getBoolean("parking");
            boolean bath = jso.getBoolean("bath");
            boolean swimming = jso.getBoolean("swimming");
            boolean beach = jso.getBoolean("beach");
            boolean tv = jso.getBoolean("tv");
            boolean air = jso.getBoolean("air");
            boolean landuary = jso.getBoolean("landuary");
            boolean bar = jso.getBoolean("bar");
            boolean business = jso.getBoolean("business");
            boolean play = jso.getBoolean("play");
            boolean spa = jso.getBoolean("spa");
            String describe = jso.getString("description");
            double lng = jso.getDouble("lng");
            double lat = jso.getDouble("lat");
            int manager_id = jso.getInt("manager_id");
            
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pres.setString(1, name);
            pres.setDouble(2, price);
            pres.setString(3, image);
            pres.setString( 4, address);
            pres.setInt( 5, avgrate);
            pres.setBoolean( 6, baby);
            pres.setBoolean( 7, breakfast);
            pres.setBoolean( 8, wifi);
            pres.setBoolean( 9, smoking);
            pres.setBoolean( 10, shower);
            pres.setBoolean( 11, ktv);
            pres.setBoolean( 12, van);
            pres.setBoolean( 13, parking);
            pres.setBoolean( 14, bath);
            pres.setBoolean( 15, swimming);
            pres.setBoolean( 16, beach);
            pres.setBoolean( 17, tv);
            pres.setBoolean( 18, air);
            pres.setBoolean( 19, landuary);
            pres.setBoolean( 20, bar);
            pres.setBoolean( 21, business);
            pres.setBoolean( 22, play);
            pres.setBoolean( 23, spa);
            pres.setString( 24, describe);
            pres.setDouble( 25, lng);
            pres.setDouble(26, lat);
            pres.setInt(27, manager_id);
         
            
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
    
    public JSONObject putTag(JSONObject product_tag, ResultSet rs) {
    	ArrayList<Boolean> hashtag = new ArrayList<Boolean>();
    	
    	
		try {
			boolean baby = rs.getBoolean("baby");
			hashtag.add(baby);             
	        boolean breakfast =  rs.getBoolean("breakfast");
	        hashtag.add(breakfast);  
	        boolean wifi =  rs.getBoolean("wifi");
	        hashtag.add(wifi);  
	        boolean smoking =  rs.getBoolean("smoking");
	        hashtag.add(smoking);  
	        boolean shower =  rs.getBoolean("shower");
	        hashtag.add(shower);  
	        boolean KTV =  rs.getBoolean("KTV");
	        hashtag.add(KTV);  
	        boolean van =  rs.getBoolean("van");
	        hashtag.add(van);  
	        boolean parking =  rs.getBoolean("parking");
	        hashtag.add(parking);  
	        boolean bath =  rs.getBoolean("bath");
	        hashtag.add(bath);  
	        boolean swimming =  rs.getBoolean("swimming");
	        hashtag.add(swimming);  
	        boolean beach =  rs.getBoolean("beach");
	        hashtag.add(beach);  
	        boolean TV =  rs.getBoolean("TV");
	        hashtag.add(TV);  
	        boolean air =  rs.getBoolean("air");
	        hashtag.add(air); 
	        boolean laundry =  rs.getBoolean("laundry");
	        hashtag.add(laundry);
	        boolean bar =  rs.getBoolean("bar");
	        hashtag.add(bar); 
	        boolean business =  rs.getBoolean("business");
	        hashtag.add(business); 
	        boolean game =  rs.getBoolean("game");
	        hashtag.add(game);
	        boolean SPA =  rs.getBoolean("SPA");
	        hashtag.add(SPA);
	        for (int i = 0; i < hashtag.size(); i++) {
            	
            	product_tag.put("tag"+i, hashtag.get(i));
        	
            }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return product_tag;
    }
}
