//解决裂图或者图片加载失败处理方式
function imgError(obj){
	obj.src = basePath+"/img/default.jpg";
};

//大图片处理方式
function imgError2(obj){
	obj.src = basePath+"/img/default-a.jpg";
};

//空判断 
function isEmpty(content){
	return content==null || content=="" || content==undefined;
};

//非空判断
function isNotEmpty(content){
	return !isEmpty(content)
};

/*弹出框*/
function loading(content,timeout){
	var $loading = $("<div class='mkloading animated bounceInLeft'>"+
		"<span>"+content+"</span>"+
	"</div>");
	$("body").append($loading);
	var width = $loading.width();
	var height = $loading.height();
	$loading.css({marginLeft:(-width/2),marginTop:(-height/2)});
	$loading.on("click",function(){
		$(this).removeClass("animated bounceInLeft")
		.addClass("animated bounceOutRight")
		.fadeOut("slow",function(){
			$(this).remove();
			if($loading.timer)clearInterval($loading.timer);
	});
});

function dialog(title,content,callback){
				var $dialog = $("<div class='modal' tabindex='-1' style='display: block;'>"+
				" <div class='modal-dialog' role='document'>"+
				"   <div class='modal-content'>"+
				"     <div class='modal-header'>"+
				"       <button type='button' class='close js-closebtn'><span>&times;</span></button>"+
				"       <h4 class='modal-title'>"+title+"</h4>"+
				"     </div>"+
				"     <div class='modal-body'>"+
				"       <p>"+content+"</p>"+
				"     </div>"+
				"     <div class='modal-footer'>"+
				"       <button type='button' class='btn btn-default js-closebtn'>关闭</button>"+
				"       <button type='button' class='btn btn-primary js-surebtn'>确定</button>"+
				"     </div>"+
				"   </div>"+
				" </div>"+
				"</div>");
				
				var $overlay = $("<div class='modal-backdrop fade in'></div>");
				$("body").append($dialog).append($overlay);
				
				//绑定点击事件
				$dialog.find(".js-surebtn").on("click",function(){
					//删除用户
					if(callback)callback(true);
					$dialog.fadeOut("slow",function(){
						$(this).next().remove();
						$(this).remove();
					});
				});
				
				//关闭
				$dialog.find(".js-closebtn").on("click",function(){
					//删除用户
					if(callback)callback(false);
					$dialog.fadeOut("slow",function(){
						$(this).next().remove();
						$(this).remove();
					});
				});
			}