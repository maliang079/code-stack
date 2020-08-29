package com.test.bigdata.hive;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang3.SystemUtils;

public class Hive {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement smt = null;
        ResultSet rs = null;
        FileOutputStream fos = null;
        FileChannel fc = null;
        try {
            fos = new FileOutputStream(new File("E:\\WorkSpace\\GitWorkSpace\\code-stack\\test\\data\\hive\\stu.txt"));
            fc = fos.getChannel();

            conn = DriverManager.getConnection("jdbc:hive2://node-three:10000", "mal", "");
            smt = conn.createStatement();
            smt.execute("use testdb");
            rs = smt.executeQuery("desc stu");
            while (rs.next()) {
                String line = rs.getString(1) + "\t" + rs.getString(2);
                fc.write(ByteBuffer.wrap(line.getBytes()));
                fc.write(ByteBuffer.wrap(SystemUtils.LINE_SEPARATOR.getBytes()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (smt != null) smt.close();
            if (conn != null) conn.close();
            if (fc != null) fc.close();
            if (fos != null) fos.close();
        }
    }

}
