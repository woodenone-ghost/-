<#assign ctx = request.contextPath>
<#include "/macro/layout.ftl">
<@html>
	<h3>对不起，您没有访问当前页面的权利！</h3>
	
	<a href="${ctx}/login">跳转到主页</a>
	
	<@js_include />
</@html>