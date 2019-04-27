<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>
<#assign entityNm = entityConf.entityName>
<#assign fields = entityConf.fields>
<#include "/macro/layout.ftl">

<@html>
	<@page_head />
	
	<@page_body>
		<table
		id="${entityAbbr}QryTable"
  		data-toggle="table"
  		data-pagination="true"
  		data-url="${ctx}/commodity/qry"
  		data-side-pagination="server"
  		data-query-params="queryParams"
  		data-query-params-type=''>
  		<thead>
			<tr>
				<th data-field="id">${entityConf.fields["id"].fName}</th>
				<th data-field="icon" data-formatter="iconFormatter">${entityConf.fields["icon"].fName}</th>
				<th data-field="name">${entityConf.fields["name"].fName}</th>
				<th data-field="price">${entityConf.fields["price"].fName}</th>
				<th data-field="salesVolume">${entityConf.fields["salesVolume"].fName}</th>
				<th data-field="evaluationPrice">${entityConf.fields["evaluationPrice"].fName}</th>
				<th data-field="evaluationService">${entityConf.fields["evaluationService"].fName}</th>
			</tr>
		</thead>			  
		</table>			
	</@page_body>
	
	<@page_tail />
	
	<@js_include />
	
	<script>
	 	function iconFormatter(value, row) {   	
	    	var id=row.id
	    	var url_src="${ctx}/resources/icon/"+value
	    	var url_href="${ctx}/commodity/detail?id="+id
	    	var result="<a href="+url_href+"><img width=130px src="+url_src+"/></a>"
	    	return result
  		}
	
		function queryParams(params) {
    		params._QRY_name = $("#_QRY_name").val()
    		params._QRY_category = $("#_QRY_category").val()
    		return params
  		}
  		
  		 function getData(number, size) {
    	 	$.get('${basePath}/qry', {
      			pageNumber: number,
      			pageSize: size,
      			_QRY_name : $("#_QRY_name").val(),
      			_QRY_category : $("#_QRY_category").val(),
    		}, function (data) {
    		var $qryTable=$("#${entityAbbr}QryTable");
      		$qryTable.bootstrapTable("load", data.data.pager)
    		})
 		}
 		
		$(document).ready(function(){				
			var $qryForm=$("#qryForm");
			var $qryTable=$("#${entityAbbr}QryTable");
			
			$qryTable.on('page-change.bs.table', function (e, number, size) {
      			getData(number, size)
    		})
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{
					_QRY_name: {
						required:true,
						maxlength:32},	
					_QRY_category: {
						required:true,
						maxlength:16}
				},
	        	submitHandler:function(form){
	            	$("#qryButton").attr("disabled","disabled");
	            	$(form).ajaxSubmit({
	                	type:"post",
	                    dateType:"json",
	                    success:function(resp){
	                    	$.dealAjaxResp(resp,function(data){
	                        	$("#qryButton").removeAttr("disabled");
	                            $qryTable.bootstrapTable("load",data.pager);
	                        });
	                    }
	                });
	            },
	            errorPlacement:function(error,element){
	                error.appendTo(element.parent());
	            }
        	});
        	
        	$.post("${ctx}/commodity/qry",
			  {
			    _QRY_name : $("#_QRY_name").val(),
      			_QRY_category : $("#_QRY_category").val(),
      			pageNumber : 1,
      			pageSize : 10
			  },
			  function(data){
			    $qryTable.bootstrapTable("load",data.data.pager);
			  });  	        	      	
        			
		}); 			
	</script>
</@html>

