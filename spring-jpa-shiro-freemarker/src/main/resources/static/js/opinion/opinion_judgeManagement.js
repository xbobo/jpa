$(function() {
    $(".no-data").hide();
    // 如果没有数据
    var len=$("#table_tbody>tr").length;
    if(len>0){
         $("#paging").show();
    }else{
        $("#paging").hide();
        showNoData();
    }
     page("");
     // 查看 详情
     $(document).on("click",".check_details",function(){
          var id=$(this).parent().parent().find('td:eq(0)').children("input").val();
           $.ajax({
            url : "/opinionJudge/opinionJudgeCheck.do",
            dataType : "json",
            type : 'post',
            contentType : 'application/json;charset=utf-8', //设置请求头信息
            data :  "{'id':'"+id+"'}",
            success : function(result) {
                var html="";
                 if(result!=null&&result.data.length>0){
                     for(var x=0,len=result.data.length;x<len;x++){
                         html+='<tr>'+
                                    '<td style="text-align: center;" >'+result.data[x].name+'</td>'+
                                    '<td style="text-align: center;" >'+result.data[x].todayCount+'</td>'+
                                    '<td style="text-align: center;" >'+result.data[x].count+'</td>'+
                                '</tr>';
                     }
                 }
                 checkLayer(html);
            },
            error : function(result) {
            }
        });
     });
    // 查看 编辑
     $(document).on("click",".check_edit",function(){
         // 编辑
         var id=$(this).parent().parent().find('td:eq(0)').children("input").val();
         var selName=$(this).parent().parent().find('td:eq(0)').text();
         //console.log("研判员",id+"_"+selName);
         //页面跳转舆情研判账号修改页面
         openPublic("/judgeUser/getJudgeUserData.do?userAccount="+selName+"&permissionCode=101","_self");
     });
});
function checkLayer(html){
    layer.open({
          type: 1
          ,title: false //不显示标题栏
          ,closeBtn: true
          ,area: '600px'
          ,shade: 0.8
          ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
          ,resize: false 
          ,btnAlign: 'c'
          ,moveType: 1 //拖拽模式，0或者1
          ,content: 
          '<div style="padding: 10px; line-height: 22px;  font-weight: 300;margin:0;" class="content">'+
            '<table class="table table-striped table-bordered table-hover">'+
                 '<thead>'+
                    '<tr>'+
                    '<th style="text-align: center;" >待研判用户</th>'+
                    '<th style="text-align: center;" >今日研判量</th>'+
                    '<th style="text-align: center;" >总研判量</th>'+
                    '</tr>'+
                '</thead>'+
                '<tbody >'+ html+'</tbody></table>'+
          '</div>'
        });
}
function page(param){
    var total=$("#totalPages").val();
    total=parseInt(total.replace(new RegExp(",","gm"),""));
    var pageNo=$("#pageNo").val();
    pageNo=parseInt(pageNo.replace(new RegExp(",","gm"),""));
    if(total>0){
        pageInit('#paging',total,10,10,pageNo,pageCallback);
    }
}
function pageCallback(num,type){
    if(num!=undefined&&num!=parseInt($("#pageNo").val())&&pageNo!=1000){
       openPublic("/opinionJudge/opinionJudgeManagement.do?pageNo="+num,"_self");
    }
}
function showNoData(){
    $(".no-data").show();
    $(".table").hide();
}