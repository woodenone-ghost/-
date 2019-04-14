<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_body>
		<div class="jumbotron">
    		<h2 style="position: relative;left: 65px;">欢迎注册养老服务管理系统</h2> 
  		</div>
		<form id="registerForm" class="form-inline" action="${ctx}/register/submit" method="POST">        
        	<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="account" class="w-25">账户:</label>
  				<input type="text" class="form-control w-50" id="account" name="account" />
  			</div>
  			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="password" class="w-25">密码:</label>
  				<input type="password" class="form-control w-50" id="password" name="password" placehold="长度：10-16位"/>
  			</div>
  			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    			<label for="passwordZ" class="w-25">重复密码:</label>
  				<input type="password" class="form-control w-50" id="passwordZ" name="passwordZ" placehold="长度：10-16位"/>
  			</div>
  			<div class="col-lg-7 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
  				<label for="buyer" class="w-25">身份:</label>
  				<input type="radio" name="identity" checked="checked" value="buyer" id="buyer">&nbsp;&nbsp;我 是 买 家
  				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  				<input type="radio" name="identity" value="seller" id="seller" required>&nbsp;&nbsp;我 是 卖 家
			</div>			
        	<button id="submitButton" type="submit" class="btn btn-success" style="position: relative;top: 50px;right: 800px;width: 100px;">注 册</button>		
    	</form>
	</@page_body>
	
	<@js_include />

	<script>
		$(document).ready(function(){
			var $form=$("#registerForm");
	
			<!-- 格式检验代码 -->
			$form.validate({
				rules:{
					account: {
						required:true,
						maxlength:20},					
					password: {
						maxlength:16,
						minlength:10},
					passwordZ:{
						maxlength:16,
						minlength:10,
						equalTo:"#password"}
				},
		       	submitHandler:function(form){
		           	$("#submitButton").attr("disabled","disabled");
		           	$(form).ajaxSubmit({
		               	type:"post",
	                    dateType:"json",
		                success:function(resp){
		                	$.dealAjaxResp(resp,function(data){
		                       	$("#submitButton").removeAttr("disabled");
		                        alert("注册成功");
		                        var account = $("#account").val();
		                        $.jumpTo("${ctx}/register/identity?account="+account);
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