
(function($){

/* ============ 机房预约 ============= */
$("body").on("click", ".bookable", function() {
	var bookingData = $(this).data();
	var groupName = $(this).parents("table").data("tableName");
	var startDate = new XDate();
	var timeStr = getBookingTime(bookingData, startDate);
	
	var maxNum = bookingData.maxNum;
	var orderpcid = bookingData.pcid;
//	修改后防止添加冲突
	var id = orderpcid + "_"+bookingData.index;
	var orderpcname = bookingData.pcname;
	var orderdate = bookingData.date;
	var orderperiod = bookingData.slot;
	var picpath =  bookingData.pcpicpath;
	var groupHtml = 
		'<div class="post-equip-group" data-name="' + groupName + '" >' +
			'<div class="group-hd">' +
				'<div class="group-name pull-left">' + groupName + '</div>' +
				'<div class="group-line"><hr></div>' +
			'</div>' +
			'<div class="group-body">' +
			'</div>'+
		'</div>';
	var html = 
	  '<div class="row" id=' + id + '>' + 
		  '<div class="item-name">' + timeStr + '</div>' +
		  '<div class="item-ctrl spinner input-group">' +
			  '<span class="input-group-btn">' + 
				  '<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
			  '</span>' + 
			  '<input name="pcorderinfo" orderpcid="'+orderpcid+'" orderdate="'+orderdate+'" orderperiod="'+orderperiod+'" orderpcname="'+orderpcname+'" picpath="'+picpath+'"  type="text" class="spinner-input form-control">' + 
			  '<span class="input-group-btn">' + 
				  '<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
			  '</span>' + 
			  '<span class="input-group-btn">' + 
				  '<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
			  '</span>' + 
		  '</div>' + 
	  '</div>';
		  
	$(this).toggleClass("selected");
	if($(this).hasClass("selected")) {		// 如果选中
		$(this).append('<i class="icon-ok"></i>');
		if ($("#rent-list #" + id).length > 0) {
			alert(timeStr + "已添加！");
		} else {
			var isGroupExist = false;
			$("#rent-list > .post-equip-group").each(function( index ){
				if(($(this).data("name")) == groupName) {		// 组存在，该组的新预约加入组中
					isGroupExist = true;
					event.stopImmediatePropagation();
				}							
			});
			if(!isGroupExist) {		// 组不存在，创建组
				$("#rent-list").append(groupHtml);
			}
			$("#rent-list > .post-equip-group").each(function(){
				
				if(($(this).data("name")) == groupName) {		// 组存在，该组的新预约加入组中
					$(this).children(".group-body").append(html);
					$("#rent-list #" + id).children(".spinner").spinner({max: maxNum});
					$("#rent-list #" + id).children(".spinner").spinner('value', 1);
					isGroupExist = true;
					event.stopImmediatePropagation();
				}							
			});
			//$("#rent-list").append(html);
								
//				$("#rent-list #" + eId).children(".spinner").spinner({max: maxNum});		// 设置设备最大可预约数量
//				$("#rent-list #" + eId).children(".spinner").spinner('value', eSelectNum);
//				$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
//					if($(this).spinner('value') === maxNum) {
//						$(this).on("click", ".spinner-up",function(){
//							alert("已达最大可预约数量！");
//						});
//					} else {
//						$(this).off("click", ".spinner-up");
//					}
//					event.stopImmediatePropagation();
//				});
			
//				if ($("#rent-list .row").length > 0) {
//					$("#rent-list .no-add").hide("fast", function() {
//						reWizardHeight();
//					});
//				} else {
//					$("#rent-list .no-add").show("fast", function() {
//						reWizardHeight();	
//					});
//				}

			if ($("#rent-list .row").length > 0) {
				$("#rent-list .no-add").hide("fast", function() {
					reWizardHeight();
				});
			} else {
				$("#rent-list .no-add").show("fast", function() {
					reWizardHeight();	
				});
			}
		}
	} else {
		$("#rent-list > .post-equip-group").each(function(){
			if(($(this).data("name")) == groupName) {		// 组存在
				$(this).find(".group-body > #" + id).remove();
				event.stopImmediatePropagation();
			}							
		});	
		$("#rent-list > .post-equip-group").each(function(){
			if($(this).find(".row").length <= 0) {			// 如果组内无预约，移除该组
				$(this).remove();
				event.stopImmediatePropagation();
			};							
		});						
		$(this).find(".icon-ok").remove();		// 移除选中标识
		
	}
});	

	})(jQuery);