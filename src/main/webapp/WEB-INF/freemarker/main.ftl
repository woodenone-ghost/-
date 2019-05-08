<#assign ctx = request.contextPath>

<#include "/macro/layout.ftl"/>
<#include "/macro/qryForm.ftl"/>
<#include "/macro/manage_modal.ftl"/>

<@html>
	<@page_head />
	
	<@page_body>
		<#assign userInformation=Session.SESSION_KEY_USER />
		<#if userInformation.identity=="buyer">
		<div>
		
		  <ul class="nav nav-tabs" role="tablist">
		    <li class="nav-item">
		      <a class="nav-link active" data-toggle="tab" href="#bill">未 评 价 账 单</a>
		    </li>
		    <li class="nav-item">
		      <a class="nav-link" data-toggle="tab" href="#myBills">我 的 账 单</a>
		    </li>
		  </ul>	

		  <div class="tab-content">
		    <div id="bill" class="container tab-pane active"><br>
							<!-- 未评价账单表格展示 -->
							<table
							  id="QryTable"
			  			      data-toggle="table"
			  			      data-pagination="true"
			  			      data-side-pagination="server"
			  			      data-query-params-type=''
			  				  data-url="${ctx}/bill/notEvaluateBills"
			  				  data-response-handler="responseHandler">
			  			      <thead>
							  	<tr>
							      <th data-field="id">${entityConf.fields["id"].fName}</th>
							      <th data-field="accountSeller">${entityConf.fields["accountSeller"].fName}</th>
							      <th data-field="nameCommodity">商品名称</th>
							      <th data-field="quantity">${entityConf.fields["quantity"].fName}</th>
							      <th data-field="price">${entityConf.fields["price"].fName}</th>
							      <th data-field="state">${entityConf.fields["state"].fName}</th>
							      <th data-field="control" data-formatter="Formatter1">操作</th>
							    </tr>
							  </thead>			  
							</table>
		    </div>
		    
		    <div id="myBills" class="container tab-pane fade"><br>
							<!-- 我的账单表格展示 -->
							<@qryForm id="Qry" action="${ctx}/bill/qry">
								<@qryInput fId="id" colClass="col-lg-6" />
								<@qryInput fId="idCommodity" colClass="col-lg-6" />
								<@qryInput fId="accountSeller" colClass="col-lg-6" />
							</@qryForm>
				
							<div style="position: relative;top: 40px;">
								<table
								  id="QryTable5"
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
								      <th data-field="idCommodity">${entityConf.fields["idCommodity"].fName}</th>
								      <th data-field="nameCommodity">商品名称</th>
								      <th data-field="accountSeller">${entityConf.fields["accountSeller"].fName}</th>
								      <th data-field="price">${entityConf.fields["price"].fName}</th>
								      <th data-field="time">${entityConf.fields["time"].fName}</th>
								      <th data-field="state">${entityConf.fields["state"].fName}</th>
								    </tr>
								  </thead>			  
								</table>
							</div>
		    </div>
		  </div>
		</div>	
		<@modal id="manageModal" />			
		</div>
		<#elseif userInformation.identity=="seller">
			<div class="row" style="height:830px;">		
	  			<div class="col-lg-2" style="padding-right:0;"> <!-- 左侧导航栏 -->
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
	    					<li class="nav-item">
	      						<a class="nav-link" href="#">图 表 查 看</a>
	    					</li>    					
	  					</ul>
					</nav>
				</div> 
				
				<div class="col-lg-10" style="padding-left:0;"> <!-- 网页具体内容 -->
					<ul class="nav nav-tabs">
		  				<li class="nav-item">
		    				<a class="nav-link active" data-toggle="tab" href="#todayBills">今 日 账 单</a>
		  				</li>
		  				<li class="nav-item">
		    				<a class="nav-link" data-toggle="tab" href="#bills">未 发 货 账 单</a>
					  	</li>
					  	<li class="nav-item">
		    				<a class="nav-link" data-toggle="tab" href="#badBills">差 评 账 单</a>
					  	</li>
					</ul>
	
	
					<div class="tab-content">
					
	    				<div id="todayBills" class="container tab-pane active"><br>
							<!-- 今日账单表格展示 -->
							<table
							  id="QryTable"
			  			      data-toggle="table"
			  			      data-pagination="true"
			  			      data-side-pagination="server"
			  			      data-query-params-type=''
			  				  data-url="${ctx}/bill/todayBills"
			  				  data-response-handler="responseHandler">
			  			      <thead>
							  	<tr>
							      <th data-field="id">${entityConf.fields["id"].fName}</th>
							      <th data-field="accountBuyer">${entityConf.fields["accountBuyer"].fName}</th>
							      <th data-field="nameCommodity">商品名称</th>
							      <th data-field="quantity">${entityConf.fields["quantity"].fName}</th>
							      <th data-field="price">${entityConf.fields["price"].fName}</th>
							      <th data-field="state">${entityConf.fields["state"].fName}</th>
							      <th data-field="control" data-formatter="Formatter">操作</th>
							    </tr>
							  </thead>			  
							</table>
	    				</div>
	    				
	    				<div id="bills" class="container tab-pane fade"><br>
							<!-- 未评价账单表格展示 -->
							<table
							  id="QryTable1"
			  			      data-toggle="table"
			  			      data-pagination="true"
			  			      data-side-pagination="server"
			  			      data-query-params-type=''
			  				  data-url="${ctx}/bill/notEvaluateBills"
			  				  data-response-handler="responseHandler"		  				  
  							  }
			  				  >
			  			      <thead>
							  	<tr>
							      <th data-field="id">${entityConf.fields["id"].fName}</th>
							      <th data-field="accountBuyer">${entityConf.fields["accountBuyer"].fName}</th>
							      <th data-field="nameCommodity">商品名称</th>
							      <th data-field="quantity">${entityConf.fields["quantity"].fName}</th>
							      <th data-field="price">${entityConf.fields["price"].fName}</th>
							      <th data-field="time">${entityConf.fields["time"].fName}</th>
							      <th data-field="state">${entityConf.fields["state"].fName}</th>
							      <th data-field="control" data-formatter="Formatter">操作</th>
							    </tr>
							  </thead>			  
							</table>
	    				</div>
	    				
	    				<div id="badBills" class="container tab-pane fade"><br>
							<!-- 差评账单表格展示 -->
							<table
							  id="QryTable2"
			  			      data-toggle="table"
			  			      data-pagination="true"
			  			      data-side-pagination="server"
			  			      data-query-params-type=''
			  				  data-url="${ctx}/bill/badBills"
			  				  data-response-handler="responseHandler"		  				  
  							  }
			  				  >
			  			      <thead>
							  	<tr>
							      <th data-field="id">${entityConf.fields["id"].fName}</th>
							      <th data-field="accountBuyer">${entityConf.fields["accountBuyer"].fName}</th>
							      <th data-field="nameCommodity">商品名称</th>
							      <th data-field="quantity">${entityConf.fields["quantity"].fName}</th>
							      <th data-field="price">${entityConf.fields["price"].fName}</th>
							      <th data-field="time">${entityConf.fields["time"].fName}</th>
							      <th data-field="control" data-formatter="Formatter">操作</th>
							    </tr>
							  </thead>			  
							</table>
	    				</div>	    				
	  				</div>
	  				
	  				<@modal id="manageModal" />
	  									
				</div>	
			 		
			</div>	
		</#if>
	</@page_body>
	
	<@page_tail />
	
	<@js_include />
	
	<script>
			function responseHandler(res) {
    			return res.data.pager
  			}		
		
	 	function Formatter(value, row) {   	
	    	var id1=row.id
	    	var result="<button type=\"button\" id=\"detailButton\" class=\"btn btn-info\" onclick=\"detail("+id1+")\">详 情</button>"
	    	return result
  		}
  		
  		function Formatter1(value, row) {   	
	    	var id1=row.id
	    	var result="<button type=\"button\" class=\"btn btn-info\" onclick=\"evaluate1("+id1+")\">评 价</button>"
	    	result=result+" "+"<button type=\"button\" id=\"detailButton\" class=\"btn btn-primary\" onclick=\"detail("+id1+")\">详 情</button>"
	    	return result
  		}
  		
  		function detail(id){
  			 var $mngModal=$("#manageModal");
  			 
  			 $mngModal.find(".modal-content").empty();
             var url="${ctx}/bill/detail?id="+id;
             $.get(url,function(data){
             	$mngModal.find(".modal-content").html(data);
             	$mngModal.modal('show');
             });
  		}
  		
  		function evaluate1(id){
  			 var $mngModal=$("#manageModal");
  			 
  			 $mngModal.find(".modal-content").empty();
             var url="${ctx}/bill/evaluate?id="+id;
             $.get(url,function(data){
             	$mngModal.find(".modal-content").html(data);
             	$mngModal.modal('show');
             });
  		}
		
		<#if userInformation.identity=="buyer">
		
			jQuery.validator.addMethod("zhengzhengshu", function(value, element) {
			    var zhengzhengshu =  /^[1-9]\d*$/
			    return this.optional(element) || (zhengzhengshu.test(value));
			}, "请输入正确的内容");
			
			function queryParams(params) {
				params._QRY_id = $("#id").val()
	    		params._QRY_idCommodity = $("#idCommodity").val()
	    		params._QRY_accountSeller = $("#accountSeller").val()
	    		return params
	  		}
	  		
	  		 function getData(number, size) {
	    	 	$.get('${ctx}/bill/qry', {
	      			pageNumber: number,
	      			pageSize: size,
	      			_QRY_id : $("#id").val(),
	      			_QRY_idCommodity : $("#idCommodity").val(),
	      			_QRY_accountSeller : $("#accountSeller").val()
	    		}, function (data) {
	    		var $qryTable=$("#QryTable");
	      		$qryTable.bootstrapTable("load", data.data.pager)
	    		})
	 		}
	 		
			$(document).ready(function(){
				var $qryForm=$("#QryForm");
				var $qryTable=$("#QryTable5");
				var $mngModal=$("#manageModal");
				
				$qryTable.on('page-change.bs.table', function (e, number, size) {
	      			getData(number, size)
	    		})
				
				<!-- 格式检验代码 -->
				$qryForm.validate({
					rules:{
						_QRY_id: {zhengzhengshu:true},
						_QRY_idCommodity: {zhengzhengshu:true},
						_QRY_accountSeller: {zhengzhengshu:32}
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
							url:"${ctx}/bill/delete",
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
				
				<!-- 记录详情代码 -->			
	            $("#detailButton").click(function(){
	                var rows=$qryTable.bootstrapTable('getSelections');
					if(rows==null||rows.length!=1){
						alert("请选择一条记录");
						return;
					}
	                $mngModal.find(".modal-content").empty();
	                var url="${ctx}/bill/detail?id="+rows[0].id;
	                $.get(url,function(data){
	                    $mngModal.find(".modal-content").html(data);
	                    $mngModal.modal('show');
	                });
	            });			
				
				    
			});	 		
	 				
		<#elseif userInformation.identity=="seller">	

		</#if>
	</script>
</@html>

