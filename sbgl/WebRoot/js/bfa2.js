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
					alert(order_fillouttheappointment);  
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
				if(maxNum == 0) {
					alert(order_forthenumber);
					return;
				} else {
					eSelectNum = 1;
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
								if ($("#rent-list #" + item.comId).length > 0) {
									var curVal = $("#rent-list #" + item.equipmentid).children(".spinner").spinner('value');
									var tempnum = parseInt(item.num) + parseInt(curVal) - item.borrownum;
									if(tempnum>0){
										//console.log(".spinner-up, 设备组要求"+item.equipmentname+"设备数量超出！,超出： " + tempnum);
										alert(order_equipmentgroupre+item.equipmentname+order_numberofdevicesbeyond + tempnum);
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', item.borrownum);
									}else{
										$("#rent-list #" + item.equipmentid).children(".spinner").spinner('value', parseInt(item.num)+parseInt(curVal));
									}
								}else{
									var html = 
										'<div class="row" id="' + item.comId + '">' + 
											'<div class="item-name">' + item.equipmentname + '</div>' +
											'<div class="item-ctrl spinner input-group">' +
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
												'</span>' +
												'<input type="text" name="equNum" class="spinner-input form-control">' + 
												'<input type="hidden" name="equId" value="'+item.comId+'">' + 
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
												'</span>' + 
												'<span class="input-group-btn">' + 
													'<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
												'</span>' + 
											'</div>' + 
										'</div>';
									$("#rent-list").append(html);
									var spinner = $("#rent-list #" + item.comId).children(".spinner").spinner({max: item.borrownum});		// 设置设备最大可预约数量

									var overMaxNum = 0;
									if(item.num>item.borrownum){
										var overMaxNum = item.num - item.borrownum;
										//console.log(".spinner-up, 设备组要求"+item.equipmentname+"设备数量超出！,超出： " + overMaxNum);
										alert(order_equipmentgroupre+item.equipmentname+order_numberofdevicesbeyond + overMaxNum);
										$("#rent-list #" + item.comId).children(".spinner").spinner('value', item.borrownum);
									}else{
										$("#rent-list #" + item.comId).children(".spinner").spinner('value', item.num);
									}
									$("#rent-list .spinner").on("changed", $(this), function(event) {		// 处理达到最大预约数量时的消息提示
										var _this = $(this);
										var curVal = $(this).spinner("value");

										if(curVal >= item.borrownum) {
											var str = item.equipmentname + order_maximum;
											var n = noty({text: str, timeout: 1500});	
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
						var str = order_equment + eName + order_beenadded;
						var n = noty({text: str, timeout: 1000});
					} else {
						$("#rent-list").append(html);
						var spinner = $("#rent-list #" + eId).children(".spinner").spinner({max: maxNum});		// 设置设备最大可预约数量
						$("#rent-list #" + eId).children(".spinner").spinner('value', eSelectNum);	//设置初始数量
						$("#rent-list .spinner").on("click", ".spinner-up",function(){  // 处理达到最大预约数量时的消息提示
							var curVal = $(this).spinner("value");
							if(curVal >= maxNum) {
								var str = eName + order_maximum;
								var n = noty({text: str, timeout: 1500});					
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