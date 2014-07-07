
	        	  
 
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
	var id = orderpcid + "-"+bookingData.index;
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
	  '<div class="row" id="'+id+'">' + 
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
				  '<button type="button" class="btn btn-link close" action-type="removeThisOrderItem" data-id="' + orderpcid + '" data-index="' + bookingData.index + '"><i class="icon-remove"></i></button>' + 
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
			}
		});
		$("#rent-list > .post-equip-group").each(function(){
			if($(this).find(".row").length <= 0) {			// 如果组内无预约，移除该组
				$(this).remove();
			};
		});
		$(this).find(".icon-ok").remove();		// 移除选中标识

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
});	

// 侧栏预约单删除（移除）按钮
$(".post-sidebar-warp").on("click", "#rent-list .close", function() {
	var tdIndex = $(this).parents("#rent-list .row").data("index");
	var groupName = $(this).parents("#rent-list .post-equip-group").data("name"); 
	$(this).parents("#rent-list .row").remove();
	$("#rent-list > .post-equip-group").each(function(){
			if($(this).find(".row").length <= 0) {			// 如果组内无预约，移除该组
				$(this).remove();
				event.stopImmediatePropagation();
			};
	});
	$(".post-list table").each(function(index, table) {
		if($(table).data("tableName") == groupName) {
			$(table).find("td").each(function(index, td) {
				if($(td).data("index") == tdIndex) {
					$(td).removeClass("selected");
					$(td).find(".icon-ok").remove();		// 移除选中标识
				}
			});
		}
	});
	if ($("#rent-list .row").length > 0) {
		$("#rent-list .no-add").hide("fast", function() {
			reWizardHeight();
		});
	} else {
		$("#rent-list .no-add").show("fast", function() {
			reWizardHeight();	
		});
	}
});


function reWizardHeight() {
	$("#rent-list-wrap .panel-body").animate({
		height: $("#rent-list").height() + 30
	});
}

})(jQuery);