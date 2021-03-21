package com.example.datebasebigwork.Controller;

import com.example.datebasebigwork.Entity.Users;
import com.example.datebasebigwork.Mapper.BaiDuOCR;
import com.example.datebasebigwork.Repository.UsersRepository;
import com.example.datebasebigwork.Service.service;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/first")
public class testController {
    @Resource
    private service service0;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpSession session;

    private final String filePath="http://localhost:63342/datebasebigwork/web_bigwork\\用户头像\\";
    private final String localFilePath = "D:\\JavaEclipse\\datebasebigwork\\src\\main\\java\\web_bigwork\\用户头像\\";

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String register(){
        response.addHeader("Access-Control-Allow-Origin", "*");   //用于ajax post跨域（*，最好指定确定的http等协议+ip+端口号）
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String username=request.getParameter("username");

        String email=request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        System.out.println(username+email+phone+password);
        Users user = service0.findByusername(username);
        if(user == null){
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPhoneNumber(phone);
            newUser.setUserPassword(password);

            service0.save(newUser);
            return "注册成功！";
        }else{
            return "用户名已存在！";
        }

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpSession session,HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String username=request.getParameter("username");
        String password = request.getParameter("password");
        Users user = service0.findByusername(username);
        if (user==null){
            return "该用户不存在！";
        }
        else {
            if (!user.getUserPassword().equals(password)){
                return "密码错误！";
            }
            else {
                session.setAttribute("username",username);
                System.out.println(session.getAttribute("username"));
                System.out.println(session.getId());
                return "登录成功！";
            }
        }

    }

