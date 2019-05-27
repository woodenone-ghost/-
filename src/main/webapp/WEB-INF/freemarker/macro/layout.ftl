<#macro html title="养老服务管理系统">
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
            <meta content="width=device-width,initial-scale=1" name="viewport" />
            <meta content="" name="description" />
            <meat content="woodone" name="author" />
            <title>${title}</title>
            <link rel="stylesheet" href="${ctx}/resources/bootstrap-4.3.1/css/bootstrap.min.css" type="text/css">
            <link rel="stylesheet" href="${ctx}/resources/bootstrap-table/css/bootstrap-table.min.css" type="text/css">
            <link rel="stylesheet" href="${ctx}/resources/raty-master/css/jquery.raty.css" type="text/css">
            <script src="${ctx}/resources/jquery/jquery-3.3.1.min.js"></script>
			<script src="${ctx}/resources/bootstrap-4.3.1/js/bootstrap.min.js"></script>
			<script src="${ctx}/resources/raty-master/js/jquery.raty.js"></script>
			<script src="${ctx}/resources/EChart/echarts.js"></script>
            <script>
            	_ctx="${ctx}";
            </script>
            <style>
				.error{
  					color:red;
				}
			</style>
        </head>

        <body style="width:100%;">
        	<#nested />
        </body>
    </html>
</#macro>


