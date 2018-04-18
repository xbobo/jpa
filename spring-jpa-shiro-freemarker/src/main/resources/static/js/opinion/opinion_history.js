$(function() {
     page("");
     $(document).on("click",".changeEmotion",function(){
         var pnode=$(this).parent().parent();
         $(this).parent().find('.btn-success').each(function(index) {
           $(this).removeClass('btn-success');
         });
         $(this).addClass('btn-success');
         var index = $(this).parent().index();
         var preIndex=index-1;
         var temp=$(this).text();
         var pretemp=pnode.find("td:eq("+preIndex+")").text();
         var mactemp=pnode.find("td:eq("+(preIndex-1)+")").text();
         if(temp!=pretemp){
             var emotion=getEnglishEmotion(temp);
             var id=pnode.find("td:eq(0)").find("input").val();
              //修改情感 
              //if(confirm("您确定修改吗")){
                  var isSame=$(this).parent().find("input[name='isSame']").val();
                  if(mactemp==temp){
                      isSame=0;
                  }else if(mactemp=="正面"){
                      isSame=1;
                  }else if(mactemp=="负面"){
                      isSame=-1;
                  }else{
                      isSame=2;
                  }
                  changeEmotion(id,isSame,emotion,pnode,preIndex,temp,$(this));
              //}
         }
     });
     $(document).on("click",".search_button",function(){
         page($("#param").val());
     });
});

function page(param){
    var url="/opinionJudge/searchHistory.do";
    var curName=$('#curName').val();
    var params="{'curName':'"+curName+"','param':'"+param+"'}";
    $.ajax({
        url : url,
        dataType : "json",
        type : 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data : params,
        success : function(result) {
            $(".PageDiv").find("ul").remove();
            if(result.data!=null){
                var total=result.data.totalPages;
                var totalNum=result.data.totalElements;
                var pageNo=result.data.number+1;
                if(param==""){
                    $("#readNum").text(totalNum);
                }
                if(total>0){
                    $(".no-data").hide();
                    $(".table_div").show();
                    $(".PageDiv").append('<ul id="paging"></ul>');
                    pageInit('#paging',total,10,10,pageNo,pageCallback,result);
                }else{
                   showNoData();
                }
            }else{
                showNoData();
            }
        },
        error : function(result) {
        }
    });
}
function pageCallback(num,type,result){
    if(type=="change"){
         changeContent(num,$("#param").val(),$('#curName').val());
    }else{
        toSetTable(result,num);
    }
}
//分页查询
function changeContent(num,param,curName){
    $.ajax({
        url : "/opinionJudge/searchHistory.do",
        dataType : "json",
        type : 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data : "{'curName':'"+curName+"','pageNo':'"+num+"','param':'"+param+"'}",
        success : function(result) {
            toSetTable(result,num);
        },
        error : function(result) {
        }
    });
}

//修改情感
function changeEmotion(id,isSame,emotion,pnode,preIndex,temp,self) {
    $.ajax({
        url : "/opinionJudge/changeEmotion.do",
        dataType : "json",
        type : 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data : "{'id':'"+id+"','isSame':'"+isSame+"','emotion':'"+emotion+"'}",
        success : function(result) {
            if(result.data){
                 pnode.find("td:eq("+preIndex+")").text(temp);
                 self.parent().find('.hide').each(function(index) {
                   $(this).removeClass("hide");
                   $(this).addClass("show");
                 });
                 $(self).removeClass('show');
                 $(self).addClass('hide');
                 showSuccess('修改成功');
            }else{
                showSuccess('修改失败');
            }
            
        },
        error : function(result) {
        }
    });
}
function getEmotion(emotion){
    if(emotion=="positive"){
        return "正面";
    }else  if(emotion=="negative"){
        return "负面";
    }else if(emotion=="neture"){
        return "无关";
    }else{
        return "无关";
    }
}
function getEnglishEmotion(emotion){
    if(emotion=="正面"){
        return "positive";
    }else  if(emotion=="负面"){
        return "negative";
    }else if(emotion=="无关"){
        return "neture";
    }else{
        return "无关";
    }
}
function showNoData(){
    $(".no-data").show();
    $("table").hide();
    $(".PageDiv").hide();
}
function toSetTable(result,num){
     $("#paging").hide();
    if(result.data!=null){
        if(result.data.content!=null&&result.data.content.length>0){
            $(".no-data").hide();
            $("table").show();
            $("#paging").show();
            $("#table_tbody").find('tr').remove();
            var content=result.data.content;
            var html="";
            for(var x=0,len=content.length;x<len;x++){
                html+="<tr>";
                html+="<td style='text-align: center;'><input type='hidden' name='id' value='"+content[x].id+"'>"+((num-1)*10+x+1)+"</td>";
                if(content[x].content==null||content[x].content==""){
                     html+="<td></td>";
                }else{
                    html+="<td>"+content[x].content+"</td>";
                }
                var machine="";
                if(content[x].isSame=="0"){
                    machine=getEmotion(content[x].emotion);
                }else if(content[x].isSame=="1"){
                    machine="正面";
                }else if(content[x].isSame=="-1"){
                    machine="负面";
                }else{
                    machine="无关";
                }
                html+="<td style='text-align: center;'>"+machine+"</td>";
                var tempEmotion=getEmotion(content[x].emotion);
                html+="<td style='text-align: center;'>"+tempEmotion+"</td>";
                html+="<td style='text-align: center;'>";
                html+="<input type='hidden' name='isSame' value='"+content[x].isSame+"'>";
                if(tempEmotion=="正面"){
                    html+="<a href='javascript:void(0);' class='pointer changeEmotion hide'>正面</a>";
                }else{
                    html+="<a href='javascript:void(0);' class='pointer changeEmotion show'>正面</a>";
                }
                if(tempEmotion=="负面"){
                    html+=" <a href='javascript:void(0);' class='pointer changeEmotion hide'>负面</a>";
                }else{
                    html+=" <a href='javascript:void(0);' class='pointer changeEmotion show'>负面</a>";
                }
                if(tempEmotion=="无关"){
                    html+=" <a href='javascript:void(0);' class='pointer changeEmotion hide'>无关</a> ";
                }else{
                    html+=" <a href='javascript:void(0);' class='pointer changeEmotion show'>无关</a> ";
                }
                html+="</td>";
                html+="</tr>";
            }
            $("#table_tbody").append(html);
        }else{
            showNoData();
        }
    }else{
        showNoData();
    }
}

