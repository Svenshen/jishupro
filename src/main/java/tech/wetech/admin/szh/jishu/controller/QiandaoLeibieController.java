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
import tech.wetech.admin.modules.system.service.OrganizationService;
import tech.wetech.admin.szh.jishu.domain.QiandaoLeibie;
import tech.wetech.admin.szh.jishu.service.QiandaoLeibieService;

/**
 *
 * @author  szh
 * QQ:873689
 * @date 2019-7-5 15:37:33
 */
@Controller
@RequestMapping("/qiandaoleibie")
public class QiandaoLeibieController {
    
    @Autowired
    QiandaoLeibieService qiandaoLeibieService;
   
    
    @GetMapping
    @RequiresPermissions("qiandaoleibie:view")
    public String page(){
        return "jishu/qiandaoleibie";
    }
    
    @ResponseBody
    @PostMapping("/create")
    @RequiresPermissions("qiandaoleibie:create")
    @SystemLog("签到类别新增")
    public Result create(QiandaoLeibie qiandaoLeibie) throws Exception  {
        qiandaoLeibieService.saveAndupdate(qiandaoLeibie);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete")
    @RequiresPermissions("qiandaoleibie:delete")
    @SystemLog("签到类别删除")
    public Result delete(@NotNull @RequestParam("kehudaima") Long id)   {
        qiandaoLeibieService.deleteById(id);
        return Result.success();
    }
    
    @ResponseBody
    @PostMapping("/delete-batch")
    @RequiresPermissions("qiandaoleibie:delete")
    @SystemLog("签到类别删除")
    public Result delete(@NotNull @RequestParam("id")  Long[] ids)   {
        for(Long id : ids){
            qiandaoLeibieService.deleteById(id);
        }        
        return Result.success();
    }
    
    
    @ResponseBody
    @PostMapping("/update")
    @RequiresPermissions("qiandaoleibie:update")
    @SystemLog("签到类别修改")
    public Result update(QiandaoLeibie qiandaoLeibie)  {
        qiandaoLeibieService.saveAndupdate(qiandaoLeibie);
        return Result.success();
    }
    
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("qiandaoleibie:view")
    public Result<List<QiandaoLeibie>> queryList(@RequestParam("pageSize") int pasgesize,@RequestParam("pageNumber") int pagenumber) {
        Pageable pageable = PageRequest.of(pagenumber-1, pasgesize);
        Page<QiandaoLeibie> page =  qiandaoLeibieService.queryAll(pageable);
        
        return Result.success(page.getContent()).addExtra("total", page.getTotalElements());
        
    }

}
