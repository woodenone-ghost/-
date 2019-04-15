<#assign ctx = request.contextPath>
<#assign basePath = request.contextPath+"/"+entityConf.abbr>
<#assign entityAbbr = entityConf.abbr>
<#assign entityNm = entityConf.entityName>
<#assign fields = entityConf.fields>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_head />
	<@page_body>
		<div class="row" style="height:830px;">
  			<div class="col-lg-2"> <!-- 左侧导航栏 -->
				<nav class="navbar bg-dark navbar-dark h-100">
  					<ul class="navbar-nav" style="position: relative;left: 30px;bottom: 300px;">
    					<li class="nav-item">			
      						<a class="nav-link " href="${ctx}/buyer/manage">买 家 管 理</a>
    					</li>
    					<li class="nav-item">
      						<a class="nav-link" href="${ctx}/seller/manage">卖 家 管 理</a>
    					</li>
    					<li class="nav-item">
      						<a class="nav-link" href="${ctx}/commodity/manage">商 品 管 理</a>
    					</li>
    					<li class="nav-item">
      						<a class="nav-link" href="${ctx}/bill/manage">账 单 管 理</a>
    					</li>
    					<li class="nav-item">
      						<a class="nav-link" href="#">图 表 查 看</a>
    					</li>    					
  					</ul>
				</nav>
			</div>  			
  			<div class="col-lg-10"> <!-- 网页具体内容 -->
				<@qryForm id="${entityAbbr}Qry" action="${basePath}/qry">
					<@qryInput fId="id" colClass="col-lg-6" />
					<@qryInput fId="name" colClass="col-lg-6" />
					<@qryInput fId="category" colClass="col-lg-6" />
					<@qryInput fId="businessNameName" colClass="col-lg-6" />
				</@qryForm>
				
				<div style="position: relative;top: 40px;">
				<table
				  id="${entityAbbr}QryTable"
  			      data-toggle="table"
  			      data-pagination="true"
  			      data-side-pagination="server"
  			      data-query-params="queryParams"
  			      data-query-params-type=''
  			      data-single-select="true"
  				  data-click-to-select="true">
  			      <thead>
				  	<tr>
				  	  <th data-field="selected" data-checkbox="true"></th>
				  	  <th data-field="id">${entityConf.fields["id"].fName}</th>
				      <th data-field="name">${entityConf.fields["idBuyer"].fName}</th>
				      <th data-field="category">${entityConf.fields["idCommodity"].fName}</th>
				      <th data-field="businessName">${entityConf.fields["idSeller"].fName}</th>
				      <th data-field="price">${entityConf.fields["price"].fName}</th>
				      <th data-field="salesVolume">${entityConf.fields["time"].fName}</th>
				    </tr>
				  </thead>			  
				</table>
				</div>
			</div>  			
		</div>
	</@page_body>
	
	<@modal id="manageModal" />
	
	
	<@js_include />

	<script>
		
		function queryParams(params) {
			params._QRY_id = $("#id").val()
    		params._QRY_name = $("#name").val()
    		params._QRY_category = $("#category").val()
    		params._QRY_businessName = $("#businessName").val()
    		return params
  		}
  		
  		 function getData(number, size) {
    	 	$.get('${basePath}/qry', {
      			pageNumber: number,
      			pageSize: size,
      			_QRY_id = $("#id").val(),
      			_QRY_name : $("#name").val(),
      			_QRY_category : $("#category").val(),
      			_QRY_businessName : $("#businessName").val()
    		}, function (data) {
    		var $qryTable=$("#${entityAbbr}QryTable");
      		$qryTable.bootstrapTable("load", data.data.pager)
    		})
 		}
  			
		$(document).ready(function(){
			var $qryForm=$("#${entityAbbr}QryForm");
			var $qryTable=$("#${entityAbbr}QryTable");
			var $mngModal=$("#manageModal");
			
			$qryTable.on('page-change.bs.table', function (e, number, size) {
      			getData(number, size)
    		})
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{
					_QRY_id: {digits:true},
					_QRY_name: {maxlength:32},	
					_QRY_category: {maxlength:64},
					_QRY_businessName: {maxlength:32}
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
			
			<!-- 删除记录代码 -->
			$("#deleteButton").click(function(){
				var rows=$qryTable.bootstrapTable('getSelections');
				if(rows==null||rows.length!=1){
					alert("请选择一条记录");
					return;
				}
				if(confirm("确认删除?")){
					$.ajax({
						url:"${basePath}/delete",
						data:{
							id:rows[0].id
						},
						method:"post",
						dataType:"json",
						success:function(resp){
							$.dealAjaxResp(resp,function(data){
								alert("删除成功");
								$qryForm.submit();
							});
						}
					});
				}
			}); 
			
            $("#editButton").click(function(){
                var rows=$qryTable.bootstrapTable('getSelections');
                if(rows==null||rows.length!=1){
                    alert("请选择一条记录");
                    return;
                }
                $mngModal.find(".modal-content").empty();
                var url="${basePath}/edit?id="+rows[0].id;
                $.get(url,function(data){
                    $mngModal.find(".modal-content").html(data);
                    $mngModal.modal('show');
                });
            });            
			
			<!-- 记录详情代码 -->			
            $("#detailButton").click(function(){
                var rows=$qryTable.bootstrapTable('getSelections');
				if(rows==null||rows.length!=1){
					alert("请选择一条记录");
					return;
				}
                $mngModal.find(".modal-content").empty();
                var url="${basePath}/detail?id="+rows[0].id;
                $.get(url,function(data){
                    $mngModal.find(".modal-content").html(data);
                    $mngModal.modal('show');
                });
            });			
			
			    
		});
		
	
	</script>
	
</@html>	