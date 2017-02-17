/*
* Copyright (c) 2016 Sohu TV. All rights reserved.
*/
package com.sohu.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sohu.dto.JsonDto;
import com.sohu.dto.SeckillExposer;
import com.sohu.dto.SeckillResult;
import com.sohu.dto.SeckillStateEnum;
import com.sohu.exception.RepeatSeckillException;
import com.sohu.exception.SeckillCloseException;
import com.sohu.model.Seckill;
import com.sohu.service.SeckillService;

/**
 * <P>
 * Description:
 * </p>
 * @author wenbozhang
 * @version 1.0
 * @Date 2016年1月28日上午10:52:09
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    SeckillService seckillService;
    private final Logger logger= LoggerFactory.getLogger(SeckillController.class);

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public String getSckillList(Model model){
        List<Seckill> secklist=seckillService.listSeckill();
        model.addAttribute("secks", secklist);
        return "secklist";
    }

    @RequestMapping(value="/{seckillId}/detail",method=RequestMethod.GET)
    public String getSckillDetail(@PathVariable("seckillId")long id,Model model){
        Seckill seckill=seckillService.querySeckById(id);
        model.addAttribute("seckill", seckill);
        return "detail";
    }
    @RequestMapping(value="/{seckillId}/exposeUrl")
    @ResponseBody//既然是暴露接口，这个接口是由人去触发的，还是前台自动触发.返回的是秒杀商品列表
    public JsonDto<SeckillExposer> SckillExpose(@PathVariable("seckillId") long seckillId){
        SeckillExposer seckillpose=seckillService.exportSeckillUrl(seckillId);
        JsonDto<SeckillExposer> JsonDto=new JsonDto<SeckillExposer>(true,seckillpose);
        return JsonDto;
    }
    //执行秒杀,应该传入商品id和md5密码
    @RequestMapping(value="/{seckillId}/{md5}/execute")
    @ResponseBody
    public JsonDto<SeckillResult> SckillResult(@PathVariable("seckillId")long seckillId,@PathVariable("md5") String md5,@CookieValue(value="phone",required = false) String phone){
            if(phone==null){
                return new  JsonDto<SeckillResult>(false,SeckillStateEnum.NOLOGIN.getStateinfo());
            }
            try{
                SeckillResult seckillResult=seckillService.executeSeckill(seckillId, md5, phone);
                return new  JsonDto<SeckillResult>(true,seckillResult);
            }catch(SeckillCloseException e){
                logger.error("SeckillCloseException={}"+e.getMessage());
                return new  JsonDto<SeckillResult>(false,e.getMessage());
            }catch(RepeatSeckillException e){
                logger.error("RepeatSeckillException={}"+e.getMessage());
                return new  JsonDto<SeckillResult>(false,e.getMessage());
            }catch(Exception e){
                logger.error("Exception={}"+e.getMessage());
                return new  JsonDto<SeckillResult>(false,e.getMessage());
            }
    }
    @RequestMapping(value="/time/now",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JsonDto<Date> getTime(){
        return new JsonDto<Date>(true,new Date());
    }
    //执行秒杀,应该传入商品id和md5密码，去除Java客户端的事务延迟，采用mysql的存储过程
    @RequestMapping(value="/{seckillId}/{md5}/executePromote")
    @ResponseBody
    public JsonDto<SeckillResult> SckillResultPromote(@PathVariable("seckillId")long seckillId,@PathVariable("md5") String md5,@CookieValue(value="phone",required = false) String phone){
            if(phone==null){
            	phone = "13355555555";
                //return new  JsonDto<SeckillResult>(false,SeckillStateEnum.NOLOGIN.getStateinfo());
            }
            int result=seckillService.executeSeckillPromote(seckillId, md5, phone);
            if(result==1){
                SeckillResult seckillResult=new SeckillResult(seckillId, SeckillStateEnum.SUCCESS);
                return new  JsonDto<SeckillResult>(true,seckillResult);
            }else if(result==0){//秒杀还没开始
                return new  JsonDto<SeckillResult>(false,SeckillStateEnum.NOBEGIN.getStateinfo());
            }else if(result==2){//重复秒杀
                return new  JsonDto<SeckillResult>(false,SeckillStateEnum.REPEATSECKILL.getStateinfo());
            }else{//系统超时
                return new  JsonDto<SeckillResult>(false,SeckillStateEnum.NORESULT.getStateinfo());
             }
    }
}
