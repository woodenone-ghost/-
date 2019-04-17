<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<div class="jumbotron jumbotron-fluid" style="padding:30px;margin-bottom: 0;">
		<div class="container" >
			<h3>增加商品</h3> 
		</div>
	</div>
	
	<@page_body>
		<@editForm id="addCommodity" action="${ctx}/commodity/add/submit" style="position: relative;right: 810px;top: 50px;">
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
				<label for="icon" class="w-25">商品头像:</label>
				<input type="file" name="icon" id="icon" accept="image/gif,image/jpeg,image/jpg,image/png" />
			</div>	
			<@add fId="name" colClass="col-lg-7" />
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="price" class="w-25">商品价格:</label>
  				<input type="text" class="form-control w-25" id="price" name="price" />&nbsp;
  				<select name="danwei" style="height:37px;">
 					<option selected="selected">每个</option>
 					<option>每次</option>
 					<option>每小时</option>
 					<option>每天</option>
 					<option>每周</option>
 					<option>每月</option>
				</select>
  			</div>  			
  			<div class="col-lg-7" style="margin-bottom: 15px;margin-top: 15px;">
  				<label for="category" class="w-25" style="padding-bottom: 5px;">商品类别:</label>
  				<div class="col-lg-12 form-inline" >
  					<label class="w-25">助洁:</label>
  					<input type="radio" name="category" checked="checked" value="购物" id="category">&nbsp;购 物
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<input type="radio" name="category" value="category" id="打扫" style="position: relative;left: 41px;">
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;打 扫
  				</div>
  				<div class="col-lg-12 form-inline" >
  					<label class="w-25">助餐:</label>
  					<input type="radio" name="category" value="上门助餐" id="category">&nbsp;上 门 助 餐
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<input type="radio" name="category" value="老年食堂" id="category">&nbsp;老 年 食 堂
  				</div>
  				<div class="col-lg-12 form-inline" >
  					<label class="w-25">助医:</label>
  					<input type="radio" name="category" value="护理保健" id="category">&nbsp;护 理 保 健
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<input type="radio" name="category" value="上门诊治" id="category">&nbsp;上 门 诊 治
  				</div>
  				<div class="col-lg-12 form-inline" >
  					<label class="w-25">康乐服务:</label>
  					<input type="radio" name="category" value="聊天解闷" id="category">&nbsp;聊 天 解 闷
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<input type="radio" name="category" value="文化娱乐" id="category">&nbsp;文 化 娱 乐
  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  					<input type="radio" name="category" value="法律帮助" id="category">&nbsp;法 律 帮 助
  				</div>  				
			</div>
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
				<label for="characteristic" class="w-25">商品详情:</label>
				<input type="file" name="characteristic" id="characteristic" accept="image/gif,image/jpeg,image/jpg,image/png" />
			</div>
		</@editForm>
	</@page_body>
	
	<@js_include />
</@html>