// JavaScript Document

	

	
$(document).ready(function(){
	var lastScrollTop = 0;
	$(window).scroll(function () {
		var st = $(this).scrollTop();
		if (st > lastScrollTop){
		   // downscroll code
		   
		} else {
		  // upscroll code
		  
		}
		lastScrollTop = st;
		
        if ($(window).scrollTop() > 0) {
			$("#global-header").css("top", 0 - $(window).scrollTop());
			$(".nav-wrap").css("top", 80 - $(window).scrollTop());
			$("#rent-bar").css("top", 145 - $(window).scrollTop());
			$(".site-nav .dropdown-menu").css("top","-2px");
			if ($(window).scrollTop() >= 80) {
				$(".nav-wrap").css("top", 0);
				$("#rent-bar").css("top", 65);
			}
        }
        else {
			$(".site-nav .dropdown-menu").css("top","80px");
			$("#global-header").css("top", 0);
			$(".nav-wrap").css("top", 80);
			$("#rent").css("top", 145);
        }
		
    });
	
	/* 全选 */
	$("input[type='checkbox']").click(function() {
		var num = 0;
		if($(this).attr("id") === "chk-all") {
			$("input[name='chk-list']").prop("checked",$(this).prop("checked"));
		}
		$("input[name='chk-list']").each(function() {
			if($(this).prop("checked") === true){
				num++;  
			}
		});
		if(num > 0){
			$(".s-h").css({
				"visibility": "visible",
				"opacity": "1",
				"filter": "alpha(opacity=100)"
			});
		} else {
			$(".s-h").css({
				"visibility": "hidden",
				"opacity": "0",
				"filter": "alpha(opacity=0)"
			});
		}
	});

	$(".select2").select2({
		minimumResultsForSearch: 8,
		formatResult: formatSelect2,
		escapeMarkup: function(markup) { return markup; }
	});

	$('body').tooltip({
		selector: 'a[rel=tooltip],input[rel=tooltip],abbr[rel=tooltip],strong[rel=tooltip]'
	});
	$('a[rel=popover]').popover({
		trigger: "manual",
		html: true,
		placement: 'bottom',
		content: function() {
			  return $('#popover_user_wrapper').html();
		}	
	}).on("click", function(e) {
		e.preventDefault();
	}).on("mouseenter", function() {
		var _this = this;
		$(this).popover("show");
		$(this).siblings(".popover").on("mouseleave", function() {
			$(_this).popover('hide');
		});
	}).on("mouseleave", function() {
		var _this = this;
		setTimeout(function() {
			if (!$(".popover:hover").length) {
		  		$(_this).popover("hide");
			}
	  	}, 300);
	});
	

	$('.spinner').spinner();


	/* ============ 机房预约 ============= */
	$("body").on("click", ".bookable", function() {
		//alert(globalSelectOrderListMap);
		var bookingData = $(this).data();
		var groupName = $(this).parents("table").data("tableName");
		var startDate = new XDate();
		//var timeStr = getBookingTime(bookingData, startDate);
		var timeStr =  bookingData.date+ ' '+bookingData.periodname;
		var maxNum = bookingData.maxNum;
		var orderpcid = bookingData.pcid;
		var index = bookingData.index;
		var id = bookingData.pcid+"-"+bookingData.index;
		var tdid = bookingData.id;//预约清单上显示的条目，用id确定，用于判读是否加入全局变量
		var orderpcname = bookingData.pcname;
		var orderdate = bookingData.date;
		var orderperiod = bookingData.slot;
		//alert(orderperiod);
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
		  '<div class="row" id="' + id + '" data-model-id="' + orderpcid + '" data-index="' + index +'">' + 
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
						
						globalSelectOrderListMap.put(tdid,'1');  //添加
						
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
			globalSelectOrderListMap.remove(tdid);   
			
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
	/* ============ 机房预约 ========== // 侧栏预约单删除（移除）按钮 */
	$(".post-sidebar-warp").on("click", "#rent-list .close", function() {
		var tdIndex = $(this).parents("#rent-list .row").data("index");
		var modelId = $(this).parents("#rent-list .row").data("modelId"); 
		var tdid= 'td'+modelId+'_'+tdIndex;
		
		globalSelectOrderListMap.remove(tdid);   //不管预约单在那个分类的界面下，如果删除某个模型，都要删map
		
		$(this).parents("#rent-list .row").remove();
		$("#rent-list > .post-equip-group").each(function(){
				if($(this).find(".row").length <= 0) {			// 如果组内无预约，移除该组
					$(this).remove();
					event.stopImmediatePropagation();
				};
		});
		$("#post-equip-" + modelId).find('tbody td').each(function(index, td) {
			if ($(td).data("index") === tdIndex ) {
				$(td).removeClass("selected");
				$(td).find(".icon-ok").remove();		// 移除选中标识
				
				
				
				
			};
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

});

function reWizardHeight() {
	$("#rent-list-wrap .panel-body").animate({
		height: $("#rent-list").height() + 30
	});
}

function formatSelect2(state) {
	var originalOption = state.element;
	
	if ($(originalOption).data('foo') === 'sub') {
		return "<span class='sub'>" + state.text + "</span>";
	} else {
		return state.text;
	}

}

function getSelectedCheckboxList() {
	var idsForDel = "";
		
	$("input[name='chk-list']").each(function() {
		if($(this).prop("checked") === true) {
			//num++;
			idsForDel += $(this).prop("value") + ";";
		}
	});
	
	return idsForDel;

}

//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外  
function banBackSpace(e){    
    var ev = e || window.event;//获取event对象     
    var obj = ev.target || ev.srcElement;//获取事件源     
      
    var t = obj.type || obj.getAttribute('type');//获取事件源类型    
      
    //获取作为判断条件的事件类型  
    var vReadOnly = obj.getAttribute('readonly');  
    var vEnabled = obj.getAttribute('enabled');  
    //处理null值情况  
    vReadOnly = (vReadOnly == null) ? false : vReadOnly;  
    vEnabled = (vEnabled == null) ? true : vEnabled;  
      
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，  
    //并且readonly属性为true或enabled属性为false的，则退格键失效  
    var flag1=(ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")   
                && (vReadOnly==true || vEnabled!=true))?true:false;  
     
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效  
    var flag2=(ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")  
                ?true:false;          
      
    //判断  
    if(flag2){  
        return false;  
    }  
    if(flag1){     
        return false;     
    }     
}  
  
//禁止后退键 作用于Firefox、Opera  
document.onkeypress=banBackSpace;  
//禁止后退键  作用于IE、Chrome  
document.onkeydown=banBackSpace;  