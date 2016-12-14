package weixin.guanjia.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import weixin.util.DataDictionaryUtil.MaterialStatus;
import weixin.util.DataDictionaryUtil.MaterialType;
import weixin.util.DataDictionaryUtil.UrlType;

@Controller
@RequestMapping("/dataDict")
@SuppressWarnings("all")
public class DataDictController {

    @RequestMapping(params = "urlType")
    public void urlType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List urlType = new ArrayList<>();
        UrlType[] types = UrlType.values();
        for (UrlType t : types) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("code", t.getCode());
            m.put("name", t.getName());
            urlType.add(m);
        }
        String json = gson.toJson(urlType);

        out.write(json);
    }

    @RequestMapping(params = "materialType")
    public void materialType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List typeList = new ArrayList<>();
        MaterialType[] types = MaterialType.values();
        for (MaterialType t : types) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("code", t.getCode());
            m.put("name", t.getName());
            typeList.add(m);
        }
        String json = gson.toJson(typeList);

        out.write(json);
    }

    @RequestMapping(params = "status")
    public void status(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List typeList = new ArrayList<>();
        MaterialStatus[] types = MaterialStatus.values();
        for (MaterialStatus t : types) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("code", t.getCode());
            m.put("name", t.getName());
            typeList.add(m);
        }
        String json = gson.toJson(typeList);

        out.write(json);
    }

    @RequestMapping(params = "auditStatus")
    public void auditStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 调用后台的查询所有商户的信息的接口方法
        // 输出到页面
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        List typeList = new ArrayList<>();
            Map<String, String> m = new HashMap<String, String>();
            m.put("code", MaterialStatus.audit_fail.getCode());
            m.put("name", MaterialStatus.audit_fail.getName());
            typeList.add(m);
            m = new HashMap<String, String>();
            m.put("code", MaterialStatus.audit_pass.getCode());
            m.put("name", MaterialStatus.audit_pass.getName());
            typeList.add(m);
        String json = gson.toJson(typeList);

        out.write(json);
    }
}