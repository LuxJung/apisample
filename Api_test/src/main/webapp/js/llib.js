$(function() {
	getJson();
}
);

function getJson() {

var json =  $(".content");
alert(json);

	$.getJSON(json, function(data) {
		alert(json+"aowgh buie byi");
		$.each(data, function(key, val) {
			if (key == "RESULT") {
				$("table").attr("border", "1");
				$("thead").append(
					"<tr>" +
					"<th>" + "도서관명" + "</th>" +
					"<th>" + "도서관주소" + "</th>" +
					"<th>" + "도서관 전화번호" + "</th>" +
					"<th>" + "val.OP_TIME" + "</th>" +
					"<th>" + "val.HMPG_URL" + "</th>" +
					"</td>"
				);

			} else {
				var list = val;
				for (var i = 0; i < list.length; i++) {
					var str = list[i];
					$("tbody").append(
						"<tr>" +
						"<td>" + str.lbrry_name + "</td>" +
						"<td>" + str.adres + "</td>" +
						"<td>" + str.tel_no + "</td>" +
						"<td>" + str.op_time + "</td>" +
						"<td>" + str.hmpg_url + "</td>" +
						"</td>"
					);
				}
			}

		});
	});
}