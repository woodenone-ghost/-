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
            <script src="${ctx}/resources/jquery/jquery-3.3.1.min.js"></script>
			<script src="${ctx}/resources/bootstrap-4.3.1/js/bootstrap.min.js"></script>
            <script>
            	_ctx="${ctx}";
            </script>
            <style>
				.error{
  					color:red;
				}
			</style>
        </head>

        <body style="width:1534px;">
        	<#nested />
        </body>
    </html>
</#macro>


<#macro page_head> <!-- 固定在网页上端的页头 -->
	<#if Session.SESSION_KEY_USER?exists>
    	<#assign userInformation=Session.SESSION_KEY_USER />
		<nav class="navbar navbar-expand-lg bg-light navbar-light" role="navigation">
			<a class="navbar-brand w-25" href="#">欢迎！${userInformation.account}</a>
			<ul class="navbar-nav" style="position: relative;width:75%">
				<form class="form-inline">
    				<input class="form-control" type="text" placeholder="请搜索..." style="width:400px;">
    				&nbsp;
    				<button class="btn btn-success" type="button" style="width:100px;">搜索</button>
  				</form>
    			<li class="nav-item" style="position:relative;left:380px">
     				<a class="nav-link" href="${ctx}/userInformation">修改个人资料</a>
    			</li>
    			<li class="nav-item" style="position:relative;left:380px">
      				<a class="nav-link" href="${ctx}/changePassword">修改密码</a>
    			</li>
  			</ul>
		</nav>				
	<#else>
		<nav class="navbar navbar-expand-lg bg-light navbar-light" role="navigation">
			<a class="navbar-brand w-25" href="#">养老服务管理系统</a>
			<ul class="navbar-nav" style="position: relative;width:75%">
				<form class="form-inline w-50">
    				<input class="form-control" type="text" placeholder="请搜索..." style="width:400px;">
    				&nbsp;
    				<button class="btn btn-success" type="button" style="width:100px;">搜索</button>
  				</form>
    			<li class="nav-item" style="position:relative;left:400px">
     				<a class="nav-link" href="${ctx}/login">登录</a>
    			</li>
    			<li class="nav-item" style="position:relative;left:400px">
      				<a class="nav-link" href="${ctx}/register">注册</a>
    			</li>
  			</ul>
		</nav>	
	</#if>
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
      			<a class="nav-link" href="${ctx}/aboutWeb">关于网站</a>
    		</li>
    		<li class="nav-item" style="position:relative;left:80px">
      			<a class="nav-link" href="${ctx}/aboutAuthor">关于作者</a>
    		</li>
    		<li class="nav-item" style="position:relative;left:165px">
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