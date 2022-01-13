package ncu.im3069.demo.app;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.text.SimpleDateFormat;

import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class OrderHelper {
    
    private static OrderHelper oh;
    private Connection conn = null;
    private PreparedStatement pres = null;
    private OrderItemHelper oph =  OrderItemHelper.getHelper();
    
    private OrderHelper() {
    }
    
    public static OrderHelper getHelper() {
        if(oh == null) oh = new OrderHelper();
        
        return oh;
    }
    
    public JSONObject create(Order order) {
        /** 記錄實際執行之SQL指令 */
        String exexcute_sql = "";
        long id = -1;
        JSONArray opa = new JSONArray();
        
        try {
            /** 取得資料庫之連線 */
            conn = DBMgr.getConnection();
            /** SQL指令 */
            String sql = "INSERT INTO `missa`.`orders`(`member_name`, `room_name`, `coupon_name`, `price`, `status`, `check_in`, `check_out`,`create`,`manager_id`)"
            		
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

            
           
            
            String status = order.getStatus();            
            Date check_in = order.getCheckIn();
            Date check_in8 = addHoursToJavaUtilDate(check_in,8);

                  
            
            /** 取得所需之參數 */

            String member_name = order.getMemberName();
            String room_name = order.getRoomName();
            String coupon_name = order.getCouponName();
            float price = order.getPrice();
            
            Date check_out = order.getCheckOut();
            Date check_out8 = addHoursToJavaUtilDate(check_out,8);           
            java.sql.Date checkInDate = new java.sql.Date(check_in8.getTime());
            java.sql.Date checkOutDate = new java.sql.Date(check_out8.getTime());
            Timestamp create = order.getCreate();
            int manager_id = order.getManager_id();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pres.setString(1, member_name);
            pres.setString(2, room_name);
            pres.setString(3, coupon_name);
            pres.setFloat(4, price);


            pres.setString(5, status);

            pres.setDate(6, checkInDate);
            pres.setDate(7, checkOutDate);
            
            
            
            pres.setTimestamp(8, create);
            

            pres.setInt(9, manager_id);

            
            
            /** 執行新增之SQL指令並記錄影響之行數 */
            pres.executeUpdate();
            
            /** 紀錄真實執行的SQL指令，並印出 **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            ResultSet rs = pres.getGeneratedKeys();

            
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

        /** 將SQL指令、花費時間與影響行數，封裝成JSONObject回傳 */
        JSONObject response = new JSONObject();
        response.put("order_id", id);
        response.put("order_product_id", opa);

        return response;
    }
    
    public JSONObject getAll() {
        Order o = null;
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
            String sql = "SELECT * FROM `missa`.`orders`";
            
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
                int id = rs.getInt("id");
                String member_name = rs.getString("member_name");
                String room_name = rs.getString("room_name");
                String coupon_name = rs.getString("coupon_name");
                int price = rs.getInt("price");
                System.out.print(price);
                String status = rs.getString("status");
               
                Date check_in = (Date)rs.getTimestamp("check_in");
                
                Date check_out =(Date)rs.getTimestamp("check_out");
                
                
                Timestamp create = rs.getTimestamp("create");
                int manager_id = rs.getInt("manager_id");
                
                /** 將每一筆商品資料產生一名新Product物件 */
                o = new Order(id,member_name,room_name,coupon_name,price,status,check_in,check_out,create,manager_id);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                System.out.print(o.getOrderAllInfo());
                jsa.put(o.getOrderAllInfo());
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
    
    public JSONObject getById(String id) {
        JSONObject data = new JSONObject();
        Order o = null;
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
            String sql = "SELECT * FROM `missa`.`orders` WHERE `orders`.`id` = ?";
            
            /** 將參數回填至SQL指令當中，若無則不用只需要執行 prepareStatement */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
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
                
                String member_name = rs.getString("member_name");
                String room_name = rs.getString("room_name");
                String coupon_name = rs.getString("coupon_name");
                int price = rs.getInt("price");
                String status = rs.getString("status");
                Date check_in = (Date)rs.getTimestamp("check_in");
                Date check_out =(Date)rs.getTimestamp("check_out");
                Timestamp create = rs.getTimestamp("create_time");
                int managerId = rs.getInt("manager_id");
                
                /** 將每一筆商品資料產生一名新Product物件 */
                o = new Order(Integer.parseInt(id),member_name,room_name,coupon_name,price,status,check_in,check_out,create,managerId);
                /** 取出該項商品之資料並封裝至 JSONsonArray 內 */
                data = o.getOrderAllInfo();
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
        response.put("data", data);

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
            String sql = "DELETE FROM `missa`.`orders` WHERE `id` = ? LIMIT 1";
            
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
    
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
