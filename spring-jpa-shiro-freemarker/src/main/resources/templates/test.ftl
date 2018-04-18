<#assign menu=5/>
<!DOCTYPE html> 
<html lang="en">
		<#include "/public.ftl"/>
		<link rel="stylesheet" href="/css/public.css">
		<link rel="stylesheet" href="/css/opinion.css">
		<link rel="stylesheet" href="/css/page.css">
	</head>
	<body>
		<#include "/header.ftl"/>
		<script src="/lib/jqPaginator/jqPaginator.js"></script>
		<script src="/lib/lay/layer/layer.js"></script>
		<script src="/js/opinion/opinion_public.js"></script>
		<script src="/js/common/page.js"></script>
		<script src="/js/opinion/opinion_judgeManagement.js"></script>
		<section class="section">
			<#include "/left.ftl"/>
			<div class="right">
				<div class="title">
					<h3 class="">舆情数据研判</h3>
				</div>
				<div class="select">
					<input type="hidden" id="totalPages" name="totalPages" value="">
					<input type="hidden" id="pageNo" name="pageNo" value="">
					<div class="tab"  style="border-bottom: 0px;">
						<span class="tab-left">
							 
						</span>
						<span class="tab-right">
							共<font></font>个用户
						</span>
					</div>
				</div>
				<div class="content">
				 	<table cellspacing="0" cellpadding="0">
				 	 <thead>
				 		<tr>
				 		<th style="width:15%;text-align: center;" >研判员</th>
				 		<th style="width:55%;text-align: center;" >待研判用户</th>
				 		<th style="width:20%;text-align: center;" >研判时间</th>
				 		<th style="width:10%;text-align: center;" >操作</th>
				 		</tr>
				 	</thead>
				 	<tbody id="table_tbody">
				 		 
				 	</tbody>
				 	</table>
				 <div class="PageDiv clearfix">  
						<ul id="paging"></ul>
				 </div>
				 <div class="no-data">
							<div>暂无数据</div>
				  </div>
				</div>
				<div class="divide-line"></div>
			</div>
		</section>
		<#include "/footer.ftl"/>
	</body>
</html>