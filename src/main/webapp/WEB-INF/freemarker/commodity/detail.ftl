<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="详情">
	<@detail fId="id" />
	<@detail fId="icon" />
	<@detail fId="name" />
	<@detail fId="price" />
	<@detail fId="category" />
	<@detail fId="businessName" />
	<@detail fId="characteristic" />
	<@detail fId="salesVolume" />
	<@detail fId="evaluation" />
</@modalShow>