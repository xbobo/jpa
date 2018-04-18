$(function(){
	openOrClosePermission();//增加或者删除权限
    initPermission();//初始化权限
})
function initPermission(){
	 var permissionCodeList =$("#data").val();
		if(permissionCodeList !=""){
		var list = permissionCodeList.split(",");
			for(var i=0;i<list.length;i++){
				var dom = $("#"+list[i]);
				if(undefined != dom){
					$(dom).parent().removeClass("trackOff").addClass("trackOn");
				}
				
			}
			
		}
}
function openOrClosePermission(){
	$(".track").click(function(event) {
        if ($(this).children('i').css('left') === "3px") {
            $(this).css({
                background: '#4bd864'
            });
            savePermission(this,true);
            $(this).children('i').animate({ left: "26px" }, 300)
        } else if ($(this).children('i').css('left') === "26px") {
            $(this).css({
                background: '#fff'
            });
            savePermission(this,false);
            $(this).children('i').animate({ left: "3px" }, 300)
        };
    });
}
function savePermission(dom,isOopenPermission){
	var userAccount = $("#userAccount").val();
	var permissionCode = $(dom).parent().find("input").val();
	var url ="";
	if("" != permissionCode && undefined!=permissionCode){
		if(isOopenPermission){//开启权限
			url="/judgeUserPermission/saveUserPermission.do";
		}else{//关闭权限
			url ="/judgeUserPermission/deleteUserPermission.do";
		}
		$.ajax({
				url:url,
				type:"post",
				data:{"userAccount":userAccount,"permissionCode":permissionCode},
				dataType:"json",
				success:function(result){
					if(!result.success){
						alert("更新失败");
					}
				},
				error:function(e){
					alert("更新失败");
				}
			});
	}
}
function checkUserPermission(dom){
	var userAccount = $("#userAccount").val();
	var permissionCode = $(dom).parent().prev().find("input").val();
	$.ajax({
		url:"/judgeUserPermission/checkUserPermission.do",
		type:"post",
		data:{"userAccount":userAccount,"permissionCode":permissionCode},
		dataType:"json",
		success:function(result){
			if(result.success){
				window.open("/judgeUser/getJudgeUserData.do?userAccount="+userAccount+"&permissionCode="+permissionCode,"_self");
			}
		},
		error:function(e){
			console.log(e);
			alert(e);
		}
	});
}
