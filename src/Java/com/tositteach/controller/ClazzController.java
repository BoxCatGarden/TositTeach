package com.tositteach.controller;

import com.tositteach.domain.entity.Clazz;
import com.tositteach.domain.entity.Engineer;
import com.tositteach.domain.entity.Student;
import com.tositteach.service.*;
import com.tositteach.util.PagingBody;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/cla")
public class ClazzController {
    @Resource
    private ClazzService clazzService;

    //获取班级列表，分页
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public PagingBody query(@RequestParam(value = "st", required = false, defaultValue = "0")int st,
                            @RequestParam(value = "nm", required = false, defaultValue = "10")int nm) {
        if (st < 0) st = 0;
        if (nm < 0) nm = 10;
        return clazzService.query(st, nm);
    }

    //添加班级
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int add(@RequestBody ClaReqBody cla) {
        return clazzService.add(cla.cn, cla.rm, cla.ui);
    }

    //根据选中班级的cla_id删除班级信息
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public int del(@RequestBody List<String> ids) {
        if (ids.size() < 1) return 0;
        return clazzService.del(ids);
    }

    //将学生加入班级，[cla_id,stu_id[,stu_id[,..]]]
    @RequestMapping(value = "/addstu", method = RequestMethod.POST)
    @ResponseBody
    public int addstu(@RequestBody List<String> ids) {
        if (ids.size() < 2) return 0;
        String claId = ids.get(0);
        List<String> stuIds = ids.subList(1, ids.size());
        return clazzService.addstu(claId, stuIds);
    }

}

class ClaReqBody {
    String cn;
    String rm;
    String ui;

    public void setCn(String cn) {
        this.cn = cn;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }
}
