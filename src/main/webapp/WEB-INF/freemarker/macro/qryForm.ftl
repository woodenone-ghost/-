<#macro qryForm id action type="true">
    <form id="${id}Form" class="form-inline" action="${action}" method="POST" style="position: relative;top: 30px;">
        <input type="hidden" id="${id}_pageNum" name="pageNumber" value="1" />
        <input type="hidden" id="${id}_pageSize" name="pageSize" value="10" />
        <#nested />
        
        <div class="w-100 form-inline">
        	<div class="col-lg-1" style="position: relative;left: 40px;margin-bottom: 15px;">        	
				<button type="submit" id="qryButton" class="btn btn-primary">查 询</button>
			</div>	
			<#if type=="true">		
				<div class="col-lg-1" style="position: relative;left: 40px;margin-bottom: 15px;">
					<button type="button" id="deleteButton" class="btn btn-danger">删 除</button>
				</div>
				<#assign userInformation=Session.SESSION_KEY_USER />
				<#if userInformation.identity!="buyer">
					<div class="col-lg-1" style="position: relative;left: 40px;margin-bottom: 15px;">
						<button type="button" id="editButton" class="btn btn-warning">修 改</button>
					</div>
				</#if>
				<div class="col-lg-1" style="position: relative;left: 40px;margin-bottom: 15px;">
					<button type="button" id="detailButton" class="btn btn-info">详 情</button>
				</div>	
			</#if>		
		</div>
    </form>
</#macro>

<#macro qryInput fId colClass="col-lg-6">
    <div class="${colClass} form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    	<label for="${fId}" class="w-25">${entityConf.fields[fId].fName}:</label>
  		<input type="text" class="form-control" id="${fId}" name="_QRY_${fId}"/>
  	</div>
</#macro>

<#macro qryTable id style="position: relative;top: 60px;left: 55px;">	
    <table id="${id}Table" style="${style}"></table>
</#macro>