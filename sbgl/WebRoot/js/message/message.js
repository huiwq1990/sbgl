
function messageFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_message_id').val(),
					
					"title" : $('#input_message_title').val(),
					
					"content" : $('#input_message_content').val(),
					
					"senderid" : $('#input_message_senderid').val(),
					
					"sendtime" : $('#input_message_sendtime').val(),
					
					"replyid" : $('#input_message_replyid').val(),
					
					"readstatus" : $('#input_message_readstatus').val(),
					
					"filepath" : $('#input_message_filepath').val(),
					
					"isbigfile" : $('#input_message_isbigfile').val(),
					
					"type" : $('#input_message_type').val(),
					
					"status" : $('#input_message_status').val()
					

	};
	
	return params;
}


function addMessageAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addMessageAjax.action",
		type: 'post',
		data:messageFormToJson(),
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

function updateMessageAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateMessageAjax.action",
		type: 'post',
		data:messageFormToJson(),
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


function deleteMessageAjax(){
	jQuery.ajax({
		url: "deleteMessageAjax.action",
		type: 'GET',
		data:$('#AddMessageForm').serialize(),
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
