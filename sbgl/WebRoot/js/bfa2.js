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

			
		
			$('body').tooltip({
				selector: 'a[rel=tooltip],input[rel=tooltip],abbr[rel=tooltip],strong[rel=tooltip],code[rel=tooltip],button[rel=tooltip]'
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
					$("#rent-bar > #rent-list-wrap").show();
				}
			});
			/* 添加设备按钮 */
			$("body").on("click", "a[data-type|=addEquip]", function() {
				var myData = $(this).data();
				var eId = myData.id;
				var eName = myData.name;
				var maxNum = myData.num;  // 设备最大可预约数量
				var eCate = myData.cate; //如果cate是-2则为设备组
				var eSelectNum = 0;
				if($("#equip-detail").hasClass("in")) {
					eSelectNum = $(this).parents("#equip-detail").find("select").select2("val");
				}
				else {
					eSelectNum = $(this).parent().find("select").select2("val");
				}
				if(eCate==-2){
					var fromDate = $("#fromDate").val();
					var endDate = $("#endDate").val();
					$.ajax({
						url: "equipmentGroupOrder.do",
						type: 'POST',
						data: {equipmentId:eId,fromDate:fromDate,endDate:endDate},
						dataType: 'json',
						success: function(data){
							jQuery.each(data.equipmentList, function(i,item){
								if ($("#rent-list #" + item.equipmentid).length > 0) {
									var curVal = $("#rent-list #" + item.equipmentid).children(".spinner").spinner('value');
									var tempnum = parseInt(item.num) + parseInt(curVal) - item.borrownum;
									if(tempnum>0){
										console.log(".spinner-up, 设备组要求"+item.equipmentname+"设备数量超出！,超出： " + tempnum);
										alert("设备组要求"+item.equipmentname+"设备数量超出！,超出： " + tempnum);
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', item.borrownum);
									}else{
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', parseInt(item.num)+parseInt(curVal));
									}
								}else{
									var html = 
										'<div class="row" id="' + item.equipmentid + '">' + 
											'<div class="item-name">' + item.equipmentname + '</div>' +
											'<div class="item-ctrl spinner input-group">' +
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
												'</span>' +
												'<input type="text" name="equNum" class="spinner-input form-control">' + 
												'<input type="hidden" name="equId" value="'+item.equipmentid+'">' + 
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
												'</span>' + 
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
												'</span>' + 
											'</div>' + 
										'</div>';
									$("#rent-list").append(html);
									var spinner = $("#rent-list #" + eId).children(".spinner").spinner({max: item.borrownum});		// 设置设备最大可预约数量

									var overMaxNum = 0;
									if(item.num>item.borrownum){
										var overMaxNum = item.num - item.borrownum;
										console.log(".spinner-up, 设备组要求"+item.equipmentname+"设备数量超出！,超出： " + overMaxNum);
										alert("设备组要求"+item.equipmentname+"设备数量超出！,超出： " + overMaxNum);
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', item.borrownum);
									}else{
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', item.num);
									}
									$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
										var _this = $(this);
										var curVal = $(this).spinner("value");
										var htmlMoreStr = '<div class="item-need-more">( 还需：<span class="need-num"></span> )</div>';
										var needMore;
										if(curVal === item.borrownum) {
											var str = "已达最大可借数量，继续添加，系统会记录下你所需实际数量，并提供相应建议!";
											var n = noty({text: str, timeout: 6000});
				
											$(_this).on("click", ".spinner-up",function(){
												if ($(_this).parent().find(".item-need-more").length === 0 ) {
													needMore = $(_this).parent().append(htmlMoreStr);
													reWizardHeight();	
												}
												overMaxNum++;
												$("#rent-list #" + item.equipmentid).children(".spinner").spinner("setMin", item.borrownum);		// 设置设备最小可预约数量为最大数，使其减少还需数量
												console.log(".spinner-up, 已达最大可预约数量！,超出： " + overMaxNum);
												$(_this).next(".item-need-more").find(".need-num").html(overMaxNum);								
												console.log(".spinner-up, 已达最大可预约数量！,超出： " + overMaxNum);
											});	
											$(_this).on("click", ".spinner-down",function(){
												overMaxNum--;
												if (overMaxNum <= 0) {
													overMaxNum = 1;
													$("#rent-list #" + item.equipmentid).children(".spinner").spinner("setMin", 0);		// 设置设备最小可预约数量为0	
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
										if(curVal > item.borrownum) {
											$(_this).spinner("value", item.borrownum);
											overMaxNum = 0;
											$(_this).next(".item-need-more").remove();
											reWizardHeight();
										}
										if(curVal < item.borrownum) {
											overMaxNum = 0;
											$(_this).next(".item-need-more").remove();
											reWizardHeight();
										}
										$(_this).find(".spinner-input").focus(function() {
											overMaxNum = 0;
										});
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
						}  
					});
				}else{
					var html = 
						'<div class="row" id="' + eId + '">' + 
							'<div class="item-name">' + eName + '</div>' +
							'<div class="item-ctrl spinner input-group">' +
								'<span class="input-group-btn">' + 
									'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
								'</span>' +
								'<input type="text" name="equNum" class="spinner-input form-control">' + 
								'<input type="hidden" name="equId" value="'+eId+'">' + 
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
						var spinner = $("#rent-list #" + eId).children(".spinner").spinner({max: maxNum});		// 设置设备最大可预约数量
						$("#rent-list #" + eId).children(".spinner").spinner('value', eSelectNum);
						var overMaxNum = 0;
						$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
							var _this = $(this);
							var curVal = $(this).spinner("value");
							var htmlMoreStr = '<div class="item-need-more">( 还需：<span class="need-num"></span> )</div>';
							var needMore;
							if(curVal === maxNum) {
								//var str = "已达最大可借数量，继续添加，系统会记录下你所需实际数量，并提供相应建议!";
								//var n = noty({text: str, timeout: 6000});
	
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
				}
			});
			$("body").on("click", "#rent-bar #rent-list .close", function() {
				$(this).parents("#rent-list .row").remove();
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


			$('.spinner').spinner();

		
		});
	})(jQuery);