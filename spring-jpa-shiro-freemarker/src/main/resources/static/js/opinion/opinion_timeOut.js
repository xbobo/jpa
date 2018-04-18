$(function() {
    publicInit();
    page();
    var len=$(".content-detail").length;
    if(len>0){
        $(".push_switch").parent().show();
        $(".no-data").hide();
         $("#paging").show();
        $(".submit-div").show();
        $(".PageDiv").show();
    }else{
        $(".push_switch").parent().hide();
        $("#paging").hide();
        $(".submit-div").hide();
        $(".PageDiv").hide();
        $(".no-data").show();
    }
    $(document).on("click",".push_switch",function(){
        if(confirm("您确定一键推送？")){
            pushSwitch();
        }
    });
});

function page(){
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
        openPublic("/opinionJudge/getTimeOutJudgeData.do?curName="+$("#curName").val()+"&pageNo="+num,"_self");
    }
}

function pushSwitch() {
    $.ajax({
        url : "pushSwitch.do",
        dataType : "json",
        type : 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data : "{'curName':'"+$("#curName").val()+"'}",
        success : function(result) {
             //刷新页面
            openPublic("/opinionJudge/getTimeOutJudgeData.do?curName="+$("#curName").val(),"_self");
        },
        error : function(result) {
        }
    });
}

