$.dealAjaxResp = function(r, succCallBack, failCallBack) {
	var code = r.code;
	if ("00" == code) {
		if (typeof succCallBack == "function") {
			succCallBack(r.data)
		}
	} else if ("99" == code) {
		$.jumpTo(_ctx + "/loginout")
	} else {
		if (typeof failCallBack == "function") {
			failCallBack(r)
		} else {
			alert(r.errorMessage)
		}
	}
}

$.jumpTo = function(url) {
	window.location.href = url;
}