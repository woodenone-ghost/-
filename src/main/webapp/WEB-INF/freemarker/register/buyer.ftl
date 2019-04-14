<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_body>
		<@editForm id="buyerEdit" action="${ctx}/register/buyer/submit" style="position: relative;right: 820px;top: 50px;">
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
  				<input type="hidden" class="form-control w-50" id="${id}" name="idBuyer" value="${id}" />
  			</div>
			<@add fId="nameBuyer" colClass="col-lg-7" />
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
				<label for="sexBuyer" class="w-25">买家性别:</label>
				<input type="radio" name="sexBuyer" value="男" checked="checked" required>男
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="sexBuyer" value="女" >女
			</div>
			<@add fId="ageBuyer" colClass="col-lg-7" />
			<@add fId="addressBuyer" colClass="col-lg-7" />
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
				<label for="pastHistoryBuyer" class="w-25">买家既往史:</label>
				<textarea class="w-50" rows="3" name="pastHistoryBuyer" id="pastHistoryBuyer"></textarea>
			</div>	
			<@add fId="childNameBuyer" colClass="col-lg-7" />
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
				<label for="childIdentityBuyer" class="w-25">买家子女身份:</label>
				<input type="radio" name="childIdentityBuyer" value="儿子">儿 子
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="childIdentityBuyer" value="女儿">女 儿
			</div>
			<@add fId="childPhoneBuyer" colClass="col-lg-7" />
		</@editForm>
	</@page_body>
	
	<@js_include />
	
	<script>
		$(document).ready(function(){
			var $qryForm=$("#buyerEditForm");
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{			
					nameBuyer: {
						required:true,
						maxlength:10},
					ageBuyer:{
						required:true,
						digits:true,
						range:[30,99]},
					addressBuyer:{
						required:true,
						maxlength:128},
					pastHistoryBuyer:{maxlength:256},
					childNameBuyer: {maxlength:10},
					childPhoneBuyer:{
					maxlength:11,
					minlength:11}
				},
	        	submitHandler:function(form){
	            	$("#editSubmitButton").attr("disabled","disabled");
	            	$(form).ajaxSubmit({
	                	type:"post",
	                    dateType:"json",
	                    success:function(resp){
	                    	$.dealAjaxResp(resp,function(data){
	                        	$("#editSubmitButton").removeAttr("disabled");
	                            alert("买家注册成功");
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