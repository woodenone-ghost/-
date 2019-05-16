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
				<nav class="navbar bg-light navbar-light h-100">
	  					<ul class="navbar-nav" style="position: relative;left: 30px;bottom: 300px;">
							<li class="nav-item">			
	      						<a class="nav-link " href="${ctx}/loginout">登 出</a>
	    					</li>
	    					<li class="nav-item">			
	      						<a class="nav-link " href="${ctx}/commodity/add">增 加 商 品</a>
	    					</li>
	    					<li class="nav-item">
	      						<a class="nav-link" href="${ctx}/commodity/sellerManage">管 理 商 品</a>
	    					</li>
	    					<li class="nav-item">
	      						<a class="nav-link" href="${ctx}/bill/sellerManage">管 理 账 单</a>
	    					</li>
	    					<li class="nav-item dropdown">
						    	<a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">图 表 查 看</a>
						    	<div class="dropdown-menu">
						    		<a class="dropdown-item" href="${ctx}/bill/lineChart">折 线 图</a>
						      		<a class="dropdown-item" href="${ctx}/bill/barChart">柱 状 图</a>
						      		<a class="dropdown-item" href="${ctx}/bill/pieGraph">饼 图</a>
						    	</div>
						    </li> 					
	  					</ul>
				</nav>
			</div>  			
  			<div class="col-lg-10"> <!-- 网页具体内容 -->
				<@qryForm id="${entityAbbr}Qry" action="${basePath}/qry" type="false">
					<@qryInput fId="id" colClass="col-lg-6" />
					<@qryInput fId="name" colClass="col-lg-6" />
					<div class="col-lg-6 form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    					<label for="category" class="w-25">${entityConf.fields["category"].fName}:</label>
				        <select class="form-control" id="category" name="_QRY_category" style="width: 203px;">
				        	<option></option>
					        <option>助洁-购物</option>
					        <option>助洁-打扫</option>
					        <option>助餐-上门助餐</option>
					        <option>助餐-老年食堂</option>
					        <option>助医-护理保健</option>
					        <option>助医-上门诊治</option>
					        <option>康乐服务-聊天解闷</option>
					        <option>康乐服务-文化娱乐</option>
					        <option>康乐服务-法律帮助</option>
				        </select>    					
  					</div>
				</@qryForm>
				
				<div style="position: relative;top: 40px;">
				<table
				  id="QryTable"
				  data-unique-id="id"
  			      data-toggle="table"
  			      data-pagination="true"
  			      data-side-pagination="server"
  			      data-query-params="queryParams"
  			      data-query-params-type=''
  			      data-single-select="true"
  				  data-click-to-select="true">
  			      <thead>
				  	<tr>
				  	  <th data-field="id">${entityConf.fields["id"].fName}</th>
				      <th data-field="name">${entityConf.fields["name"].fName}</th>
				      <th data-field="category">${entityConf.fields["category"].fName}</th>				 
				      <th data-field="shangjia">${entityConf.fields["shangjia"].fName}</th>
				      <th data-field="control" data-formatter="Formatter">图 表</th>
				      <th data-field="control1" data-formatter="Formatter1">操 作</th>
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
	
	 	function Formatter(value, row) {   	
	    	var id1=row.id
	    	var result="<select class=\"form-control\" id=\""+id1+"_select\">"
	    	result=result+ "<option>男女比例-饼图</option><option>年龄分布-饼图</option><option>评价分布-饼图</option></select>"
	    	return result
  		}
  		
  		function Formatter1(value, row) {   	
	    	var id1=row.id
	    	var result="<button type=\"button\" class=\"btn btn-info\" onclick=\"xianshi("+id1+")\">显 示</button>"
	    	return result
  		}
  		
  		function xianshi(id){
  			var selected=document.getElementById(id+"_select").value;
			$.post('${basePath}/pieGraph/xianshi', {
      			id:id,
      			selected:selected
    		}, function (data) {
    			var $mngModal=$("#manageModal");
    			$mngModal.find(".modal-content").html(data);
                $mngModal.modal('show');
    		})
  		}	
	
		jQuery.validator.addMethod("zhengzhengshu", function(value, element) {
		    var zhengzhengshu =  /^[1-9]\d*$/
		    return this.optional(element) || (zhengzhengshu.test(value));
		}, "请输入正确的内容");
		
		function queryParams(params) {
			params._QRY_id = $("#id").val()
    		params._QRY_accountBuyer = $("#accountBuyer").val()
    		params._QRY_idCommodity = $("#idCommodity").val()
    		return params
  		}
  		
  		 function getData(number, size) {
    	 	$.get('${basePath}/qry', {
      			pageNumber: number,
      			pageSize: size,
      			_QRY_id : $("#id").val(),
      			_QRY_accountBuyer : $("#accountBuyer").val(),
      			_QRY_idCommodity : $("#idCommodity").val(),
    		}, function (data) {
    		var $qryTable=$("#${entityAbbr}QryTable");
      		$qryTable.bootstrapTable("load", data.data.pager)
    		})
 		}
  			
		$(document).ready(function(){
			var $qryForm=$("#${entityAbbr}QryForm");
			var $qryTable=$("#QryTable");
			var $mngModal=$("#manageModal");
			
			$qryTable.on('page-change.bs.table', function (e, number, size) {
      			getData(number, size)
    		})
			
			<!-- 格式检验代码 -->
			$qryForm.validate({
				rules:{
					_QRY_id: {zhengzhengshu:true},
					_QRY_accountBuyer: {zhengzhengshu:32},	
					_QRY_idCommodity: {zhengzhengshu:true},
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
			    
		});
		
	
	</script>
	
</@html>	