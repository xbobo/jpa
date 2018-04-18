 //将表单序列化成json格式的数据(但不适用于含有控件的表单，例如复选框、多选的select)
(function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);

function publicInit(){
     $(document).on("click",".select-tab>span",function(){
        $(".span-active").removeClass('span-active');
        $(this).addClass('span-active');
    });
    $(document).on("click",".pointer",function(){
        var pnode=$(this).parent().parent();
        var initemotion=pnode.find("input[name='initemotion']").val();
         
         if($(this).hasClass(initemotion)){
             pnode.find("input[name='isSame']").val("0");
             pnode.find("input[name='emotion']").val(initemotion);
         }else{
             if($(this).hasClass("positive")){
                pnode.find("input[name='emotion']").val("positive");
                pnode.find("input[name='isSame']").val("1");
             }
             if($(this).hasClass("negative")){
                pnode.find("input[name='emotion']").val("negative");
                pnode.find("input[name='isSame']").val("-1");
             }
             if($(this).hasClass("neutral")){
                pnode.find("input[name='emotion']").val("neutral");
                pnode.find("input[name='isSame']").val("2");
             }
             if($(this).hasClass("pass")){
                pnode.find("input[name='isSame']").val("0");
                pnode.find("input[name='emotion']").val(initemotion);
             }
         }
         pnode.find("input[name='isJudged']").val("1");
         $(this).text("✔").siblings().trigger("unchecked");
    });
    $(document).on("unchecked",".normal-btn",function(){
        var $this = $(this);
        if($this.hasClass('positive'))
            $this.text('正面');
        if($this.hasClass('negative'))
            $this.text('负面');
        if($this.hasClass('neutral'))
            $this.text('无关');
        if($this.hasClass('pass'))
            $this.text('通过');
    });
}
 
 // 数据提交
function toSubmit(url) {
    var jsonStr=$("#formData").serializeJson();
    var curName=$("#curName").val();
   
    $.ajax({
        url : "/opinionJudge/updates.do",
        dataType : "json",
        type : 'post',
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data :  jsonStr,
        success : function(result) {
             //刷新页面
            openPublic(url+"?curName="+$("#curName").val(),"_self");
        },
        error : function(result) {
        }
    });
}

function addNoData(container, msg) {
    $(container).html("<div class='no-data'><span class='glyphicon glyphicon-info-sign'>&nbsp;<font>" + msg + "</font></span></div>");
}
function openPublic(url,type){
   // window.location.href="opinionJudgeManagement.do?pageNo="+num;
   //window.open("/opinionJudge/opinionJudgeManagement.do?pageNo="+num,"_self"); 
   window.open(url,type); 
}