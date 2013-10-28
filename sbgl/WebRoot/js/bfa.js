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

			$(".select2").select2({
				minimumResultsForSearch: 8,
				formatResult: format,
				escapeMarkup: function(markup) { return markup; }
			});
			function format(state) {
				var originalOption = state.element;
				
				if ($(originalOption).data('foo') === 'sub') {
					return "<span class='sub'>" + state.text + "</span>";
				} else {
					return state.text;
				}
			
			}

							
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
				selector: 'a[rel=tooltip],input[rel=tooltip],abbr[rel=tooltip]',
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
				/*
				$(".step-pane").each(function(index) {
					console.log( $(this).css() );
					if ($(this).hasClass("active")) {
						$(this).show(300);
					} else {
						$(this).hide(300);
					}
				});
				*/
				var item = $('.wizard').wizard('selectedItem');
				if((item.step > 2)) {
					$("#rent-bar").animate({
							"right": "0%",
							"top": "145px",
							"width": "380px",
							"margin-right": "20px"
					});
					$(".modal-backdrop").remove();					
				}
			});
			/* 添加设备按钮 */
			$("body").on("click", "a[data-type|=addEquip]", function() {
				var myData = $(this).data();
				var eId = myData.id;
				var eName = myData.name;
				var maxNum = myData.num;  // 设备最大可预约数量
				
				var eId = $(this).data("id");
				var eName = $(this).data("name");
				var maxNum = $(this).data("num");  // 设备最大可预约数量
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
							'<input type="text" name="equipmentNums" class="spinner-input form-control">' + 
							'<input type="hidden" name="equipmentIds" value="'+eId+'">'+
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
					$("#rent-list").append(html);					
					$("#rent-list #" + eId).children(".spinner").spinner({max: maxNum});		// 设置设备最大可预约数量
					$("#rent-list #" + eId).children(".spinner").spinner('value', eSelectNum);
					$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
						if($(this).spinner('value') === maxNum) {
							$(this).on("click", ".spinner-up",function(){
								alert("已达最大可预约数量！");
							});
						} else {
							$(this).off("click", ".spinner-up");
						}
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
				$(this).parents(".row").remove();
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
				$("#rent-bar .wizard").animate({
					height: $("#rent-bar .wizard .step-content").height() + 130
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