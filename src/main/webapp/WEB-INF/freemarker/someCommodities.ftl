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
  		data-side-pagination="server"
  		data-query-params="queryParams"
  		data-query-params-type=''
  		data-url="${ctx}/commodity/qry"
  		data-response-handler="responseHandler">
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
  		
  		function responseHandler(res) {
    		return res.data.pager
  		}
  		
  			
	</script>
</@html>

