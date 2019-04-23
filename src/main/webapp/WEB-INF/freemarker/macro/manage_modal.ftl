<#macro modal id>
    <div class="modal" id="${id}" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content"  style="width: 820px">
            	<#nested />
            </div>
        </div>
    </div>
</#macro>

<#macro modalShow title>
	<div class="modal-header">
        <h4 class="modal-title" id="modalTitle">${title}</h4>
    </div>
    <div class="modal-body">
		<#nested />
	</div>
    <div class="modal-footer">  	
    	<button type="button" id="closeButton" class="btn btn-warning" data-dismiss="modal">关闭</button>
    </div>
</#macro>

<#macro editForm id action style="position: relative;right: 80px;top: 86px;">
    <form id="${id}Form" class="form-inline" action="${action}" method="POST" enctype="multipart/form-data">        
        <#nested />	
        <button id="editSubmitButton" type="submit" class="btn btn-success" style="${style}">提 交</button>		
    </form>
</#macro>

<#macro detail fId colClass="col-lg-11">
    <div class="${colClass} form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    	<label for="${fId}Z" class="w-25">${entityConf.fields[fId].fName}:</label>
  		<input type="text" class="form-control w-50" id="${fId}Z" name="${fId}Z" disabled="disabled" value="${entity[fId]}"/>
  	</div>
</#macro>

<#macro edit fId colClass="col-lg-11" type="text">
    <div class="${colClass} form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    	<label for="${fId}" class="w-25">${entityConf.fields[fId].fName}:</label>
  		<input type="${type}" class="form-control w-50" id="${fId}" name="${fId}" value="${entity[fId]}"/>
  	</div>
</#macro>

<#macro editHidden fId type="text">
  		<input type="${type}" class="form-control w-50" id="${fId}" name="${fId}" value="${entity[fId]}"/>
</#macro>

<#macro add fId colClass="col-lg-11" type="text">
    <div class="${colClass} form-inline" style="margin-bottom: 15px;margin-top: 15px;">
    	<label for="${fId}" class="w-25">${entityConf.fields[fId].fName}:</label>
  		<input type="${type}" class="form-control w-50" id="${fId}" name="${fId}" />
  	</div>
</#macro>