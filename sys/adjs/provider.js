// Các xử lý kịch bản cho provider.html

function checkValidProvider(fn){
	// Lấy tên nhà cung cấp
	var name = fn.txtProviderName.value;
	var check = fn.chkAgree.checked;
	
	if(name.trim()!="" && check){
		fn.btnRegistry.disabled = false;
	}else{
		fn.btnRegistry.disabled = true;
	}
	
}



