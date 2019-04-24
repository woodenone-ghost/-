<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="修改">
	<@editForm id="${entityAbbr}Edit" action="${basePath}/edit/submit">
		<@editHidden fId="idBuyer" type="hidden"/>
		<@detail fId="idBuyer" />
		<@edit fId="nameBuyer" />
		<@edit fId="sexBuyer" />
		<@edit fId="ageBuyer" />
		<@edit fId="addressBuyer" />
		<@edit fId="pastHistoryBuyer" />
		<@edit fId="childNameBuyer" />
		<@edit fId="childIdentityBuyer" />
		<@edit fId="childPhoneBuyer" />
	</@editForm>
</@modalShow>

<script>
		jQuery.validator.addMethod("zhengzhengshu", function(value, element) {
			var zhengzhengshu =  /^[1-9]\d*$/
			return this.optional(element) || (zhengzhengshu.test(value));
		}, "请输入正确的内容");

	$(document).ready(function(){
		var $editForm=$("#${entityAbbr}EditForm");
		var $mngModal=$("#manageModal");
		
		<!-- 格式检验代码 -->
		$editForm.validate({
			rules:{
				idBuyer: {zhengzhengshu:true},					
				nameBuyer: {
					required:true,
					maxlength:10},
				sexBuyer : {
					required:true,
					maxlength:1},
				ageBuyer:{
					required:true,
					digits:true,
					zhengzhengshu:true,
					range:[30,99]
				},
				addressBuyer: {
					required:true,
					maxlength:128},
				pastHistoryBuyer: {maxlength:256},
				childNameBuyer: {maxlength:10},
				childIdentityBuyer: {maxlength:2},
				childPhoneBuyer:{maxlength:11}
			},
	        submitHandler:function(form){
	            $("#editSubmitButton").attr("disabled","disabled");
	            $(form).ajaxSubmit({
	                type:"post",
	                dateType:"json",
	                success:function(resp){
	                	$.dealAjaxResp(resp,function(data){
	                	$("#editSubmitButton").removeAttr("disabled");
	                	alert("修改成功");
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