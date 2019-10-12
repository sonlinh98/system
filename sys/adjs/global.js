function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function generaNumberViewOnPage(name) {
	var result = "";
	var number = getCookie(name);
	if (number !== "") {
		result += "<div class=\"wrapPageNumber\">";
		result += "<span>Hiển thị </span>";
		result += "<select type=\"text\" >";

		for (let i = 1; i < 5; i++) {
			if ((i * 5) == number) {
				result += "<option value=\"" + i + "\" selected >" + (i * 5)
						+ "</option>";
			} else {
				result += "<option value=\"" + i + "\">" + (i * 5)
						+ "</option>";
			}
		}

		result += "</select>";
		result += "</div>";
	} else {
		result += "<div class=\"wrapPageNumber\">";
		result += "<span>Hiển thị </span>";
		result += "<select type=\"text\" >";

		for (let i = 1; i < 5; i++) {
			result += "<option value=\"" + i + "\">" + (i * 5) + "</option>";
		}

		result += "</select>";
		result += "</div>";
	}
	return result;
}