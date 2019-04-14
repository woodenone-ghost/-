<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_body>
		<div class="jumbotron">
    		<h2 style="position: relative;left: 65px;">修改密码，请注意！</h2> 
  		</div>
		<@editForm id="changePassword" action="${ctx}/changePassword/submit" style="position: relative;right: 807px;top: 50px;">
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="oldPassword" class="w-25">原密码:</label>
  				<input type="password" class="form-control w-50" id="oldPassword" name="oldPassword" placehold="长度：10-16位"/>
	  		</div>
			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="newPassword" class="w-25">新密码:</label>
  				<input type="password" class="form-control w-50" id="newPassword" name="newPassword" placehold="长度：10-16位"/>
	  		</div>
	  		<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
	    		<label for="newPasswordZ" class="w-25">重复密码:</label>
	  			<input type="password" class="form-control w-50" id="newPasswordZ" name="newPasswordZ" placehold="长度：10-16位"/>
	  		</div>
		</@editForm>
	</@page_body>
	
	<@js_include />
	
	<script>
		$(document).ready(function(){
			var $qryForm=$("#changePasswordForm");
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{			
					oldPassword: {
						required:true,
						minlength:10,
						maxlength:16},
					newPassword: {
						required:true,
						minlength:10,
						maxlength:16},					
					newPasswordZ: {
						required:true,
						minlength:10,
						maxlength:16,
						equalTo:"#newPassword"}
				},
	        	submitHandler:function(form){
	            	$("#editSubmitButton").attr("disabled","disabled");
	            	$(form).ajaxSubmit({
	                	type:"post",
	                    dateType:"json",
	                    success:function(resp){
	                    	$.dealAjaxResp(resp,function(data){
	                        	$("#editSubmitButton").removeAttr("disabled");
	                            alert("修改密码成功");
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