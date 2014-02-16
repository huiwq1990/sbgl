// JavaScript Document

	(function($){
		
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

		function format(state) {
			var originalOption = state.element;
			
			if ($(originalOption).data('foo') === 'sub') {
				return "<span class='sub'>" + state.text + "</span>";
			} else {
				return state.text;
			}
		
		}

		function reloadSelect2() {
			$(".select2").select2({
				minimumResultsForSearch: 8,
				formatResult: format,
				escapeMarkup: function(markup) { return markup; }
			});
		}
		$(".select2").select2({
			minimumResultsForSearch: 8,
			formatResult: format,
			escapeMarkup: function(markup) { return markup; }
		});
			
		$(window).load(function(){


			
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
			
			$('body').tooltip({
				selector: 'a[rel=tooltip],input[rel=tooltip],abbr[rel=tooltip],strong[rel=tooltip],code[rel=tooltip]',
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
				  		$(_this).popover("hide")
					}
			  	}, 300);
			});
			

			/* 添加预约设备向导 */
			$("#rent-bar .wizard").height($("#rent-bar .wizard .step-content").height() + 60);
			$('#rent-bar .wizard').on('changed', function(e, data) {
				$("#rent-bar .wizard").animate({
					height: $("#rent-bar .wizard .step-content").height() + 130
				});
/*				var item = $('.wizard').wizard('selectedItem');
				if((item.step > 2)) {
					$("#rent-bar").animate({
							"right": "0%",
							"top": "145px",
							"width": "380px",
							"margin-right": "20px"
					});
					$(".modal-backdrop").remove();					
				}*/
			});
			$('#rent-bar .wizard').on('finished', function(e, data) {

				var dateCheckin = $("#rent-bar .wizard .input-daterange .checkin").datepicker("getDate");
				var dateCheckout = $("#rent-bar .wizard .input-daterange .checkout").datepicker("getDate");
				if (dateCheckin == "Invalid Date" || dateCheckout == "Invalid Date" ) {
					alert("请填写预约时间。");
				} else {
					$("#rent-bar").animate({
						"right": "0%",
						"top": "145px",
						"width": "380px",
						"margin-right": "20px"
					});
					$("#rent-bar .input-daterange .checkin1").datepicker("setDate", dateCheckin);
					$("#rent-bar .input-daterange .checkout1").datepicker("setDate", dateCheckout);
					$(".modal-backdrop").remove();
					$("#rent-bar .wizard").remove();
					$("#rent-bar > .rent-time").show();
					$("#rent-bar > #rent-list").show();
				}
			});
			/* 添加设备按钮 */
			$("body").on("click", "a[data-type|=addEquip]", function() {
				var myData = $(this).data();
				var eId = myData.id;
				var eName = myData.name;
				var maxNum = myData.num;  // 设备最大可预约数量
				var eSelectNum = 0;
				if($("#equip-detail").hasClass("in")) {
					eSelectNum = $(this).parents("#equip-detail").find("select").select2("val");
				}
				else {
					eSelectNum = $(this).parent().find("select").select2("val");
				}
				var html = 
					'<div class="row" id=' + eId + '>' + 
						'<div class="item-name">' + eName + '</div>' +
						'<div class="item-ctrl spinner input-group">' +
							'<span class="input-group-btn">' + 
								'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
							'</span>' + 
							'<input type="text" class="spinner-input form-control">' + 
							'<span class="input-group-btn">' + 
								'<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
							'</span>' + 
							'<span class="input-group-btn">' + 
								'<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
							'</span>' + 
						'</div>' + 
					'</div>';
					
				if ($("#rent-list #" + eId).length > 0) {
					alert("设备" + eName + "已添加！");
				} else {									
					$("#rent-list .panel-body").append(html);					
					var spinner = $("#rent-list #" + eId).children(".spinner").spinner({max: maxNum});		// 设置设备最大可预约数量
					$("#rent-list #" + eId).children(".spinner").spinner('value', eSelectNum);
					var overMaxNum = 0;
					$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
						var _this = $(this);
						var curVal = $(this).spinner("value");
						var htmlMoreStr = '<div class="item-need-more">( 还需：<span class="need-num"></span> )</div>';
						var needMore;
						if(curVal === maxNum) {
							var str = "已达最大可借数量，继续添加，系统会记录下你所需实际数量，并提供相应建议!";
							var n = noty({text: str, timeout: 6000,});

							$(_this).on("click", ".spinner-up",function(){
								if ($(_this).parent().find(".item-need-more").length === 0 ) {								
									needMore = $(_this).parent().append(htmlMoreStr);
									reWizardHeight();	
								}
								overMaxNum++;
								$("#rent-list #" + eId).children(".spinner").spinner("setMin", maxNum);		// 设置设备最小可预约数量为最大数，使其减少还需数量
								console.log(".spinner-up, 已达最大可预约数量！,超出： " + overMaxNum);
								$(_this).next(".item-need-more").find(".need-num").html(overMaxNum);								
								console.log(".spinner-up, 已达最大可预约数量！,超出： " + overMaxNum);
							});	
							$(_this).on("click", ".spinner-down",function(){
								overMaxNum--;
								if (overMaxNum <= 0) {
									overMaxNum = 1;
									$("#rent-list #" + eId).children(".spinner").spinner("setMin", 0);		// 设置设备最小可预约数量为0	
									$(_this).next(".item-need-more").remove();
									reWizardHeight();
									console.log("set min 0, 超出： " + overMaxNum);
								}
								
								console.log(".spinner-down, 已达最大可预约数量！,超出： " + overMaxNum);	
								$(_this).next(".item-need-more").find(".need-num").html(overMaxNum);								
				
							});
											
						}
						else {							
							$(this).off("click", ".spinner-up");		// 注销+单击事件，直到再次达到最大数量时再次绑定单击事件
							$(this).off("click", ".spinner-down");		// 注销-单击事件，直到再次达到最大数量时再次绑定单击事件
							console.log("spinner-down off");
							console.log("spinner-up off");
						}
						console.log("curVal=" + curVal);
						if(curVal > maxNum) {
							$(_this).spinner("value", maxNum);
							overMaxNum = 0;
							$(_this).next(".item-need-more").remove();
							reWizardHeight();
						}
						if(curVal < maxNum) {
							overMaxNum = 0;
							$(_this).next(".item-need-more").remove();
							reWizardHeight();
						}
						$(_this).find(".spinner-input").focus(function() {
							overMaxNum = 0;
						});
//						if (overMaxNum == 0) {
//							$("#rent-list #" + eId).children(".spinner").spinner("setMin", 0);		// 设置设备最小可预约数量									
//							console.log("set min 0, 超出： " + overMaxNum);
//						}

						event.stopImmediatePropagation();
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
			});
			$("body").on("click", "#rent-list .close", function() {
				$(this).parent().parent().parent().remove();
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

			/* ============ 机房预约 ============= */
			$("body").on("click", ".bookable", function() {
				var bookingData = $(this).data();
				var groupName = $(this).parents("table").data("tableName");
				var startDate = new XDate();
				var timeStr = getBookingTime(bookingData, startDate);
				var id = bookingData.index;
				var maxNum = bookingData.maxNum;
				var groupHtml = 
					'<div class="post-equip-group" data-name=' + groupName + '>' +
						'<div class="group-hd">' +
							'<div class="group-name pull-left">' + groupName + '</div>' +
							'<div class="group-line"><hr></div>' +
						'</div>' +
						'<div class="group-body">' +
						'</div>'
					'</div>';
				var html = 
				  '<div class="row" id=' + id + '>' + 
					  '<div class="item-name">' + timeStr + '</div>' +
					  '<div class="item-ctrl spinner input-group">' +
						  '<span class="input-group-btn">' + 
							  '<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
						  '</span>' + 
						  '<input type="text" class="spinner-input form-control">' + 
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

/*			function reWizardHeight_old() {
				$("#rent-bar .wizard").animate({
					height: $("#rent-bar .wizard .step-content").height() + 130
				});
			}*/

            function reWizardHeight() {
				$("#rent-list").animate({
					height: $("#rent-list .panel-body").height() + 100
				});
			}


			$('.spinner').spinner();
			/*
			$("#rent-list").mCustomScrollbar({
				scrollButtons:{
					enable:false
				},
				theme:"dark-thick"
			});
			*/




		
		});
	})(jQuery);