<#macro page_head> <!-- 固定在网页上端的页头 -->
	<#if Session.SESSION_KEY_USER?exists>
    	<#assign userInformation=Session.SESSION_KEY_USER />
		<nav class="navbar navbar-expand-lg bg-light navbar-light" role="navigation">
			<a class="navbar-brand w-25" href="${ctx}/login">欢迎！${userInformation.account}</a>
			<ul class="navbar-nav" style="position: relative;width:75%">
				<form class="form-inline w-75" action="${ctx}/commodity/gotoSearch" id="qryForm">
				    <input type="hidden" name="pageNumber" value="1" />
        			<input type="hidden" name="pageSize" value="10" />
					<select class="form-control" name="_QRY_category" id="_QRY_category" style="width: 200px;">
				    	<option>全部种类</option>
				    	<#if category?exists>
							<#if category=="助洁-购物">
				        		<option selected = "selected" >助洁-购物</option>
				        	<#else>
				        		<option>助洁-购物</option>
				        	</#if>
				        	
				        	<#if category=="助洁-打扫">
				        		<option selected = "selected" >助洁-打扫</option>
				        	<#else>
				        		<option>助洁-打扫</option>
				        	</#if>
				        	
				        	<#if category=="助餐-上门助餐">
				        		<option selected = "selected" >助餐-上门助餐</option>
				        	<#else>
				        		<option>助餐-上门助餐</option>
				        	</#if>	
				        				        						        
					        <#if category=="助餐-老年食堂">
				        		<option selected = "selected" >助餐-老年食堂</option>
				        	<#else>
				        		<option>助餐-老年食堂</option>
				        	</#if>
				        	
					        <#if category=="助医-护理保健">
				        		<option selected = "selected" >助医-护理保健</option>
				        	<#else>
				        		<option>助医-护理保健</option>
				        	</#if>
				        	
					        <#if category=="助医-上门诊治">
				        		<option selected = "selected" >助医-上门诊治</option>
				        	<#else>
				        		<option>助医-上门诊治</option>
				        	</#if>				        					        	
				        	
					        <#if category=="康乐服务-聊天解闷">
				        		<option selected = "selected" >康乐服务-聊天解闷</option>
				        	<#else>
				        		<option>康乐服务-聊天解闷</option>
				        	</#if>	
				        	
					        <#if category=="康乐服务-文化娱乐">
				        		<option selected = "selected" >康乐服务-文化娱乐</option>
				        	<#else>
				        		<option>康乐服务-文化娱乐</option>
				        	</#if>	
				        	
					        <#if category=="康乐服务-法律帮助">
				        		<option selected = "selected" >康乐服务-法律帮助</option>
				        	<#else>
				        		<option>康乐服务-法律帮助</option>
				        	</#if>					    	
				    	<#else>
					    	<option>助洁-购物</option>
					        <option>助洁-打扫</option>
					        <option>助餐-上门助餐</option>
					        <option>助餐-老年食堂</option>
					        <option>助医-护理保健</option>
					        <option>助医-上门诊治</option>
					        <option>康乐服务-聊天解闷</option>
					        <option>康乐服务-文化娱乐</option>
					        <option>康乐服务-法律帮助</option>	
				    	</#if>				        	
				    </select>
				    &nbsp;
				    <#if name?exists>  
				    	<input class="form-control" type="text" placeholder="请搜索..." name="_QRY_name" id="_QRY_name" value="${name}" style="width:400px;">
				    <#else>
				    	<input class="form-control" type="text" placeholder="请搜索..." name="_QRY_name" id="_QRY_name" style="width:400px;">
				    </#if>  					    				
    				&nbsp;
    				<button class="btn btn-success" type="submit" id="qryButton" style="width:100px;">搜索</button>
  				</form>
    			<li class="nav-item" style="position:relative;left:80px">
     				<a class="nav-link" href="${ctx}/userInformation">修改个人资料</a>
    			</li>
    			<li class="nav-item" style="position:relative;left:80px">
      				<a class="nav-link" href="${ctx}/changePassword">修改密码</a>
    			</li>
  			</ul>
		</nav>				
	<#else>
		<nav class="navbar navbar-expand-lg bg-light navbar-light" role="navigation">
			<a class="navbar-brand w-25" href="#">养老服务管理系统</a>
			<ul class="navbar-nav" style="position: relative;width:75%">
				<form class="form-inline w-75" action="${ctx}/commodity/gotoSearch" id="qryForm">
				    <input type="hidden" name="pageNumber" value="1" />
        			<input type="hidden" name="pageSize" value="10" />
					<select class="form-control" name="_QRY_category" id="_QRY_category" style="width: 200px;">
				    	<option>全部种类</option>
				    	<#if category?exists>
							<#if category=="助洁-购物">
				        		<option selected = "selected" >助洁-购物</option>
				        	<#else>
				        		<option>助洁-购物</option>
				        	</#if>
				        	
				        	<#if category=="助洁-打扫">
				        		<option selected = "selected" >助洁-打扫</option>
				        	<#else>
				        		<option>助洁-打扫</option>
				        	</#if>
				        	
				        	<#if category=="助餐-上门助餐">
				        		<option selected = "selected" >助餐-上门助餐</option>
				        	<#else>
				        		<option>助餐-上门助餐</option>
				        	</#if>	
				        				        						        
					        <#if category=="助餐-老年食堂">
				        		<option selected = "selected" >助餐-老年食堂</option>
				        	<#else>
				        		<option>助洁-购物</option>
				        	</#if>
				        	
					        <#if category=="助医-护理保健">
				        		<option selected = "selected" >助医-护理保健</option>
				        	<#else>
				        		<option>助医-护理保健</option>
				        	</#if>
				        	
					        <#if category=="助医-上门诊治">
				        		<option selected = "selected" >助医-上门诊治</option>
				        	<#else>
				        		<option>助医-上门诊治</option>
				        	</#if>				        					        	
				        	
					        <#if category=="康乐服务-聊天解闷">
				        		<option selected = "selected" >康乐服务-聊天解闷</option>
				        	<#else>
				        		<option>康乐服务-聊天解闷</option>
				        	</#if>	
				        	
					        <#if category=="康乐服务-文化娱乐">
				        		<option selected = "selected" >康乐服务-文化娱乐</option>
				        	<#else>
				        		<option>康乐服务-文化娱乐</option>
				        	</#if>	
				        	
					        <#if category=="康乐服务-法律帮助">
				        		<option selected = "selected" >康乐服务-法律帮助</option>
				        	<#else>
				        		<option>康乐服务-法律帮助</option>
				        	</#if>					    	
				    	<#else>
					    	<option>助洁-购物</option>
					        <option>助洁-打扫</option>
					        <option>助餐-上门助餐</option>
					        <option>助餐-老年食堂</option>
					        <option>助医-护理保健</option>
					        <option>助医-上门诊治</option>
					        <option>康乐服务-聊天解闷</option>
					        <option>康乐服务-文化娱乐</option>
					        <option>康乐服务-法律帮助</option>	
				    	</#if>			        	
				    </select>
				    &nbsp;    					
				    <#if name?exists>  
				    	<input class="form-control" type="text" placeholder="请搜索..." name="_QRY_name" id="_QRY_name" value="${name}" style="width:400px;">
				    <#else>
				    	<input class="form-control" type="text" placeholder="请搜索..." name="_QRY_name" id="_QRY_name" style="width:400px;">
				    </#if>  
    				&nbsp;
    				<button class="btn btn-success" type="submit" id="qryButton" style="width:100px;">搜索</button>
  				</form>
    			<li class="nav-item" style="position:relative;left:100px">
     				<a class="nav-link" href="${ctx}/login">登录</a>
    			</li>
    			<li class="nav-item" style="position:relative;left:100px">
      				<a class="nav-link" href="${ctx}/register">注册</a>
    			</li>
  			</ul>
		</nav>	
	</#if>
	
	<script>
		$(document).ready(function(){
			var $qryForm=$("#qryForm");
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{
					_QRY_name: {
					required:true,
					maxlength:32},	
					_QRY_category: {maxlength:16},
				},
	            errorPlacement:function(error,element){
	                error.appendTo(element.parent());
	            }
        	});
		});
	</script>
	
</#macro>

<#macro page_body> <!-- 网页主题内容 -->
    <div class="container-fluid" style="padding-left:0px;padding-right:0px;">
    	<#nested />
    </div>
</#macro>

<#macro page_tail> <!-- 页尾 -->
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
  		<ul class="navbar-nav">
    		<li class="nav-item active">
      			<a class="nav-link" href="${ctx}/aboutAuthor">关于作者</a>
    		</li>
    		<li class="nav-item" style="position:relative;left:80px">
      			<a class="nav-link" href="https://github.com/woodenone-ghost/bishe">Github</a>
    		</li>
  		</ul>
	</nav>	
</#macro>

<#macro js_include>
    <script src="${ctx}/resources/bootstrap-table/js/bootstrap-table.min.js"></script>
    <script src="${ctx}/resources/bootstrap-table/js/bootstrap-table-zh-CN.min.js"></script>
    <script src="${ctx}/resources/jquery/form/jquery.form.js"></script>
    <script src="${ctx}/resources/jquery/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/resources/jquery/validate/messages_zh.min.js"></script>
    <script src="${ctx}/resources/myJavaScript/myJavaScript.js"></script>
</#macro>