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
		<@edit fId="accountBuyer" />
		<@edit fId="idCommodity" />
		<@edit fId="accountSeller" />
		<@edit fId="quantity" />
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    		<label for="price" class="w-25">${entityConf.fields["price"].fName}:</label>
  			<input type="text" class="form-control w-25" id="price" name="price" value="${entity["price"]}"/>元
  		</div>
		<div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    		<label for="time" class="w-25">${entityConf.fields["time"].fName}:</label>
  			<input type="date" class="form-control w-50" id="time" name="time" value="${entity["time"]?string('yyyy-MM-dd')}"/>
  		</div>
		<@edit fId="state" />
		<@edit fId="evaluationPrice" />
		<@edit fId="evaluationService" />
		<@edit fId="evaluation" />
		<@edit fId="evaluationWords" />
	</@editForm>
</@modalShow>

<script>
	jQuery.validator.addMethod("zhengzhengshu", function(value, element) {
		var zhengzhengshu =  /^[1-9]\d*$/
		return this.optional(element) || (zhengzhengshu.test(value));
	}, "请输入正确的内容");

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
				id: {zhengzhengshu:true},	
				accountBuyer: {zhengzhengshu:32},
				idCommodity: {zhengzhengshu:true},	
				nameSeller: {
					required:true,
					maxlength:32},						
				quantity: {zhengzhengshu:true},	
				price: {money:true},
				time:{required:true},
				state:{required:true}			
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