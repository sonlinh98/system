// Các xử lý kịch bản cho user.html

// sinh cấu trúc quyền thực thi trên giao diện
function generatePermis(permisCurrent) {
	// Danh sách quyền
	var permis = new Array();
	permis[0] = "-- Chọn quyền --";
	permis[1] = "Thành viên";
	permis[2] = "Tác giả";
	permis[3] = "Quản lý";
	permis[4] = "Quản Trị";
	permis[5] = "Quản trị cấp cao";

	// Khai báo biến lưu trữ cấu trúc html
	var opt = "<select name=\"slcAccPermis\" onchange=\"refreshPermis(this.form)\" >";
	for (var i = 0; i < permis.length; i++) {
		opt += "<option value=\"" + i + "\" "
				+ (permisCurrent == i ? "selected" : "") + " >";
		opt += permis[i];
		opt += "</option>";
	}
	opt += "</select>";

	// Xuất cấu trúc html
	document.write(opt);

}

// Sinh cấu trúc vai trò thực thi
function generateRoles() {
	// Danh sách vai trò
	var roles = new Array();
	roles[0] = "Người sử dụng (User)";
	roles[1] = "Chuyên mục (Section)";
	roles[2] = "Thể loại (Category)";
	roles[3] = "Bài viết & Tin tức (Article)";
	roles[4] = "Hệ sản phẩm (Product System)";
	roles[5] = "Nhóm sản phẩm (Product Group)";
	roles[6] = "Loại sản phẩm (Product Category)";
	roles[7] = "Sản phẩm (Product)";
	roles[8] = "Hóa đơn (Oder)";
	roles[9] = "Khách hàng (Customer)";
	roles[10] = "Lịch sửa cập nhật (Log)";

	// Khai báo biến lưu trữ html
	var role = "";
	for (var i = 0; i < roles.length; i++) {
		if (i % 3 == 0) {
			role += "<div class=\"row roles\">";
		}

		role += "<div class=\"c4\">";
		role += "<input type=\"checkbox\" name='chks' value=\"" + i
				+ "\" id=\"chk" + i + "\" disabled />";
		role += "<label for=\"chk" + i + "\">Quản lý " + roles[i] + "</label>";
		role += "</div>";

		if (i % 3 == 2 || i == roles.length - 1) {
			role += "<div class=\"clr\"></div>";
			role += "</div>";
		}
	}

	// Xuất cấu trúc
	document.write(role);

}

// Hàm thiết lập trạng thái của checkbox
function setCheckBox(fn, dis, check) {
	// Duyệt tất cả các phần tử của form
	for (var i = 0; i < fn.elements.length; i++) {
		// Tìm các checkbox
		if (fn.elements[i].type == "checkbox" && fn.elements[i].name == "chks") {
			fn.elements[i].disabled = dis;
			fn.elements[i].checked = check;
		}
	}
}

// Hàm thay đổi giá trị của Quyền thực thi
function refreshPermis(fn) {
	// Lấy giá trị của quyền thực thi
	var permis = parseInt(fn.slcAccPermis.value);

	// Kiểm tra
	if (permis == 4 || permis == 5) {
		this.setCheckBox(fn, true, true);
	} else if (permis == 3) {
		this.setCheckBox(fn, false, true);
	} else if (permis == 2) {
		this.setCheckBox(fn, false, false);
	} else {
		this.setCheckBox(fn, true, false);
	}

}

// thiết lập chế độ ban đầu của giao diện
function setFirstTime(fn) {
	this.setCheckBox(fn, true, false);
	fn.reset();
}

// Kiểm tra toàn thể giao diện để thực hiện thêm người sử dụng
function checkValidUser(fn) {

	// Lấy thông tin bắt buộc của tài khoản
	var name = fn.txtAccName.value;
	var pass = fn.txtAccPass.value;
	var passConfirm = fn.txtAccPassConfirm.value;
	var email = fn.txtAccEmail.value;
	var permis = parseInt(fn.slcAccPermis.value);

	// Biến xác nhận sự hợp lệ
	var validName = true;
	var validPass = true;
	var validEmail = true;
	var validPermis = true;

	// Biến ghi nhận kết quả kiểm tra
	var message = "";

	// Kiểm tra name
	name = name.trim();
	if (name == "") {
		validName = false;
		message = "Cần có tên cho tài khoản.";
	} else {
		if (name.indexOf(" ") != -1) {
			validName = false;
			message = "Có dấu cách trong tài khoản.";
		} else if (name.length > 100) {
			validName = false;
			message = "Tên tài khoản quá dài, nên nhỏ hơn 100 ký tự.";
		}
	}

	// Kiểm tra pass
	pass = pass.trim();
	if (pass == "") {
		validPass = false;
		message += "\nThiếu mật khẩu cho tài khoản.";
	} else {
		if (pass.length < 6) {
			validPass = false;
			message += "\nMật khẩu quá ngắn, cần lớn hơn 5 ký tự.";
		} else {
			if (pass.length != passConfirm.length || pass != passConfirm) {
				validPass = false;
				message += "\nMật khẩu xác nhận không khớp.";
			}
		}
	}

	// Kiểm tra email
	email = email.trim();
	if (email == "") {
		validEmail = false;
		message += "\n Thiếu hộp thư vào hệ thống.";
	} else {
		var pattern = /\w+@\w+[.]\w/;
		if (!email.match(pattern)) {
			validEmail = false;
			message += "\n Không đúng cấu trúc hộp thư."
		}
	}

	// Kiểm tra vai trò cho 2 quyền tác giả và quản lý
	if (permis == 2 || permis == 3) {
		for (var i = 0; i < fn.elements.length; i++) {
			if (fn.elements[i].type == "checkbox"
					&& fn.elements[i].name == "chks") {
				if (fn.elements[i].checked) {
					validPermis = true;
					break;
				} else {
					validPermis = false;
				}
			}
		}
	}
	if (!validPermis) {
		message += "\n Cần ít nhất 1 vai trò cho cái quyền này.";
	}

	// Xuất thông báo
	if (message != "") {
		window.alert(message);
		if (!validName) {
			fn.txtAccName.focus();
			fn.txtAccName.select();
		} else if (!validPass) {
			fn.txtAccPass.focus();
			fn.txtAccPass.select();
		} else if (!validEmail) {
			fn.txtAccEmail.focus();
			fn.txtAccEmail.select();
		} else if (!validPermis) {
			fn.slcAccPermis.focus();
		}
	}

	// Trả về kết quả kiểm tra
	return validName && validPass && validEmail && validPermis;

}

function saveUser(fn) {
	if (this.checkValidUser(fn)) {
		fn.method = "POST";
		fn.action = "/sys/user/ae";
		fn.submit();
	}
}

function confirmDel(url) {
	var message = "Bạn có thực sự muốn xóa người sử dụng ?";
	if (window.confirm(message)) {
		window.location.href = url;
	} else {
		return false;
	}
}

function changeNumberViewOnPage() {
	
}

