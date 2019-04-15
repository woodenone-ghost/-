<#assign ctx = request.contextPath>
<#include "/macro/layout.ftl">
<@html>
	<@page_head />
	
	<@page_body>
		<#assign userInformation=Session.SESSION_KEY_USER />
		<#if userInformation.identity=="buyer">
		
		<#elseif userInformation.identity=="seller">
			<div class="row" style="height:830px;">		
	  			<div class="col-lg-2" style="padding-right:0;"> <!-- 左侧导航栏 -->
					<nav class="navbar bg-dark navbar-dark h-100">
	  					<ul class="navbar-nav" style="position: relative;left: 30px;bottom: 300px;">
	    					<li class="nav-item">			
	      						<a class="nav-link " href="${ctx}/commodity/">增 加 商 品</a>
	    					</li>
	    					<li class="nav-item">
	      						<a class="nav-link" href="${ctx}/seller/manage">查 询 商 品</a>
	    					</li>
	    					<li class="nav-item">
	      						<a class="nav-link" href="#">查 询 账 单</a>
	    					</li>
	    					<li class="nav-item">
	      						<a class="nav-link" href="#">图 表 查 看</a>
	    					</li>    					
	  					</ul>
					</nav>
				</div> 
				
				<div class="col-lg-10" style="padding-left:0;"> <!-- 网页具体内容 -->
					<div class="jumbotron jumbotron-fluid" style="padding:30px;margin-bottom: 0;">
			  			<div class="container" >
			      			<h3>今日账单</h3> 
			  			</div>
					</div>
					
					<!-- 今日账单表格展示 -->
					<div>
						<table
					  id="QryTable"
	  			      data-toggle="table"
	  			      data-pagination="true"
	  			      data-side-pagination="server"
	  			      data-query-params-type=''
	  			      data-single-select="true"
	  				  data-click-to-select="true"
	  				  data-url="${ctx}/bill/todayBills"
	  				  data-response-handler="responseHandler">
	  			      <thead>
					  	<tr>
					  	  <th data-field="select" data-checkbox="true"></th>
					      <th data-field="id">${entityConf.fields["id"].fName}</th>
					      <th data-field="accountBuyer">${entityConf.fields["accountBuyer"].fName}</th>
					      <th data-field="idCommodity">${entityConf.fields["idCommodity"].fName}</th>
					      <th data-field="quantity">${entityConf.fields["quantity"].fName}</th>
					      <th data-field="price">${entityConf.fields["price"].fName}</th>
					      <th data-field="state">${entityConf.fields["state"].fName}</th>
					    </tr>
					  </thead>			  
					</table>
					</div>
				</div>	
			 		
			</div>	
		</#if>
	</@page_body>
	
	<@page_tail />
	
	<@js_include />
	
	<script>
		<#if userInformation.identity=="buyer">
		
		<#elseif userInformation.identity=="seller">	
			function responseHandler(res) {
    			return res.data.pager
  			}
		</#if>
	</script>
</@html>

