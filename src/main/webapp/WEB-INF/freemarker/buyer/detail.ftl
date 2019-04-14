<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="详情">
	<@detail fId="idBuyer" />
	<@detail fId="nameBuyer" />
	<@detail fId="sexBuyer" />
	<@detail fId="ageBuyer" />
	<@detail fId="addressBuyer" />
	<@detail fId="pastHistoryBuyer" />
	<@detail fId="childNameBuyer" />
	<@detail fId="childIdentityBuyer" />
	<@detail fId="childPhoneBuyer" />
</@modalShow>