<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="详情">
	<@detail fId="id" />
	<@detail fId="accountBuyer" />
	<@detail fId="idCommodity" />
	<@detail fId="nameCommodity" />
	<@detail fId="accountSeller" />
	<@detail fId="quantity" />
	<@detail fId="price" />
    <div class="col-lg-11 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    	<label for="timeZ" class="w-25">${entityConf.fields["time"].fName}:</label>
  		<input type="text" class="form-control w-50" id="timeZ" name="timeZ" disabled="disabled" value="${entity["time"]?string('yyyy-MM-dd')}"/>
  	</div>
	<@detail fId="state" />
	<@detail fId="evaluation" />
	<@detail fId="evaluationPrice" />
	<@detail fId="evaluationService" />
	<@detail fId="evaluationWords" />
</@modalShow>