<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_body>
		<#assign user=Session.SESSION_KEY_USER />
		<div class="jumbotron">
    		<h2 style="position: relative;left: 65px;">修改个人资料</h2> 
  		</div>
		<@editForm id="Edit" action="${ctx}/userInformation/submit" style="position: relative;right: 820px;top: 50px;">
			<#if user.identity=="buyer">
				<@editHidden fId="idBuyer" type="hidden"/>
				<@edit fId="nameBuyer" colClass="col-lg-7"/>
				<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
					<label for="sexBuyer" class="w-25">买家性别:</label>				
					<input type="radio" name="sexBuyer" value="男" required>男
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="sexBuyer" value="女" >女
				</div>
				<@edit fId="ageBuyer" colClass="col-lg-7"/>
				<@edit fId="addressBuyer" colClass="col-lg-7"/>
				<@edit fId="pastHistoryBuyer" colClass="col-lg-7"/>
				<@edit fId="childNameBuyer" colClass="col-lg-7"/>
				<@edit fId="childIdentityBuyer" colClass="col-lg-7"/>
				<@edit fId="childPhoneBuyer" colClass="col-lg-7"/>
			<#else>
				<@editHidden fId="idSeller" type="hidden"/>
				<@edit fId="nameSeller" colClass="col-lg-7"/>
				<@edit fId="addressSeller" colClass="col-lg-7"/>
				<@detail fId="goodSeller" colClass="col-lg-7"/>
				<@detail fId="badSeller" colClass="col-lg-7"/>
			</#if>
		</@editForm>
	</@page_body>
	
	<@js_include />
	
	<script>
	<#if user.identity=="buyer">
		$(document).ready(function(){
			var $editForm=$("#EditForm");
			
			<!-- 格式检验代码 -->
			$editForm.validate({
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
	<#else>
		$(document).ready(function(){
			var $editForm=$("#EditForm");
			
			<!-- 格式检验代码 -->
			$editForm.validate({
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
		                	alert("修改成功");
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
	</#if>
	</script>
	

</@html>