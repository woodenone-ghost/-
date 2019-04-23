<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_head />
	<@page_body>
	
		<div class="row" style="margin-bottom: 15px;margin-top: 15px;"><!-- 详细信息展示 -->
			<div class="col-lg-2">
			</div>
			<div class="col-lg-3"><!-- 头像展示 -->
				<img src="${ctx}/resources/icon/${entity["icon"]}" class="img-thumbnail w-100">
			</div>
			<div class="col-lg-5"><!-- 价格、销量等信息展示 -->
				<h3><strong>${entity["name"]}</strong></h3>
				<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    				<h4>商品价格:&nbsp;</h4> <h4 style="color: red;">${entity["price"]}</h4>	
  				</div>
				<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    				<h6>商品类别:&nbsp;</h6> <h6>${entity["category"]}</h6>	
  				</div>
				<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    				<h6>生产厂商:&nbsp;</h6> <h6>${entity["businessName"]}</h6>	
  				</div>
				<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    				<h6>总销量:&nbsp;</h6> <h5 style="color: #e74c1de6;">${entity["salesVolume"]}</h6>	
  				</div>
				<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    				<h6>平均价格评价:&nbsp;</h6> <h5 style="color: #e74c1de6;">${entity["evaluationPrice"]}</h6>	
    				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    				<h6>平均服务评价:&nbsp;</h6> <h5 style="color: #e74c1de6;">${entity["evaluationService"]}</h6>	
  				</div>
  				<form id="addBill" action="${ctx}/bill/add"> 
  					<@editHidden fId="id" type="hidden"/>
					<div class="form-inline" style="margin-bottom: 15px;margin-top: 15px;">
  						<h5><strong style="position: relative;top: 2px;">数量:</strong></h5>&nbsp;
						<input type="number" name="quantity" id="quantity" min="1" max="20" step="1" value="1"/>&nbsp;个
					</div>
					<button type="submit" class="btn btn-danger btn-lg">立 即 购 买</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="reset" class="btn btn-info btn-lg">重 置</button>
  				</form>				  								
			</div>
			<div class="col-lg-2">
			</div>			
		</div>
		
		<div class="row" style="margin-bottom: 15px;margin-top: 15px;"><!-- 特性图片展示 -->
			<div class="col-lg-2">
			</div>
			<div class="col-lg-8">
				<ul class="nav nav-tabs">
	  				<li class="nav-item">
	    				<a class="nav-link active" data-toggle="tab" href="#commodityDetail">商 品 详 情</a>
	  				</li>
	  				<li class="nav-item">
	    				<a class="nav-link" data-toggle="tab" href="#evaluation">卖 家 评 价</a>
				  	</li>
				</ul>
				
				<div class="tab-content">
    				<div id="commodityDetail" class="container tab-pane active"><br>
      					<img src="${ctx}/resources/characteristic/${entity["characteristic"]}" class="img-thumbnail w-100">
    				</div>
    				<div id="evaluation" class="container tab-pane fade"><br>
      					<h3>Menu 1</h3>
      					<p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    				</div>
  				</div>
			</div>
			<div class="col-lg-2">
			</div>
							
		</div>
	</@page_body>
	
	<@page_tail />
	
	<@js_include />
	
	<script>
	
	</script>
</@html>