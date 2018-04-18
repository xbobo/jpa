function saveAccountFun(){
	var data={};
	data.userAccount=$("#userName").val();
	data.password=$("#password").val();
	data.realName=$("#realName").val();
	data.roleName=$("#roleName").val();
	$.ajax({
		url:"/userBase/saveAccount.do",
		type:'POST',
		data:data,
		dataType:'json',
		success:function(result){
			if(result.success){
				window.open("/userBase/openAccountListPage.do","_self");
			}else{
				alert(result.errorMsg);
				return;
			}
		},
		error:function(e){
			alert("保存失败");
			return;
		}
	});
}

function cancelAccountFun(){
	window.open("/userBase/openAccountListPage.do","_self");
}