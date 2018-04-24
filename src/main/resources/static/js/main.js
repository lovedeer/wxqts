$(function() {
	var linkLists = [{
		'title': '特种设备监察平台',
		'icon': 'images/1.png',
		'iconHover': 'images/a.png',
		'a_href': 'http://58.215.62.147:8067'
	}, {
		'title': '特种设备检验平台',
		'icon': 'images/2.png',
		'iconHover': 'images/b.png',
		'a_href': 'http://58.215.62.147:8066'
	}, {
		'title': '电梯维保平台',
		'icon': 'images/3.png',
		'iconHover': 'images/c.png',
		'a_href': 'http://58.215.62.147:8064'
	}, {
		'title': '企业服务平台',
		'icon': 'images/4.png',
		'iconHover': 'images/d.png',
		'a_href': 'http://58.215.62.147:8063'
	}, {
		'title': '决策分析平台',
		'icon': 'images/5.png', 
		'iconHover': 'images/e.png',
		'a_href': 'http://58.215.62.147:8065/WebReport/ReportServer?op=fs_load&cmd=fs_signin'
	}];
	var li = '';
	for(var i = 0; i < linkLists.length; i++) {
		li += '<li class="li l' + i + '">' + '<a href="' + linkLists[i]['a_href'] + '" target="_blank"><img src="' + linkLists[i]['icon'] + '" /><p class="titles">'+linkLists[i]['title'] +'</p></a>' + '</li>';
	}
	var ul = '<ul id="list" class="clearfix">' + li + '</ul>';
	$('.row').append(ul);
	$('#list>li').mousemove(function() {
		var t = $(this).index();
		var src = linkLists[t]['iconHover'];
		$(this).children('a').children('img').attr('src', src);
		$(this).children('a').children('p').css('color', '#ECA253');
	})
	$('#list>li').mouseleave(function() {
		var t = $(this).index();
		var src = linkLists[t]['icon'];
		$(this).children('a').children('img').attr('src', src);
		$(this).children('a').children('p').css('color', '#fff');
	})
	if(document.all) {
		$('.container').css({
			'width': '60%',
			'margin': '0 auto'
		})
	} else {
	}
	$('#sub').click(function(){
		var unitName = $('.form-group>input').val();
		$.ajax({
			url:'/succ/query',
			type:'get',
			data:{
				'unitName':unitName
			},
			success:function(data){
				var content="";
				for(var gsj in data){ //第二层循环取list中的对象 
					content += "单位名称："+data[gsj].mc+"<br/>"; 
					content += "社会统一信用代码："+data[gsj].shxydm+"<br/>"; 
					content += "组织机构代码："+data[gsj].zch+"<br/>"
					content += "<br/>";
				} 
				$('#res').html("<p><font size=2px>"+content+"</font></p>");
			},
			error:function(err){
				$('#res').text("query error!");
			},
		})
		
	})
})