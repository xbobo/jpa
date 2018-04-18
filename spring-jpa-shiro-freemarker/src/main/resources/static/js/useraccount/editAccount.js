function saveAccountFun(){
	var data={};
	data.id=$("#id").val();
	data.userAccount=$("#userName").val();
	data.realName=$("#realName").val();
	data.roleName=$("#roleName").val();
	$.ajax({
		url:"/userBase/updateUserById.do",
		type:"POST",
		data:data,
		dataType:"json",
		success:function(result){
			if(result.success){
				window.open("/userBase/openAccountListPage.do","_self");
			}else{
				alert(result.errorMsg);
			}
		},
		error:function(e){
			alert("更新失败");
		}
	});
	
}

function cancelAccountFun(){
	window.open("/userBase/openAccountListPage.do","_self");
}

function resetPassword(id){
	$.ajax({
		url:"/userBase/resetPassword.do",
		type:"POST",
		data:{"id":id},
		dataType:"json",
		success:function(result){
			if(result.success){
				alert("密码重置成功");
			}else{
				alert(result.errorMsg);
				return;
			}
		},
		error:function(){
			alert("重置密码失败");
			return;
		}
	});
}