<div class="left">
	<ul class="left-ul">
		<@shiro.hasRole name="操作员">
			<li class="left-normal">
				<a href="#">舆情数据研判</a>
			</li>
			<li class="left-normal sub-li <#if menu=1>selected</#if>">
				<a href="/opinionJudge/getJudgeData.do" class="sub-menu">研判列表</a>
			</li>
			<li class="left-normal sub-li <#if menu=2>selected</#if>">
				<a href="/opinionJudge/getJudgeDataHistory.do" class="sub-menu">研判历史</a>
			</li>
			<li class="left-normal">
				<a href="#">产品数据研判</a>
			</li>
			<li class="left-normal sub-li <#if menu=3>selected</#if>">
				<a href="/productJudge/getJudgeData.do" class="sub-menu">研判列表</a>
			</li>
			<li class="left-normal sub-li <#if menu=4>selected</#if>">
				<a href="/productJudge/getJudgeHistory.do" class="sub-menu">研判历史</a>
			</li>
		</@shiro.hasRole>
		<@shiro.hasRole name="管理员">
			<li class="left-normal sub-li <#if menu=5>selected</#if>">
				<a href="/opinionJudge/opinionJudgeManagement.do" >舆情数据研判</a>
			</li>
			<li class="left-normal sub-li <#if menu=6>selected</#if>">
				<a href="/productJudge/getJudgeMgmt.do" >产品数据研判</a>
			</li>
			<li class="left-normal sub-li <#if menu=7>selected</#if>">
				<a href="/userBase/openAccountListPage.do">用户管理</a>
			</li>
		</@shiro.hasRole>
	</ul>
</div>