package com.company.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.company.bean.MonitorAlarmRecordBean;
import jdk.nashorn.internal.parser.JSONParser;

import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Date;


public class UploadUtil {

    private final String USER_AGENT = "Mozilla/5.0";

    public void uploadAlarm(String uri) throws IOException {
        String url = StrUtil.format("http:/{}/webhook/monitor/alarm/record",uri) ;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setDoInput(Boolean.TRUE);
        //添加请求头
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setUseCaches(false);
        con.setRequestProperty("Content-type", "application/json");
        //必须设置false，否则会自动redirect到重定向后的地址
        con.setInstanceFollowRedirects(false);


        MonitorAlarmRecordBean monitorAlarmRecordBean = new MonitorAlarmRecordBean();
        monitorAlarmRecordBean.setAlarmTime(new Date());
        monitorAlarmRecordBean.setChannel("1");
        monitorAlarmRecordBean.setPhoto("2");
        monitorAlarmRecordBean.setEquipment("3");


        // 设置文件类型:
        //发送Post请求
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(JSONUtil.toJsonStr(monitorAlarmRecordBean).getBytes());
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);

        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //打印结果
        System.out.println(response.toString());
    }


    public void uploadAlarmAsync(String uri) {
        ThreadUtil.execAsync(() -> {
            try {
                this.uploadAlarm(uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
