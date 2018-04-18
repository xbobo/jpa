var GB_Page_Size=10;
var visiblePages=10;

$(function(){
	showList(1);
});

function pageCallback(num,type,result){
	if(type=="change"){
		showList(num);
   }else{
      
   }
}

function showList(currentPage){
	$(".mainContent-table>table>tbody").find('tr').remove();
	var data={};
	data.currentPage=currentPage-1;
	data.pageSize=GB_Page_Size;
	data.userAccount=$("#userAccount").val();
	data.roleName=$("#roleName").val();
	data.createUser=$("#createUser").val();
	$.ajax({
		url:"/userBase/getUserBasePage.do",
		data:data,
		type:"POST",
		dataType:"json",
		success:function(result){
			if(result.success){
				var totalPage=result.data.totalPage;
				if(result.data.userAccountList.length>0){
					var res=result.data.userAccountList;
					var html="";
					for(var i=0;i<res.length;i++){
						html+="<tr>"+
							"<td>"+(i+1)+"</td>"+
							"<td>"+res[i].userAccount+"</td>"+
							"<td>"+res[i].roleName+"</td>"+
							"<td>"+res[i].createDateStr+"</td>"+
							"<td>"+res[i].createUser+"</td>"+
							"<td>"+
								"<a href='/userBase/findUserById.do?id="+res[i].id+"'><img src='/img/edit.png' title='编辑'></a>"+
								"<a onclick='deleteUserFun("+res[i].id+")'><img src='/img/delete.png' title='删除'></a>"+
								"<a href='/judgeUserPermission/getUserPermissiontData.do?userAccount="+res[i].userAccount+"'><img src='/img/backSet.png' title='删除'></a>"+
							"</td>"
						"</tr>";
					}
					$(".mainContent-table>table>tbody").append(html);
				}
				if(totalPage>0){
					$(".pagging").show();
					pageInit('.pagination',totalPage,GB_Page_Size,visiblePages,currentPage,pageCallback,result);
				}else{
					$(".pagging").hide();
				}
			}else{
				
			}
		},
		error:function(e){
			
		}
	});
}

$(document).on('click','#addAccount',function(){
	window.open("/userBase/openAddAccount.do","_self");
});

function editAccountFun(id){
	window.open("/userBase/findUserById.do?id="+id,+"_self");
}

function deleteUserFun(id){
	if(confirm("确定要删除吗？")){
		$.ajax({
			url:"/userBase/deleteUserById.do",
			type:"POST",
			data:{"id":id},
			dataType:"json",
			success:function(result){
				if(result.success){
					window.open("/userBase/openAccountListPage.do","_self");
				}else{
					alert("删除失败");
					return;
				}
			},
			error:function(){
				alert("删除失败");
				return;
			}
		});
	}
}