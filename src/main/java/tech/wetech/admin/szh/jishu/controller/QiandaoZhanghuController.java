/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @autoor szh
 */

package tech.wetech.admin.szh.jishu.controller;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.wetech.admin.core.annotation.SystemLog;
import tech.wetech.admin.core.utils.Result;
import tech.wetech.admin.modules.system.po.Organization;
import tech.wetech.admin.szh.jishu.domain.QiandaoZhanghu;
import tech.wetech.admin.szh.jishu.service.QiandaoLeibieService;
import tech.wetech.admin.szh.jishu.service.QiandaoZhanghuService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 8:16:25
 */
@Controller
@RequestMapping("/qiandaozhanghao")
public class QiandaoZhanghuController {
    
    @Autowired
    QiandaoZhanghuService qiandaoZhanghuService;
    @Autowired
    QiandaoLeibieService qiandaoLeibieService;
    
    
    @ModelAttribute("leibieList")
    public List leibielist(){
        return qiandaoLeibieService.queryAll();
    }
    
    
    @GetMapping
    @RequiresPermissions("qiandaozhanghao:view")
    public String page(){
        return "jishu/qiandaozhanghao";
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("qiandaozhanghao:create")
    @SystemLog("签到账号新增")
    public Result create(QiandaoZhanghu qiandaoZhanghu) throws Exception  {
        qiandaoZhanghuService.saveAndupdate(qiandaoZhanghu);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("qiandaozhanghao:delete")
    @SystemLog("签到账号删除")
    public Result delete(@NotNull @RequestParam("id") Long id)   {
        qiandaoZhanghuService.deleteById(id);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete-batch")
    @RequiresPermissions("qiandaozhanghao:delete")
    @SystemLog("签到账号删除")
    public Result delete(@NotNull @RequestParam("id")  Long[] ids)   {
        for(Long id : ids){
            qiandaoZhanghuService.deleteById(id);
        }        
        return Result.success();
    }
    
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("qiandaozhanghao:update")
    @SystemLog("签到账号修改")
    public Result update(QiandaoZhanghu qiandaoZhanghu)  {
        qiandaoZhanghuService.saveAndupdate(qiandaoZhanghu);
        return Result.success();
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("qiandaozhanghao:view")
    public Result<List<QiandaoZhanghu>> queryList(@RequestParam("pageSize") int pasgesize,@RequestParam("pageNumber") int pagenumber) {
        Pageable pageable = PageRequest.of(pagenumber-1, pasgesize);
        Page<QiandaoZhanghu> page =  qiandaoZhanghuService.queryAll(pageable);
        
        return Result.success(page.getContent()).addExtra("total", page.getTotalElements());
    }

}
