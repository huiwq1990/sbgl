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
				var overMaxListStr = '';		// 不满足要求的器材列表
				var eSelectNum = 0;
				var curMaxNum = 1;		// 当前动态input表单中的最大值
				
				if(maxNum == 0) {
					var str = eName + order_forthenumber;
					noty({text: str, timeout: 2500, type: 'error'});
					return;
				} else {
					eSelectNum = 1;
				}
				if(eCate==-2){				// 设备组
					var fromDate = $("#fromDate").val();
					var endDate = $("#endDate").val();
					
					var curSingleEquipNum = 0; // 当前器材添加的数量
					var isOverMax = false;
					var returnData;
					
					var htmlGroup = 
						'<div class="row equip-item" id="' + eId + '" data-id="' + eId + '" title="' + eName +'">' + 
						    '<div class="group-head clearfix">' +
							'<div class="item-name"><strong>' + eName + '</strong></div>' +
								'<div class="item-ctrl-remove input-group">' + 
									'<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
								'</div>' + 
								'<div class="item-ctrl-spinner spinner input-group" data-id="' + eId + '">' +
									'<span class="input-group-btn">' + 
										'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
									'</span>' +
									'<input type="text" name="equNum" class="spinner-input form-control">' + 
									'<input type="hidden" name="equId" value="' + eId + '">' + 
									'<span class="input-group-btn">' + 
										'<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
									'</span>' + 
								'</div>' + 
							'</div>' + 
							'<div class="group-list clearfix"></div>' +
						'</div>';
					
					$.ajax({
						url: "equipmentGroupOrder.do",
						type: 'POST',
						data: {equipmentId:eId,fromDate:fromDate,endDate:endDate},
						dataType: 'json',
						success: function(data){

							returnData = data.equipmentList;
							var len = data.equipmentList.length;
							// 遍历设备组内器材，如果有器材数量不满足，则不能添加该设备组，如果都满足数量，向租赁列表内添加该设备组，并显示出组内的设备
							jQuery.each(data.equipmentList, function(i,item){
								if($("#rent-list > #" + item.comId).length > 0) {
									curSingleEquipNum = $("#rent-list > #" + item.comId).children(".spinner").spinner('value');
								}
								if((item.num + curSingleEquipNum) > item.borrownum) {			// 如果组内某个器材数量大于可借数量
									overMaxListStr = overMaxListStr + item.equipmentname + '<br>';
									isOverMax = true;  		// 该设备组不可借
								}
							});     // jQuery.each ##END


							if (isOverMax) {		// 如果设备组内有超出可预约数量的器材，则该设备组不可借
								var str = "";
								if (curSingleEquipNum != 0) {
									str = "<div class='text-left'>不能添加<strong>" + eName + "</strong>!<br>下列器材已添加导致数量无法满足该设备组。<br>" + overMaxListStr + "</div>";
								} else {
									str = "<div class='text-left'>不能添加<strong>" + eName + "</strong>!<br>因为该设备组内以下器材目前数量不足：<br>" + overMaxListStr + "</div>";
								}
								noty({
									  text: str,
									  type: 'error',
									  buttons: [
									    {addClass: 'btn btn-danger', text: 'OK', onClick: function($noty) { $noty.close(); }}
									  ]
									});
								isOverMax = false;
								curSingleEquipNum = 0;
								overMaxListStr = '';
							} else {		// 该设备满足可添加条件，那么
								if ($("#rent-list #" + eId).length > 0) {	// 如果已添加该设备组
									var str = eName + order_beenadded;
									noty({text: str, timeout: 2500});
								} else {		// 添加这个设备组
									$("#rent-list").append(htmlGroup);			// 向列表中添加该条设备组
									$("#rent-list > #" + eId).find(".group-head").children(".spinner").spinner('value', eSelectNum);
									jQuery.each(data.equipmentList, function(i,item) {
										var html =
											'<div id="group-equip-' + item.comId + '" class="group-item" title="' + item.equipmentname +'" data-id="' + item.comId + '" data-num-in-group="' + item.num + '" data-unit-num="' + item.num + '">' + 
												'<div class="item-name">&nbsp;&nbsp;&nbsp;&nbsp;' + item.equipmentname + '</div>' +
												'<div class="item-ctrl text-right">× ' +
													'<span class="num-in-group">' + item.num + '</span>' +
												'</div>' + 
											'</div>';
										$("#rent-list #" + eId).find(".group-list").append(html);
									});     // jQuery.each ##END
									
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
								
								
							}  // isOverMax #####END
							
							// 处理设备组数量变化
							$("#rent-list .spinner").on("click changed", function(event){
								event.stopImmediatePropagation();
								var _this = $(this);
								var thisGroupId = $(this).data("id");
								var curEquipGroupNum = _this.spinner("value");
								var equipInGroupNum = 0;
								
								jQuery.each(data.equipmentList, function(i,item) {
									if ($("#rent-list > #" + item.comId).length > 0) {		// 如果添加的设备组外单独添加含有组内的器材
										curSingleEquipNum = $("#rent-list > #" + item.comId).children(".spinner").spinner('value');		// 获取组外单独添加的该种设备数量
									} else {
										curSingleEquipNum = 0;
									}
									var _thisEquip = $("#rent-list #group-equip-" + item.comId);		// 遍历所有器材组中的该种器材
									var thisGroupNum = 0;
									jQuery.each(_thisEquip, function(j,elem){
										thisGroupNum = $(elem).parents(".row").find(".spinner").spinner("value");
										equipInGroupNum = equipInGroupNum + $(elem).data("unitNum") * thisGroupNum;
										//alert("item.equipmentname=" + item.equipmentname + " , unitNum=" + $(elem).data("unitNum") + " , thisGroupNum=" + thisGroupNum);
									});
									if((equipInGroupNum + curSingleEquipNum) > item.borrownum) {			// 如果组内某个器材数量大于可借数量
										overMaxListStr = overMaxListStr + item.equipmentname + '<br>';
										isOverMax = true;  		// 该设备组不可借
									}
									//alert(item.equipmentname + ", equipNum=" + (equipInGroupNum + curSingleEquipNum));
									equipInGroupNum = 0;	// 数量清零计算下一个器材的总数量
								});     // jQuery.each 处理设备组数量变化 #####END
								
								if(!isOverMax) {		// 如果没有设备超出可借数量
									jQuery.each(data.equipmentList, function(i,item){
										var html =
											'<div id="group-equip-' + item.comId + '" title="' + item.equipmentname +'" data-num-in-group="' + curEquipGroupNum * item.num + '" data-unit-num="' + item.num + '">' + 
												'<div class="item-name">&nbsp;&nbsp;&nbsp;&nbsp;' + item.equipmentname + '</div>' +
												'<div class="item-ctrl text-right">× ' +
													'<span class="num-in-group">' + curEquipGroupNum * item.num + '</span>' +
												'</div>' + 
											'</div>';
										$("#rent-list > #" + thisGroupId).find("#group-equip-" + item.comId).replaceWith(html);
										//$("#group-equip-" + item.comId).data("num-in-group", curEquipGroupNum * item.num);
										curMaxNum = curEquipGroupNum;
									});     // jQuery.each #####END
								} else {
									var str = "<div class='text-left'>不能再添加<strong>" + eName + "</strong>!<br>因为该设备组内以下器材目前数量不足：<br>" + overMaxListStr + "</div>";
									var btnStr = 'OK';
									noty({
										  text: str,
										  type: 'error',
										  buttons: [
										    {addClass: 'btn btn-danger', text: btnStr, onClick: function($noty) { $noty.close(); }}
										  ]
									});
									$(this).spinner('value', curMaxNum);	//设置为可预约最大值
									isOverMax = false;
									curSingleEquipNum = 0;
									overMaxListStr = '';
									curMaxNum = 1;
								}
							});		// 处理设备组数量变化 click  ######END


						}  
					});


				//    单个器材	
				} else {
					var html = 
						'<div class="row equip-item" id="' + eId + '" data-id="' + eId + '" title="' + eName +'">' + 
							'<div class="item-name"><strong>' + eName + '</strong></div>' +
							'<div class="item-ctrl-remove input-group">' + 
								'<button type="button" class="btn btn-link close"><i class="icon-remove"></i></button>' + 
							'</div>' + 
							'<div class="item-ctrl-spinner spinner input-group" data-id="' + eId + '">' +
								'<span class="input-group-btn">' + 
									'<button type="button" class="btn btn-link spinner-down"><i class="icon-minus"></i></button>' + 
								'</span>' +
								'<input type="text" name="equNum" class="spinner-input form-control">' + 
								'<input type="hidden" name="equId" value="' + eId + '">' + 
								'<span class="input-group-btn">' + 
									'<button type="button" class="btn btn-link spinner-up"><i class="icon-plus"></i></button>' +
								'</span>' + 
							'</div>' + 
						'</div>';
						
					if ($("#rent-list #" + eId).length > 0) { 	// 如果单个器材存在，那么不添加
						var str = eName + order_beenadded;
						noty({text: str, timeout: 2500});
					} else {	// 如果单个器材不存在，判断数量是否满足加入预约单
						var equipInGroupNum = 0;		// 单独添加的器材在器材组中的数量
						if ($("#rent-list #group-equip-" + eId).length > 0) {		// 如果添加的器材组含有该种器材，统计器材组中含有的数量
							var _this = $("#rent-list #group-equip-" + eId);
							jQuery.each(_this, function(i,item){
								equipInGroupNum = equipInGroupNum + $(item).data("numInGroup");
							});
							//alert(equipInGroupNum);
						}
						if ((eSelectNum + equipInGroupNum) > maxNum) {	// 如果器材数量大于可借数量
							isOverMax = true;  		// 超过最大数量，该设备不可添加
						}
						if (isOverMax) {	// 超过最大数量，该设备不可添加
							var str = eName + order_maximum + 'fdsfffa';
							noty({text: str, timeout: 2500});
							isOverMax = false;
						} else {		// 否则添加该单个设备到预约单
							$("#rent-list").append(html);
							$("#rent-list > #" + eId).children(".spinner").spinner('value', eSelectNum);	//设置初始数量
						}
						
						if ($("#rent-list .row").length > 0) {		// 调整预约单大小高度
							$("#rent-list .no-add").hide("fast", function() {
								reWizardHeight();
							});
						} else {
							$("#rent-list .no-add").show("fast", function() {
								reWizardHeight();	
							});
						}
					} // else 如果单个器材不存在，判断数量是否满足加入预约单    #######END
					
					$("#rent-list .spinner").on("click changed", function(event){  // // 处理单个器材数量变化
						event.stopImmediatePropagation();
						
						equipInGroupNum = 0;	// 初始化置零在器材组内该种器材的数量
						var curSingleEquipNum = $(this).spinner("value");		// 单个器材数量
						var curSingleEquipMaxNum = 1;
						var _thisEquip = $("#rent-list #group-equip-" + eId);		// 遍历所有器材组中的该种器材
						isOverMax = false;
						
						jQuery.each(_thisEquip, function(j,elem){
							equipInGroupNum = equipInGroupNum + $(elem).data("numInGroup");
						});
						
						if(curSingleEquipNum + equipInGroupNum > maxNum) {		// 如果超出最大数量
							isOverMax = true;  		// 该设备组不可借				
						}
						
						if(!isOverMax) {		// 如果没有设备超出可借数量
							curMaxNum = curSingleEquipNum;
						} else {
							var str = eName + order_maximum + "aaa";
							noty({text: str, timeout: 2500});
							$(this).spinner('value', curMaxNum);	//设置为可预约最大值
							isOverMax = false;
							curMaxNum = 1;
						}
						
						
					});			
					
				}  // if-else 判断是器材组还是单个器材 ########END
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