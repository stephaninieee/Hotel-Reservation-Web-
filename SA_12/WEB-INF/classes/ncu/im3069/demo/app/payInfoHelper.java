package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import org.json.*;

import ncu.im3069.demo.util.DBMgr;

public class payInfoHelper {
	
	/**
     * 實例化（Instantiates）一個新的（new）payInfoHelper物件<br>
     * 採用Singleton不需要透過new
     */
    private payInfoHelper() {
        
    }
    
    /** 靜態變數，儲存payInfoHelper物件 */
    private static payInfoHelper mh;
    
    /** 儲存JDBC資料庫連線 */
    private Connection conn = null;
    
    /** 儲存JDBC預準備之SQL指令 */
    private PreparedStatement pres = null;
    
    /**
     * 靜態方法<br>
     * 實作Singleton（單例模式），僅允許建立一個payInfoHelper物件
     *
     * @return the helper 回傳payInfoHelper物件
     */
    public static payInfoHelper getHelper() {
        /** Singleton檢查是否已經有payInfoHelper物件，若無則new一個，若有則直接回傳 */
        if(mh == null) mh = new payInfoHelper();
        
        return mh;
    }
    public JSONObject create(payInfo p) {
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
            String sql = "INSERT INTO `missa`.`payinfo`(`cardnum`, `month`, `year`, `csv`)"
                    + " VALUES(?, ?, ?, ?)";
            
            /** 取得所需之參數 */
            String cardnum = p.getCardnum();
            int month = p.getmonth();
            int year = p.getyear();
            int csv = p.getcsv();
            
            /** 將參數回填至SQL指令當中 */
            pres = conn.prepareStatement(sql);
            pres.setString(1, cardnum);
            pres.setInt(2, month);
            pres.setInt(3, year);
            pres.setInt(4, csv);
           
            
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
}
