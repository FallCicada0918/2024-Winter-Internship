package com.example.hotal.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.hotal.entity.Customer;
import com.example.hotal.service.HotelRoomService;
import com.example.hotal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class PayController {
    @Autowired
    private HotelRoomService hotelRoomService;
    @Autowired
    private OrderService orderService;

    //appid
    private final String APP_ID = "9021000139631600";
    //应用私钥
    private final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCxuX2+W+w+epaKV8uAQBvUkqplap15pUXJXi86KdCM8Wrk7U2sDBB7WwCK1Oh+AZD1NX2iPmcpp9IPJb2D+WttZRia6sj9KlBbMGDAYQw1aiaYQDnBnsgFuCbUwZNWWlKjnHq20Hb0kr+UwTXqb94XOVlpeV1roPspQOSk0R4Ao/82bPNp8skV7VFexmtp8Zm6UUtP4VYu8/c8+7UdLPaKwTjqUjDHn5KQ0f3eMwt2heo+XFVfvPcIlGAkXaCAYvDgeZRwV1JUjbjm1eIo8yxxKDQ5qjOoSp3eqLxXPgwBrs0+l5EQwa3L87um/WLdvPa0AX0es05H9puT6o0yGRwDAgMBAAECggEAPXXwTkxrvo+WT/FcptWEylaOJtRf8Rl+sMypeGvNe3BhjxmhqsXhTUeDQH+whyLzhlkhQWJjOryWoDZk+6bdx88WWzymlSZ085QfYWguWKqE/lH3p0XJ1nCAvULUOSCzQ3Fx3pbpDFtTdZX7lz9oho+GpDscW4QoV3Phnj3TKlT4mhspZx5SJe6HXTE2QezHkQ7wui9uuwaewERS5lw72yJv3/jt8cMVQeXychgxOnmWzITx6B3uV+4qQSqC1b5D3F6WpgBdLLoQi8s3og8lsrcWMiVpeharfJM6glXQ9PmaCRy+/nOxyhBVI/B3ruloM50VR0pxrt1lKUV/y0l4wQKBgQDlXATK+Y3fBgHL2hJBbgVJTUTfiJ8AYN3d+YtNnYKQwlqUir850Q32I/BVpC6Rvu4XIZbua9G6yhoD2za1aD56zoVnHEf2V8mchMK0leQanW2LyBjKoWJOodCsIlJKITjIFwY1qetHJF3HBQBbKFKi+jaxMu2u8dDhJ1HrJJT0ZwKBgQDGXh1MG119fMzrOckuA9c+G0lOTp839RAdV6VSUsPDxJ/9LGtRkUJ2r/xD7C9FL5BmhEkUZMjqILKKvXyJrfPq+IizYEUuHZOdztSEcdGgCc2InykuHZZJavGlg839k1ob5UE0DrPhH2JG0Lu/J1n7M/6Ql9PMacRFU/y+awc6BQKBgDCfCjERKB8gQd86EmamcR5x7+jHKJXG8yGsVfiVLGmNmfAlXZZfF+uXAMrW7DxfUk+7Aij22FYVo+AvZ3CbcatM/oC2QRca0xWIG1J2+A0gVGmueHr0LUfoC7mB6Rnn4wafwuQ7ajIBppObHtE0QhhknJWB61sLzjD8yePkNGN/AoGBAIyB23SuKYGaZbf0PXkXhV3bjAgojoTfU+PE0jhGoZ3IZ5632Oh04MQbEa+22yDBeSH0b6M/ZETaljzJzS3khlKYapPMDBSHJKlx+Hw79sTKTMiKHGwqzuoAE1wuyTjlJ718nLvX8jfyt/T7IxFaOVVC1fzrXj17aWfF/s+OFhdVAoGAfCW/T8XB5b8LFfoIfjt+coO3Ch2EWb9wZQXjuLSgH9epk/6IZeS5amimn1ZEVFwvvyLJeL+FzmJpLotMc7vN1QMWaTxgCR2JwrPhk2UExCsfXwAf1P1VSv3LDuUpMygfwPSaNOXuqUdm696fL8rZEw02zaOUF1VI6GlsCfX79wU=";
    private final String CHARSET = "UTF-8";
    // 支付宝公钥
    private final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxzTAEThE84A6P3X2778aPattut8iaBrtDtossmepAdX56bj0evdMd5+18wSaEjt2okMyVq/xj3AvjO4Mzzz6godw8v/a33H/teUQLa0O4D7QogRqnHJ/LeTeyUkfxAQHOoG0qgZG3vuDFHNWgozhGj+IhEzG+lfu3OkG16GGz2vP4hxb8armwdQaw7/qig0jcehR1nPXyLDcs/JS+HiCTQwW/tFY1ddzL2sIN8FT8RKapPEx16B19G3/YTyk2m5YaAE3dSzFnNCJoUBvdZ00ADxgykpv1got9vegEXzaj2rGOavxwXxCiwsrVXmJ5iikXOzz/jAeEZgE+qI3ERiIfQIDAQAB";
    //这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    private final String GATEWAY_URL ="https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private final String FORMAT = "JSON";
    //签名方式
    private final String SIGN_TYPE = "RSA2";
    //支付宝异步通知路径,付款完毕后会异步调用本项目的方法,必须为公网地址
    private final String NOTIFY_URL = "http://127.0.0.1/notifyUrl";
    //支付宝同步通知路径,也就是当付款完毕后跳转本项目的页面,可以不是公网地址
    private final String RETURN_URL = "http://localhost:8080/returnUrl";
    @ResponseBody
    @RequestMapping("/pay/alipay")
    public String alipay(HttpSession session, Model model,
                         @RequestParam("roomPrice") float dona_money,
                         @RequestParam("roomId") int dona_id,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkInTime ,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkOutTime
                         ) throws AlipayApiException {
        //把dona_id项目id 放在session中
        session.setAttribute("dona_id",dona_id);
        session.setAttribute("checkInTime",checkInTime);
        session.setAttribute("checkOutTime",checkInTime);


        //生成订单号（支付宝的要求？）
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String user = UUID.randomUUID().toString().replace("-","").toUpperCase();

        String OrderNum = time+user;

        //调用封装好的方法（给支付宝接口发送请求）
        System.out.println(11);
        return sendRequestToAlipay(OrderNum,dona_money,"ghjk");
    }
    /*
参数1：订单号
参数2：订单金额
参数3：订单名称
 */
    //支付宝官方提供的接口
    private String sendRequestToAlipay(String outTradeNo,Float totalAmount,String subject) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL,APP_ID,APP_PRIVATE_KEY,FORMAT,CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(RETURN_URL);
        alipayRequest.setNotifyUrl(NOTIFY_URL);

        //商品描述（可空）
        String body="";
        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        return result;
    }
    @RequestMapping("/returnUrl")
    public String returnUrlMethod(HttpServletRequest request, HttpSession session, Model model , RedirectAttributes redirectAttributes) throws AlipayApiException, UnsupportedEncodingException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);//查看参数都有哪些
        //验证签名（支付宝公钥）
        boolean signVerified = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE); // 调用SDK验证签名
        //验证签名通过
        if(signVerified){
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易流水号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            float money = Float.parseFloat(new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8"));

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("付款金额="+money);

            //在这里编写自己的业务代码（对数据库的操作）
            Integer roomId=(Integer) session.getAttribute("dona_id");
            LocalDateTime checkInTime= (LocalDateTime)session.getAttribute("checkInTime");
            LocalDateTime checkOutTime= (LocalDateTime)session.getAttribute("checkOutTime");
            Customer customer=(Customer)session.getAttribute("user");
            orderService.bookRoom(customer.getCustomer_id(),roomId,checkInTime,checkOutTime);
            redirectAttributes.addFlashAttribute("successMessage", "预定成功！您的房间号为：" + hotelRoomService.getRoomNumber(roomId));
            //跳转到提示页面（成功或者失败的提示页面）
            model.addAttribute("flag",1);
            model.addAttribute("msg","支持");
            return "redirect:/rooms/";
        }else{
            //跳转到提示页面（成功或者失败的提示页面）
            model.addAttribute("flag",0);
            model.addAttribute("msg","支持");
            return "redirect:/rooms/";
        }
    }
}


