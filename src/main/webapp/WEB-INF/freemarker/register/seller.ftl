<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_body>
		<@editForm id="sellerEdit" action="${ctx}/register/seller/submit" style="position: relative;right: 810px;top: 50px;">
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
  				<input type="hidden" class="form-control w-50" id="${id}" name="idSeller" value="${id}" />
  			</div>
			<@add fId="nameSeller" colClass="col-lg-7" />
			<@add fId="addressSeller" colClass="col-lg-7" />
		</@editForm>
	</@page_body>
	
	<@js_include />
	
		<script>
		$(document).ready(function(){
			var $qryForm=$("#sellerEditForm");
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{			
					nameSeller: {
						required:true,
						maxlength:32},
					addressSeller:{
						required:true,
						maxlength:128}
				},
	        	submitHandler:function(form){
	            	$("#editSubmitButton").attr("disabled","disabled");
	            	$(form).ajaxSubmit({
	                	type:"post",
	                    dateType:"json",
	                    success:function(resp){
	                    	$.dealAjaxResp(resp,function(data){
	                        	$("#editSubmitButton").removeAttr("disabled");
	                            alert("卖家注册成功");
	                            $.jumpTo("${ctx}/login");
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
	
</@html>