    @RequestMapping(value = "/checkSelf",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkSelf(HttpSession session,HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println(session.getId());
        String username = (String) session.getAttribute("username");

        System.out.println(username);
        Map<String,Object> result= new HashMap<String,Object>();
        Users your = service0.findByusername(username);
        System.out.println(your.getUserPassword());

        result.put("username",your.getUsername());
        result.put("address",your.getAddress());
        result.put("email",your.getEmail());
        result.put("phoneNumber",your.getPhoneNumber());
        result.put("truename",your.getTruename());
        result.put("introduce",your.getUserIntroduction());
        if (your.getUserPic()!=null){
            result.put("userPic",filePath+your.getUserPic());
        }
        else {
            result.put("userPic",null);
        }
        return result;
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateInfo(@RequestParam("pic_file") MultipartFile pic){

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String username = (String) session.getAttribute("username");
        File picFile = new File(localFilePath+username+".png");
        Map<String,Object> result= new HashMap<>();
        if (pic!=null){
            picFile.mkdirs();
            try {
                pic.transferTo(picFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.put("userPic",filePath+username+".png");
        }

        Users user = service0.findByusername(username);
        String address = request.getParameter("Address");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("PhoneNumber");
        String truename = request.getParameter("Truename");
        String introduce = request.getParameter("Introduce");
        String password = request.getParameter("Password");
        System.out.println(password);
        service0.updateInfoByUsername(username,password,username+".png",address,email,phoneNumber,truename,introduce);

        result.put("username",username);
        result.put("address",address);
        result.put("email",email);
        result.put("phoneNumber",phoneNumber);
        result.put("truename",truename);
        result.put("introduce",introduce);

        return result;
    }

    @RequestMapping(value = "/session",method = RequestMethod.GET)
    @ResponseBody
    public Object sendSession(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Object sendS = session.getAttribute("username");
        if (sendS==null){
            return 0;
        }
        return 1;
    }

    @RequestMapping(value = "/quit",method = RequestMethod.GET)
    @ResponseBody
    public String quit(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        session.removeAttribute("username");

        return "已经成功退出！";
    }

    @RequestMapping(value = "/pageNum",method = RequestMethod.GET)
    @ResponseBody
    public Integer pageNum(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        int size = 4;
        int numP=service0.numPage(size);
        return numP;
    }

    @RequestMapping(value = "/deleteSelf",method = RequestMethod.GET)
    @ResponseBody
    public String deleteSelf(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String susername = (String) session.getAttribute("username");
        System.out.println(susername);
        service0.deleteByusername(susername);

        return "已成功注销用户！";
    }

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findAllUsers(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        int page = Integer.parseInt(request.getParameter("page"));
        System.out.println("前端发送页码："+page);
        int size = 4;
        int numP=service0.numPage(size);
        Page<Users> Upage = service0.findAllUsers(size,page);
        int eP= Upage.getNumberOfElements();
        List<Users> LUsers = Upage.getContent();
        HashMap<String,Object> result = new HashMap<>();
        result.put("pageNum",numP);
        for (int i = 1;i<=eP;i++){
            String username = LUsers.get(i-1).getUsername();
            String truename = LUsers.get(i-1).getTruename();
            String phoneNumber = LUsers.get(i-1).getPhoneNumber();
            String address = LUsers.get(i-1).getAddress();
            String email = LUsers.get(i-1).getEmail();
            String introduce = LUsers.get(i-1).getUserIntroduction();
            String userPic = LUsers.get(i-1).getUserPic();

            result.put("username"+i,username);
            result.put("truename"+i,truename);
            result.put("phoneNumber"+i,phoneNumber);
            result.put("address"+i,address);
            result.put("email"+i,email);
            result.put("introduce"+i,"\""+introduce+"\"");
            if (userPic!=null){
                result.put("userPic"+i,filePath+userPic);
            }
            else
                result.put("userPic"+i,userPic);

        }

        return result;
    }

    //搜索
    @RequestMapping(value = "/findAll/search",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> search(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String keyword = request.getParameter("keyword");
        System.out.println("关键字："+keyword);
        int page = Integer.parseInt(request.getParameter("page"));
        List<Users> usersList = service0.searchUser(keyword);
        HashMap<String,Object> result = new HashMap<>();
        int pageNum = usersList.size()/4+1;

        int theLastPN = usersList.size()%4;
        result.put("pageNum",pageNum);

        System.out.println("开始搜索");
        if (page<pageNum)
            for (int i = 1;i<=4;i++){
                String username = usersList.get((i-1)+(page-1)*4).getUsername();
                String truename = usersList.get((i-1)+(page-1)*4).getTruename();
                String phoneNumber = usersList.get((i-1)+(page-1)*4).getPhoneNumber();
                String address = usersList.get((i-1)+(page-1)*4).getAddress();
                String email = usersList.get((i-1)+(page-1)*4).getEmail();
                String introduce = usersList.get((i-1)+(page-1)*4).getUserIntroduction();
                String userPic = usersList.get((i-1)+(page-1)*4).getUserPic();

                result.put("username"+i,username);
                result.put("truename"+i,truename);
                result.put("phoneNumber"+i,phoneNumber);
                result.put("address"+i,address);
                result.put("email"+i,email);
                result.put("introduce"+i,"\""+introduce+"\"");
                if (userPic!=null){
                    result.put("userPic"+i,filePath+userPic);
                }
                else
                    result.put("userPic"+i,userPic);

            }
        else{
            for (int i = 1;i<=theLastPN;i++){
                String username = usersList.get(usersList.size()-i).getUsername();
                String truename = usersList.get(usersList.size()-i).getTruename();
                String phoneNumber = usersList.get(usersList.size()-i).getPhoneNumber();
                String address = usersList.get(usersList.size()-i).getAddress();
                String email = usersList.get(usersList.size()-i).getEmail();
                String introduce = usersList.get(usersList.size()-i).getUserIntroduction();
                String userPic = usersList.get(usersList.size()-i).getUserPic();

                result.put("username"+i,username);
                result.put("truename"+i,truename);
                result.put("phoneNumber"+i,phoneNumber);
                result.put("address"+i,address);
                result.put("email"+i,email);
                result.put("introduce"+i,"\""+introduce+"\"");
                if (userPic!=null){
                    result.put("userPic"+i,filePath+userPic);
                }
                else
                    result.put("userPic"+i,userPic);

            }
        }


        return result;

    }

    @RequestMapping(value = "/searchPageNum",method = RequestMethod.GET)
    @ResponseBody
    public int searchPageNum(){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        String keyword = request.getParameter("keyword");
        System.out.println("关键字："+keyword);
        List<Users> usersList = service0.searchUser(keyword);
        int pageNum = (usersList.size()-1)/4+1;
        return pageNum;
    }

    @RequestMapping(value = "/IdCard",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> IdCardOCR(@RequestParam("IdCardPic") MultipartFile picOcr){
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        HashMap<String,Object> result = new HashMap<>();
        byte[] data = null;
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String base64 = null;
        try {
            base64 = base64Encoder.encode(picOcr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        base64 = base64.replaceAll("\r\n", "");
        base64 = base64.replaceAll("\\+", "%2B");
        String httpUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard?access_token=" + BaiDuOCR.getAuth();
        //正面照
        String httpFront = "detect_direction=true&id_card_side=front&image=" + base64;
        String jsonResult = BaiDuOCR.request(httpUrl, httpFront);
        HashMap<String, String> map = BaiDuOCR.getHashMapByJson(jsonResult);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }

        return map;
    }

}
