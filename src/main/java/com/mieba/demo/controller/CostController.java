package com.mieba.demo.controller;


import com.mieba.demo.bean.Cost;
import com.mieba.demo.dao.CostDao;
import com.mieba.demo.service.intf.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CostController {

    @Autowired(required = false)
    private HttpServletRequest request;//TODO 有没有更好的办法

    //自动装载
    @Autowired
    CostDao costDao;

    @Autowired
    CostService costService;

    MultipartHttpServletRequest multipartHttpServletRequest;

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public List<Cost> findAll(@RequestParam(value = "id", defaultValue = "0") int id,MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        this.multipartHttpServletRequest=multipartHttpServletRequest;
        return doFind(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteById(@RequestParam(value = "id", defaultValue = "0") int id) throws IOException {
        String delete = doDelete(id);
        return delete;
    }


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public String updateById() {
        String update = doUpdate();
        return update;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public String insert(){
        String insert = doInsert();
        return insert;
    }

    private List<Cost> doFind(int id) {
        //接收参数
        String testData=request.getParameter("testData");
        System.out.println(testData);
        MultipartFile model = multipartHttpServletRequest.getFile("modelFile");

        //转换文件
        StringBuilder stringBuilder = new StringBuilder();
        try{
            InputStream inputStream = model.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()));
            String line = "";
            while((line= bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        String content = stringBuilder.toString();
        System.out.println(content);
        List<Cost> costList=new ArrayList<>();
        if (id > 0) {
            costList.add(costService.queryById(id));
        }else{
            costList = costService.queryAll();
        }
        return costList;
    }

    private String doDelete(int id) {
        if (costService.delete(id)) {
            return "成功";
        } else {
            return "失败";
        }
    }

    private String doUpdate() {
        int id = Integer.parseInt(request.getParameter("id"));
        if (id > 0) {
            Cost cost = new Cost();
            cost.setDate(request.getParameter("date"));
            cost.setKind(request.getParameter("kind"));
            cost.setNote(request.getParameter("note"));
            cost.setSum(request.getParameter("sum"));
            cost.setYear(request.getParameter("year"));
            cost.setId(id);
            if (costService.updateById(cost)) {
                return "成功";
            }
            return "失败";
        } else {
            return "失败";
        }
    }

    private String doInsert() {
        Cost cost = new Cost();
        cost.setDate(request.getParameter("date"));
        cost.setKind(request.getParameter("kind"));
        cost.setNote(request.getParameter("note"));
        cost.setSum(request.getParameter("sum"));
        cost.setYear(request.getParameter("year"));
        if (costService.insert(cost)) {
            return "成功";
        }
        return "失败";
    }

}

