<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@modalShow title="修改">
	<@editForm id="${entityAbbr}Edit" action="${basePath}/edit/submit">
		<@editHidden fId="idSeller" type="hidden"/>
		<@detail fId="idSeller" />
		<@edit fId="nameSeller" />
		<@edit fId="addressSeller" />
		<@edit fId="goodSeller" />
		<@edit fId="badSeller" />
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
				idSeller: {zhengzhengshu:true},					
				nameSeller: {
					required:true,
					maxlength:32},
				addressSeller : {
					required:true,
					maxlength:64},
				goodSeller:{
					required:true,
					digits:true
				},
				badSeller:{
					required:true,
					digits:true
				}
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