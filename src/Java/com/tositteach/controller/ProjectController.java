package com.tositteach.controller;

import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Project;
import com.tositteach.domain.entity.User;
import com.tositteach.service.EngineerService;
import com.tositteach.service.ProjectService;
import com.tositteach.util.DateChecker;
import com.tositteach.util.PagingBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/pro")
public class ProjectController {
    @Resource
    private ProjectService projectService;

    //显示项目列表, state=0（待审批）|1（通过）|2（未通过）|3（全部）
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "s", required = false, defaultValue = "1") int state,
                            @RequestParam(value = "pn", required = false) String proName,
                            @RequestParam(value = "en", required = false) String engName,
                            @RequestParam(value = "ei", required = false) String engId,
                            @RequestParam(value = "hg", required = false, defaultValue = "2") int hasGroup,
                            @RequestParam(value = "st", required = false, defaultValue = "0") int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10") int nm) {
        if (state < 0 || 3 < state) state = 1;
        if (hasGroup < 0 || 2 < hasGroup) hasGroup = 2;
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return projectService.query(state, proName, engName, engId, hasGroup, st, nm);
    }

    //获取项目详情
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Project get(@RequestParam(value = "pi") String proId) {
        Project pro = projectService.get(proId);
        return pro != null ? pro : new Project();
    }

    //添加新项目，返回项目id
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(@RequestBody ProReqBody pro, HttpSession session) {
        if (pro.pn == null || pro.stt == null || pro.edt == null || pro.dp == null) return "";
        if (!DateChecker.valiDuration(pro.stt,pro.edt)) return "";
        String re = projectService.add(pro.pn, pro.stt, pro.edt, pro.dp,
                ((User) session.getAttribute("user")).getUserId());
        return re != null ? re : "";
    }

    //删除项目
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody ProReqBody pro) { //proId
        if (pro.pi == null || pro.pi.length() != 11) return 0;
        return projectService.del(pro.pi);
    }

    //审批项目
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public int check(@RequestBody ProReqBody pro) { //pro_id,state
        if (pro.pi == null || pro.pi.length() != 11 || pro.s < 1 || 2 < pro.s) return 0;
        return projectService.check(pro.pi, pro.s);
    }

    //修改项目信息
    @RequestMapping(value = "/mod", method = RequestMethod.POST)
    @ResponseBody
    public int mod(@RequestBody ProReqBody pro) {
        if (pro.pi ==null || pro.pn == null || pro.edt == null || pro.dp == null) return 0;
        if (pro.pi.length()!=11)return 0;//pro.edt
        return projectService.mod(pro.pi, pro.pn, pro.edt, pro.dp);
    }

}

class ProReqBody {
    String pi;
    String pn;
    String stt;
    String edt;
    String dp;
    Byte s;

    public void setPi(String pi) {
        this.pi = pi;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public void setS(Byte s) {
        this.s = s;
    }
}
