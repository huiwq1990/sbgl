
function notificationFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_notification_id').val(),
					
					"title" : $('#input_notification_title').val(),
					
					"content" : $('#input_notification_content').val(),
					
					"senderrid" : $('#input_notification_senderrid').val(),
					
					"receiverid" : $('#input_notification_receiverid').val(),
					
					"sendtime" : $('#input_notification_sendtime').val(),
					
					"readstatus" : $('#input_notification_readstatus').val(),
					
					"modeltype" : $('#input_notification_modeltype').val(),
					
					"status" : $('#input_notification_status').val()
					

	};
	
	return params;
}


function addNotificationAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addNotificationAjax.action",
		type: 'post',
		data:notificationFormToJson(),
		dataType: 'json',
		success: function(data){
			var returnJson = jQuery.parseJSON(data); 
			//alert(returnJson.flag);
			if(returnJson.flag==1){
				alert("保存成功!")
			}else{
				alert("保存失败!")
			}
		}		  	  
	});	
}	

function updateNotificationAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateNotificationAjax.action",
		type: 'post',
		data:notificationFormToJson(),
		dataType: 'json',
		success: function(data){
			var returnJson = jQuery.parseJSON(data); 
			//alert(returnJson.flag);
			if(returnJson.flag==1){
				alert("保存成功!")
			}else{
				alert("保存失败!")
			}
		}		  	  
	});	
}


function deleteNotificationAjax(){
	jQuery.ajax({
		url: "deleteNotificationAjax.action",
		type: 'GET',
		data:$('#AddNotificationForm').serialize(),
		dataType: 'html',
		success: function(data){
			if(data.flag!=1){
				alert("保存成功!")
			}else{
				alert("保存失败!")
				}
				}		  	  
	});
}
