<#assign ctx = request.contextPath>
<#include "/macro/layout.ftl">
<@html>
	<div style="height:970px">
		
	    <div class="container w-25" style="position: relative;top: 100px;background:white;padding:15px">
	        <form id="loginForm" action="${ctx}/login/submit" method="POST">
		        <div class="form-group">
		            <label for="inputAccount" class="col-lg-4 control-label">登录账户</label>
		            <div class="col-lg-12">    
		                <input type="text" id="account" name="account" class="form-control" placeholder="登录账户">
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="inputPassword" class="col-lg-4 control-label">登录密码</label>
		            <div class="col-lg-12">    
		                <input type="password" id="password" name="password" class="form-control" placeholder="登录密码">
		            </div>
		        </div>
		        <div class="col-lg-6">
		        	<button class="btn btn-lg btn-primary btn-block" type="button" id="loginBtn">登&nbsp;&nbsp;录</button>
		        </div>		        
	        </form>
	    </div>
	</div>
	
    <@js_include />
    
    <script>
        ;$(function(){
            var $frm=$("#loginForm");
            $frm.validate({
                rules:{
                    account:{
                        required:true,
                        maxlength:20
                    },
                    password:{
                        required:true,
                        maxlength:20
                    }
                },
                submitHandler:function(form){
                    $("#loginBtn").attr("disabled","disabled");
                    $(form).ajaxSubmit({
                        dataType:"json",
                        success:function(resp){
                            $("loginBtn").removeAttr("disabled");
                            $.dealAjaxResp(resp,function(data){
                                $.jumpTo("${ctx}/buyer/manage");
                            });
                        }
                    });
                },
                errorPlacement:function(error,element){
                    error.appendTo(element.parent());
                }
            });
            $("#loginBtn").click(function(){
                $frm.submit();
            });
        });
    </script>
    
</@html>