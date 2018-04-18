function pageInit(pageId,totalPages,pageSize,visiblePages,currentPage,callback,result){
    if(pageId==null){
        pageId="#paging";
    }
    if(pageSize==null){
        pageSize=10;
    }
    if(visiblePages==null){
        visiblePages=10;
    }
    if (typeof callback === "function"){
        $.jqPaginator(
            pageId, {
            totalPages: totalPages,
            pageSize:pageSize,
            visiblePages: visiblePages,
            currentPage: currentPage,
            //first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
            prev: '<li class="prev"><a href="javascript:void(0);"><sapn ><</sapn><\/a><\/li>',
            next: '<li class="next"><a href="javascript:void(0);"><span >></span><\/a><\/li>',
            //last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
            page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
            onPageChange: function(num, type) {
               callback(num,type,result);
            }
        }); 
    }
}
