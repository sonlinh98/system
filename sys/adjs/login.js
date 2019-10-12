// Các xử lý kịch bản cho login.html

function checkValidLogin(fn){
	// lấy thông tin đăng nhập
	var name = fn.txtAccName.value;
	var pass = fn.txtAccPass.value;
	
	// Biến ghi nhận sự hợp lệ của giá trị
	var validName = true;
	var validPass = true;
	
	// Biến ghi nhận thông báo sau khi kiểm tra giá trị
	var message = "";
	
	// Kiểm tra tên
	name = name.trim();
	if(name==""){
		validName = false;
		message = "Nhập tên để vào hệ thống";
	}else{
		// Kiểm tra dấu cách trong tên đăng nhập
		if(name.indexOf(" ")!=-1){
			validName = false;
			message = "Tên đăng nhập có dấu cách";
		}else if(name.length>100){
			// Tên đăng nhập quá dài
			validName = false;
			message = "Tên vào hệ thống quá dài, chỉ hỗ trợ dưới 100 ký tự";
		}else if(name.indexOf("@")!=-1){
			// có dấu hiệu hộp thư
			var pattern = /\w+@\w+[.]\w/;
			
			if(!name.match(pattern)){
				validName = false;
				message = "Không đúng cấu trúc hộp thư";
			}
		}
	}
	
	// Kiểm tra mật khẩu
	pass = pass.trim();
	if(pass==""){
		validPass = false;
		message += "\n Thiếu mật khẩu vào hệ thống";
	}else{
		if(pass.length<5){
			validPass = false;
			message += "\n Mật khẩu không hợp lệ";
		}
	}
	
	// Xuất thông báo nếu có
	if(message!=""){
		window.alert(message);
		if(!validName){
			fn.txtAccName.focus();
			fn.txtAccName.select();
		}else if(!validPass){
			fn.txtAccPass.focus();
			fn.txtAccPass.select();
		}
	}
	
	// Trả về kết quả kiểm tra
	return validName && validPass;
}


function login(fn) {
	if (this.checkValidLogin(fn)) {
		fn.method = "POST";// gọi vào doPOST
		fn.action ="/sys/user/login";// Đường dẫn thực thi của Servlet.
		fn.submit();
	}
}

