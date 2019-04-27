<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="修改">
	<@editForm id="${entityAbbr}Edit" action="${basePath}/evaluate/submit" style="position: relative;left: 633px;top: 71px;">
	
		<@detail fId="id" colClass="col-lg-6"/>
		<@editHidden fId="id" type="hidden"/>
		
		<@detail fId="idCommodity" colClass="col-lg-6"/>
		<@editHidden fId="idCommodity" type="hidden"/>

		<@detail fId="nameCommodity" colClass="col-lg-6"/>
		<@editHidden fId="nameCommodity" type="hidden"/>
		
		<@detail fId="accountSeller" colClass="col-lg-6"/>
		<@editHidden fId="accountSeller" type="hidden"/>
		
		<@detail fId="quantity" colClass="col-lg-6"/>
		<@editHidden fId="quantity" type="hidden"/>
		
		<@detail fId="price" colClass="col-lg-6"/>
		<@editHidden fId="price" type="hidden"/>
		
	    <div class="col-lg-6 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
	    	<label for="timeZ" class="w-25">${entityConf.fields["time"].fName}:</label>
	  		<input type="text" class="form-control w-50" id="timeZ" name="timeZ" disabled="disabled" value="${entity["time"]?string('yyyy-MM-dd')}"/>
	  	</div>
	  	<input type="hidden" class="form-control w-50" id="time" name="time" value="${entity["time"]?string('yyyy-MM-dd')}"/>
	  	
		<@detail fId="state" colClass="col-lg-6"/>
		<@editHidden fId="state" type="hidden"/>
		
		<div class="col-lg-12 form-inline border border-right-0 border-bottom-0 border-left-0 border-dark" id="evaluation" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="evaluation" class="w-25">评价:</label>
  			<input type="radio" name="evaluation" checked="checked" value="好评">&nbsp;&nbsp;好 评
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="radio" name="evaluation" value="中评" required>&nbsp;&nbsp;中 评	
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="radio" name="evaluation" value="差评" required>&nbsp;&nbsp;差 评			
		</div>
		
		<div class="col-lg-12 form-inline" id="evaluationPrice1" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="evaluationPrice" class="w-25">价格评价:</label>
			<input type="hidden" class="form-control w-50" id="evaluationPrice" name="evaluationPrice"/>
		</div>
		
		<div class="col-lg-12 form-inline" id="evaluationService1" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="evaluationService" class="w-25">服务评价:</label>
			<input type="hidden" class="form-control w-50" id="evaluationService" name="evaluationService"/>
		</div>
		
		<div class="col-lg-12 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
			<label for="evaluationWords" class="w-25">点评一下:</label>
			<textarea class="form-control w-50" id="evaluationWords" name="evaluationWords" rows="3"></textarea>
			
		</div>
			
	</@editForm>
</@modalShow>

<script>
	$(function() {
 		$('#evaluationPrice1').raty({
	        number: 5,
	        targetType: 'number', 
	        path: '${ctx}/resources/image',
	        hints: ['差', '一般', '好', '非常好', '五星!'],
	        size: 24,
	        starOff: 'star-off.png',
	        starOn: 'star-on.png',
	        targetScore: '#evaluationPrice'
	    });		
	    
	    $('#evaluationService1').raty({
	        number: 5,
	        targetType: 'number', 
	        path: '${ctx}/resources/image',
	        hints: ['差', '一般', '好', '非常好', '五星!'],
	        size: 24,
	        starOff: 'star-off.png',
	        starOn: 'star-on.png',
	        targetScore: '#evaluationService'
	    });	
	});
	
</script>