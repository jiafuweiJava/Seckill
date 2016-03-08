<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="cluster_jsp/Base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>秒杀列表</title>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid" style="margin-top: 150px;">
			<div class="span12">
				<h2 class="text-center">购物网站商品秒杀</h2>
				<table class="table table-hover" style="margin-top: 50px;">
					<thead>
						<tr>
							<th>商品ID</th>
							<th>名字</th>
							<th>数量</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>详情</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${secks}" var="seck" varStatus="status">
							<tr class=<c:if test="${status.index==0}">"success"</c:if>
								<c:if test="${status.index==1}">"error"</c:if>
								<c:if test="${status.index==2}">"warning"</c:if>
								<c:if test="${status.index==3}">"info"</c:if>>
								<td>${seck.id}</td>
								<td>${seck.name}</td>
								<td>${seck.num}</td>
								<td><fmt:formatDate value="${seck.startTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${seck.endTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<a class="btn btn-success btn-large" href="seckill/${seck.id}/detail">详情</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>
