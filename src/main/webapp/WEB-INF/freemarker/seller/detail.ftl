<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="详情">
	<@detail fId="idSeller" />
	<@detail fId="nameSeller" />
	<@detail fId="addressSeller" />
	<@detail fId="goodSeller" />
	<@detail fId="badSeller" />
</@modalShow>