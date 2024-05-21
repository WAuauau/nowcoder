package com.nowcoder.community.controller;


import com.nowcoder.community.service.AlphaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller  //controller 负责调度，处理浏览器请求
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody // 不返回视图只返回数据
    public String sayHello ()
    {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData ()
    {
        return alphaService.find();
    }

    //处理请求和响应
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求数据
        System.out.println("request.getMethod()");
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        // 创建迭代器得到请求行所有的key，请求行为key-value结构，
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.printf(name + ": " + value);

        }
        System.out.println(request.getParameter("code"));

        // 返回响应数据
        response.setContentType("Text/html;charset=utf-8");
        try (
                PrintWriter writer = response.getWriter();
        ) {
            writer.write("<h1>牛客网</h1>");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // 更简便的处理请求和响应的方式
    // GET请求（默认请求）向服务器获取数据  http://localhost:8081/community/alpha/students?current=3&limit=33
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name="current",required=false,defaultValue = "1") int current,
            @RequestParam(name="limit",required=false,defaultValue = "10") int limit)
    {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    @RequestMapping(path="/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id)
    {
        System.out.println(id);
        return "a student";
    }
    // post请求 浏览器向服务器提交数据
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age)
    {
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    // 向浏览器响应动态html数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeachert()
    {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","zhangsan");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model)
    {
        model.addAttribute("name", "beijingdaxue");
        model.addAttribute("age","13");
        return "/demo/view";
    }

    // 响应json数据,一般在异步请求中
    // Java对象 -> JSON字符串 -> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getemp()
    {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","wx");
        emp.put("age","12");
        emp.put("salary","34324");
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getemps()
    {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","wx");
        emp.put("age","12");
        emp.put("salary","34324");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","ww");
        emp.put("age","123");
        emp.put("salary","3132324");
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","aw");
        emp.put("age","193");
        emp.put("salary","2324");
        list.add(emp);

        return list;
    }
}
