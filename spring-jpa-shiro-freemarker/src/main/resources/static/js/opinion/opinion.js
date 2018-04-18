$(function() {
    publicInit();
    var len=$(".content-detail").length;
    if(len>0){
        $(".no-data").hide();
        //开始计时
        startTimer();
    }else{
        stopTimer();
        $(".submit-div").hide();
        $(".no-data").show();
    }
    
});

//计时
var t_init;
function stopTimer() {
    clearInterval(t_init);
}
function startTimer() {
    t_init = setInterval("task_init()", 1000);
}
function task_init(){
    var text=$(".span_timer").text();
    if(text==0){
        $(".span_timer").text("");
        stopTimer();
        //自动提交 
        toSubmit("/opinionJudge/getJudgeData.do");
        
    }else{
        $(".span_timer").text(text-1);
    }
}


