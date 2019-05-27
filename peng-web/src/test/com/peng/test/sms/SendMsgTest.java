package com.peng.test.sms;

import net.sf.json.JSONObject;
import org.junit.Test;

/**
 * 发送短信测试
 * @Auther: daipeng
 * @Date: 2019/5/20 11:50
 * @Description:
 */
public class SendMsgTest {


    @Test
    public void test(){

        //调用检测屏蔽词接口 检测短信内容是否合法
        //String msg = "戴鹏，恭喜你 中奖啦!!!";
        //int i = SmsUtil.checkBlack(msg);


        //发送短信
        String mobile = "17600299987";
        //平台短信模板id
        Integer tplId = 159633;
        //占位符赋值
        String tplValue="#code#=443213";

        //返回结果
        JSONObject object = SmsUtil.sendMsg(mobile,tplId,tplValue);

        System.out.println(object.toString());


    }
}
