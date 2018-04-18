$(document).ready(function(){
	//footer to bottom
	var rightHeight = ($(window).height() - 195);
	$('.right').css({'min-height' : rightHeight + 'px'});
	var noDataHeight = rightHeight - $('.select').height() - 86;
	$('.no-data').css({'height' : noDataHeight + 'px'});
	$('html, body').animate({scrollTop:0}, 'slow');
});
function showSuccess(msg) {
	if($('#show-success').length == 0){
		$('body').append("<div id='show-success'></div>");
	}
	$('#show-success').text(msg);
	$('#show-success').fadeIn('slow').fadeOut(3000);
}

function getEmotionByVal(emotion){
	switch(emotion){
		case '正面' : 
			return 'positive';
			break;
		case '中性' :
			return 'neutral';
			break;
		case '负面' : 
			return 'negative';
			break;
	}
}

function openLayer(content) {
	layer.open({
		type: 1, title: false,
		closeBtn: true, area: '600px',
		shade: 0.8, id: 'LAY_layuipro',
		resize: false , btnAlign: 'c',
		moveType: 1 , content: content
	});
}
