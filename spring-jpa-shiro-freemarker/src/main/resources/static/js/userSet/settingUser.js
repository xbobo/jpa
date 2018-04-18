$(function() {
	initSetingSelect();//初始化设置选择框
});
function initSetingSelect(){
	var notAssigned =eval('(' + $("#notAssigned").val() + ')');
	var assign = eval('(' + $("#assign").val() + ')');
	var reverse = $("#reverse").val();
	var permissionCode = $("#permissionCode").val();
	$($("#setingSelect").magicSuggest({
		placeholder : '',
		maxSelection : 10,
		maxSelectionRenderer : function(v) {
			return "";
		},
		value : assign,
		data : notAssigned
	})).on("selectionchange", function(e, cb, s) {
		var match = this.getValue();
		if ("" != match && match.length > 0) {
			$("#setVal").val(match);
		} else {
			$("#setVal").val("");
		}
	});
}
function saveSetting() {
	var userAccount = $("#userAccount").val();
	var setVal = $("#setVal").val();
	var permissionCode = $("#permissionCode").val();
	if (userAccount == "") {
		alert("用户不能为空");
		return;
	}
	if (permissionCode == "") {
		alert("类型不能为空");
		return;
	}
	$.ajax({
			url : "/judgeUser/saveSetting.do",
			data : {
				"userAccount" : userAccount,
				"setVal" : setVal,
				"permissionCode" : permissionCode
			},
			type : "post",
			success : function(result) {
				if (result.success) {
					alert("保存成功");
				}
			},
			error : function() {
				alert("保存失败");
			}
	});
}