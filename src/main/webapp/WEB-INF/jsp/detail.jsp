<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="cluster_jsp/Base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品详情</title>
  </head>
  
  <body>
    <div class="container-fluid" style="margin-top: 150px;">
	<div class="row-fluid">
		<div class="span12">
			<h2 class="text-center">
				${seckill.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;商品秒杀活动
			</h2>
			<div class="hero-unit" style="text-align: center;height: 300px;" id="part1">
				<h1>
					秒杀倒计时	
				</h1>
				<div id="exposeMsg" style="font-size: 30px;margin-top:80px;color: #00CCFF">
				</div>
			</div>
			<div id="part2" class="hero-unit" style="text-align: center;height: 300px;display:none">
				<p style="margin-top: 100px;">
					<a class="btn btn-default btn-large" id="seckill-btn"href="javascript:void(0)"></a>
				</p>
				<div id="exposeRueult" style="font-size: 30px;margin-top:80px;color: #00CCFF">
				</div>
			</div>
		</div>
	</div>
</div>

 <div class="modal fade" id="phoneModal">
        <div class="modal-dialog" style="width:400px;" >
            <div class="modal-content" style="position: relative" >
                <div class="modal-body">
                    <div style="margin-top: 30px;">
                        <input id="phoneTxt" type="text" class="form-control" placeholder="输入手机号" style="margin-left: 5px;margin-bottom: 15px;">
                        <button style="margin-left: 100px;margin-bottom: 15px;" id="phoneBtn" type="button" class="btn btn-primary">
                            		提交
                        </button>
                    </div>
                    <div id="errorMsg"></div>
                </div>

            </div>
        </div>
    </div>

<script src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="http://cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="js/seckill.js"></script>
<script type="text/javascript">
 $(function(){
        seckill.loginCheck(${seckill.id},${seckill.startTime.time},${seckill.endTime.time});
        $('#phoneBtn').click(function(){
            //$.cookie('jiafuwei','jiafuwei',{expire:700,path:'/Seckill'});
            //var jiafuwei = $.cookie('jiafuwei');
            //console.info(jiafuwei);　
            seckill.inputCheck(${seckill.id},$('#phoneTxt').val());
        });
    });
</script>
</body>
</html>
