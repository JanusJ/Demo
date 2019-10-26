package com.chinasoft.demo;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unit.Base64Util;
import unit.File1Util;
import unit.GsonUtils;
import unit.HttpUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
/*@RequestMapping("/user")*/
public class FaceController {
	
	
	@RequestMapping("/faceregister")
	@ResponseBody
    public  String add(String base) {
    	// 娉ㄦ剰杩欓噷浠呬负浜嗙畝鍖栫紪鐮佹瘡涓�娆¤姹傞兘鍘昏幏鍙朼ccess_token锛岀嚎涓婄幆澧僡ccess_token鏈夎繃鏈熸椂闂达紝 瀹㈡埛绔彲鑷缂撳瓨锛岃繃鏈熷悗閲嶆柊鑾峰彇銆�
        String accessToken = "24.b319a90419cf86ade2a4e0a022b41400.2592000.1573871925.282335-17359445";
        // 璇锋眰url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
    		String image1 = base;
            Map<String, Object> map = new HashMap<>();
            map.put("image",image1);
            map.put("group_id", "user");      //  匹配百度里面的组，每个人群都得放在组里
            map.put("user_id", "usr");   //这个ID可以用参数传进来，作为用户名
            map.put("user_info", "abc");
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject fromObject = JSONObject.fromObject(result);
            System.out.println("fromObject :"+fromObject);
//          获取错误代码
          String facefuss = fromObject.getString("error_code"); 
          System.out.println(facefuss);
          if(facefuss.equals("0")){
          	 return "1";
          }
            System.out.println(result);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
	@RequestMapping("/facelogin")
	@ResponseBody
    public String facematch(String base,HttpServletResponse response) {
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {
            //这里可以写mapper方法也就是查询base编码，可以给方法设置返回base编码，将名字命名为base1
            //到时候这要替换
            byte[] bytes1 = File1Util.readFileByBytes("D:\\one.JPG");
            //一   这块如果连接数据，要填写数据库查询到的base64编码，这个编码很，数据库里面类型要注意，不要用text这个是用来放字符的，
            String image1 = Base64Util.encode(bytes1);  //二
            //前台传来的人脸
          String image2 = base;
           //数据库查询人脸
          /*String image1 = base1;*/   //三
            List<Map<String, Object>> images = new ArrayList<>();
            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
            map1.put("face_type", "LIVE");
            map1.put("quality_control", "LOW");
            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
            map2.put("face_type", "LIVE");
            map2.put("quality_control", "LOW");
            map2.put("liveness_control", "NORMAL");
            images.add(map1);
            images.add(map2);
            String param = GsonUtils.toJson(images);
            String accessToken = "24.b319a90419cf86ade2a4e0a022b41400.2592000.1573871925.282335-17359445";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject fromObject = JSONObject.fromObject(result);
            System.out.println("facematch--fromObject :"+fromObject);
//            获取错误代码
            String facefuss = fromObject.getString("error_code"); 
            System.out.println(facefuss);
            if(facefuss.equals("223114")){
            	 return "223114";
            }else if(facefuss.equals("0")){
                //获取分数
                JSONObject jsonObject = fromObject.getJSONObject("result");
                String string = jsonObject.getString("score");             
                double score = Double.parseDouble(string);
                if(score>=1){
             	   return "1";   //登陆成功
                }else{
             	   return "2";   //登陆失败
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        	return "fail"; 
        }
		return "fail";
    }
	
	
	
}	
    
