package com.company.util;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.company.bean.MonitorAlarmRecordBean;
import jdk.nashorn.internal.parser.JSONParser;

import java.net.HttpURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;


public class UploadUtil {

    private final String USER_AGENT = "Mozilla/5.0";

    public void uploadAlarm(String alarm, String name, String photo, String file,String base64) throws IOException {

        char cbuf[] = new char[10000];
        InputStreamReader inputs = new InputStreamReader(new FileInputStream(new File(System.getProperty("user.dir") + "/config.json")), StandardCharsets.UTF_8);
        int len = inputs.read(cbuf);
        String text = new String(cbuf, 0, len);
        //1.构造一个json对象
        JSONObject jsonObj = new JSONObject(text.substring(text.indexOf("{")));   //过滤读出的utf-8前三个标签字节,从{开始读取
        StaticLog.info("{}", jsonObj);



        MonitorAlarmRecordBean monitorAlarmRecordBean = new MonitorAlarmRecordBean();
        monitorAlarmRecordBean.setAlarmTime(new Date());
        monitorAlarmRecordBean.setChannel("1");
        monitorAlarmRecordBean.setPhoto(photo);
        monitorAlarmRecordBean.setFile(file);
        monitorAlarmRecordBean.setAlarmType(alarm);
        monitorAlarmRecordBean.setBase64(base64);
        monitorAlarmRecordBean.setEquipment(name.trim().split("\u0000")[0]);

        String url = StrUtil.format("http://{}/webhook/monitor/alarm/record", jsonObj.get("uploadIp"));
        HashMap<String, String> headers = new HashMap<>();//存放请求头，可以存放多个请求头
        headers.put("User-Agent", USER_AGENT);
        headers.put("Content-type", "application/json");
        String body = HttpUtil.createPost(url).addHeaders(headers).body(JSONUtil.toJsonStr(monitorAlarmRecordBean)).execute().body();


        //打印结果
        System.out.println(body);
    }


    public void uploadAlarmAsync(String alarm, String name, String photo, String file) {
        ThreadUtil.execAsync(() -> {
            try {
                this.uploadAlarm(alarm, name, photo, file,"");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
