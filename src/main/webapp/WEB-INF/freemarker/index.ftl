<#assign ctx = request.contextPath>
<#include "/macro/layout.ftl">
<@html>
	<@page_head />
	
	<@page_body>
		<div id="demo" class="carousel slide" data-ride="carousel">
		 
		  <!-- 指示符 -->
		  <ul class="carousel-indicators">
		    <li data-target="#demo" data-slide-to="0" class="active"></li>
		    <li data-target="#demo" data-slide-to="1"></li>
		    <li data-target="#demo" data-slide-to="2"></li>
		    <li data-target="#demo" data-slide-to="3"></li>
		  </ul>
		 
		  <!-- 轮播图片 -->
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="${ctx}/resources/icon/timg.jpg" class="w-100">
		    </div>
		    <div class="carousel-item">
		      <img src="${ctx}/resources/icon/2019-04-27_fo2bC_icon.jpg" class="w-100">
		    </div>
		    <div class="carousel-item">
		      <img src="${ctx}/resources/icon/2019-04-27_RLlKc_icon.jpg" class="w-100">
		    </div>
		    <div class="carousel-item">
		      <img src="${ctx}/resources/icon/2019-04-27_Z2myt_icon.jpg" class="w-100">
		    </div>
		  </div>
		 
		  <!-- 左右切换按钮 -->
		  <a class="carousel-control-prev" href="#demo" data-slide="prev">
		    <span class="carousel-control-prev-icon"></span>
		  </a>
		  <a class="carousel-control-next" href="#demo" data-slide="next">
		    <span class="carousel-control-next-icon"></span>
		  </a>
		 
		</div>	
	
	</@page_body>
	
	<@page_tail />
	
	<@js_include />
</@html>

