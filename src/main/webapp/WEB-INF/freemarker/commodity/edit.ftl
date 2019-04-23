<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="修改">
	<@editForm id="${entityAbbr}Edit" action="${basePath}/edit/submit">
		<@editHidden fId="id" type="hidden"/>
		<@detail fId="id" />
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    		<label for="icon" class="w-25">${entityConf.fields["icon"].fName}:</label>
    		<img src="${ctx}/resources/icon/${entity["icon"]}" class="img-thumbnail" style="width: 345px;">
    	</div>
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="icon" class="w-25">新商品头像:</label>
			<input type="file" name="icon" id="icon" accept="image/gif,image/jpeg,image/jpg,image/png" />
		</div>			
		<@edit fId="name" />
			<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="price" class="w-25">商品价格:</label>
  				<input type="text" class="form-control w-25" id="price" name="price" value="${entity["price"]}"/>&nbsp;
  				<select name="danwei" style="height:37px;">
					<#if danwei=="每个">
				    	<option selected = "selected" >元/每个</option>
				    <#else>
				    	<option>元/每个</option>
				    </#if>  				

					<#if danwei=="每次">
				    	<option selected = "selected" >元/每次</option>
				    <#else>
				    	<option>元/每次</option>
				    </#if>  

					<#if danwei=="每小时">
				    	<option selected = "selected" >元/每小时</option>
				    <#else>
				    	<option>元/每小时</option>
				    </#if>  

					<#if danwei=="每天">
				    	<option selected = "selected" >元/每天</option>
				    <#else>
				    	<option>元/每天</option>
				    </#if>  
				    
					<#if danwei=="每周">
				    	<option selected = "selected" >元/每周</option>
				    <#else>
				    	<option>元/每周</option>
				    </#if>  				    

					<#if danwei=="每月">
				    	<option selected = "selected" >元/每月</option>
				    <#else>
				    	<option>元/每月</option>
				    </#if>  
				</select>
  			</div> 
					<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    					<label for="category" class="w-25">${entityConf.fields["category"].fName}:</label>
				        <select class="form-control" id="category" name="category" style="width: 345px;">
				        	<option></option>
				        	<#if entity["category"]=="助洁-购物">
				        		<option selected = "selected" >助洁-购物</option>
				        	<#else>
				        		<option>助洁-购物</option>
				        	</#if>
				        	
				        	<#if entity["category"]=="助洁-打扫">
				        		<option selected = "selected" >助洁-打扫</option>
				        	<#else>
				        		<option>助洁-打扫</option>
				        	</#if>
				        	
				        	<#if entity["category"]=="助餐-上门助餐">
				        		<option selected = "selected" >助餐-上门助餐</option>
				        	<#else>
				        		<option>助餐-上门助餐</option>
				        	</#if>	
				        				        						        
					        <#if entity["category"]=="助餐-老年食堂">
				        		<option selected = "selected" >助餐-老年食堂</option>
				        	<#else>
				        		<option>助洁-购物</option>
				        	</#if>
				        	
					        <#if entity["category"]=="助医-护理保健">
				        		<option selected = "selected" >助医-护理保健</option>
				        	<#else>
				        		<option>助医-护理保健</option>
				        	</#if>
				        	
					        <#if entity["category"]=="助医-上门诊治">
				        		<option selected = "selected" >助医-上门诊治</option>
				        	<#else>
				        		<option>助医-上门诊治</option>
				        	</#if>				        					        	
				        	
					        <#if entity["category"]=="康乐服务-聊天解闷">
				        		<option selected = "selected" >康乐服务-聊天解闷</option>
				        	<#else>
				        		<option>康乐服务-聊天解闷</option>
				        	</#if>	
				        	
					        <#if entity["category"]=="康乐服务-文化娱乐">
				        		<option selected = "selected" >康乐服务-文化娱乐</option>
				        	<#else>
				        		<option>康乐服务-文化娱乐</option>
				        	</#if>	
				        	
					        <#if entity["category"]=="康乐服务-法律帮助">
				        		<option selected = "selected" >康乐服务-法律帮助</option>
				        	<#else>
				        		<option>康乐服务-法律帮助</option>
				        	</#if>	
				        </select>    					
  					</div>
		<@detail fId="businessName" />
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    		<label for="characteristic" class="w-25">${entityConf.fields["characteristic"].fName}:</label>
    		<img src="${ctx}/resources/characteristic/${entity["characteristic"]}" class="img-thumbnail" style="width: 345px;">
    	</div>	
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="characteristic" class="w-25">新商品详情:</label>
			<input type="file" name="characteristic" id="characteristic" accept="image/gif,image/jpeg,image/jpg,image/png" />
		</div>    		
		<@detail fId="salesVolume" />
		<@detail fId="evaluationPrice" />
		<@detail fId="evaluationService" />
		<@editHidden fId="businessName" type="hidden"/>
		<@editHidden fId="salesVolume" type="hidden"/>
		<@editHidden fId="evaluationPrice" type="hidden"/>
		<@editHidden fId="evaluationService" type="hidden"/>
	</@editForm>
</@modalShow>

<script>
	jQuery.validator.addMethod("money", function(value, element) {
		var money =  /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/
		return this.optional(element) || (money.test(value));
	}, "请输入正确的价格");

	$(document).ready(function(){
		var $editForm=$("#${entityAbbr}EditForm");
		var $mngModal=$("#manageModal");
		
		<!-- 格式检验代码 -->
		$editForm.validate({
			rules:{				
					name: {
						required:true,
						maxlength:32},
					price:{
						required:true,
						money:true},
					category: {
						required:true}				
			},
	        submitHandler:function(form){
	            $("#editSubmitButton").attr("disabled","disabled");
	            $(form).ajaxSubmit({
	                type:"post",
	                dateType:"json",
	                success:function(resp){
	                	$.dealAjaxResp(resp,function(data){
	                	$("#editSubmitButton").removeAttr("disabled");
	                	alert("修改成功");
	                	document.getElementById('closeButton').click();
	                    });
	                }
	            });
	        },
	        errorPlacement:function(error,element){
	            error.appendTo(element.parent());
	        }
        });			
	});
</script>