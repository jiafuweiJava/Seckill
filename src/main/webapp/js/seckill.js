var seckill= {
    URL: {
        exposeUrl:function(seckillId){
            return "/seckill/"+seckillId+"/exposeUrl";
        },
        executeUrl:function(seckillId,md5){
           /* return "/seckill/"+seckillId+"/"+md5+"/execute";*/
        	return "/seckill/"+seckillId+"/"+md5+"/executePromote"
        },
        nowSysTimeUrl:function(){
            return "/seckill/time/now";
        }
    },
    inputCheck:function(seckillId,param){
        if(!param||param.length!=11||isNaN(param)){
            $('#phoneTxt').val("");
            $('#errorMsg').hide(function(){
                $('#errorMsg').html('输入手机格式不正确！').show();
            });
        }else{
            $('#phoneTxt').val("");
            $.cookie('phone',param,{expire:7,path:'/seckill'});
            $('#phoneModal').modal('toggle');
            seckill.loginCheck(seckillId);
        }
    },
    countdown:function(seckillId,startTime){
        var date=new Date(startTime+1000);
        $('#exposeMsg').countdown(date,function(event) {
            $(this).html(event.strftime('%D天 %H时 %M分 %S秒'));
        }).on('finish.countdown',function(){
            seckill.loginCheck(seckillId);
        });
    },
    executeSeckill:function(seckillId,md5){
    	 $("#part1").hide();
         $("#part2").show();
         $('#seckill-btn').text("开始秒杀").css({color:"#00CCFF","font-size":30});
         $('#seckill-btn').one('click',function(){
            $.post(seckill.URL.executeUrl(seckillId,md5),{},function(result){
                if(result["isSuccess"]){
                	$('#exposeRueult').html(result["data"]["stateInfo"]);
                }else{
                	$('#exposeRueult').html(result["error"]);
                }
            });
        });
    },
    loginCheck:function(seckillId,startTime,endTime){
        var phone=$.cookie('phone');
        if(phone){
            $.get(seckill.URL.nowSysTimeUrl(),{},function(result){
                if(result["isSuccess"]){
                 var nowTime = result["data"];
                    if(nowTime>=endTime){
                        //秒杀结束
                        $("#part1").hide();
                        $("#part2").show();
                        $('#seckill-btn').text("秒杀结束").css({color:"#00CCFF","font-size":30});
                    }else if(nowTime<startTime){
                        //倒计时处理
                        seckill.countdown(seckillId,startTime);
                    }else{
                        //暴露秒杀接口
                        $.post(seckill.URL.exposeUrl(seckillId),{},function(result){
                            var data=result["data"];
                            if(data["isExpose"]){
                                seckill.executeSeckill(seckillId,data["md5"]);
                            }
                        });
                    }
                }else{
                    alert("请求失败！");
                }
            });

        }else{
            var modalObj=$('#phoneModal');
            modalObj.modal({backdrop:'static',keyboard:false});
            modalObj.modal('show');
        }
    }
}