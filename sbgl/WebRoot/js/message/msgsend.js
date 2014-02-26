
function msgsendFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_msgsend_id').val(),
					
					"senderid" : $('#input_msgsend_senderid').val(),
					
					"receiverid" : $('#input_msgsend_receiverid').val(),
					
					"title" : $('#input_msgsend_title').val(),
					
					"content" : $('#input_msgsend_content').val(),
					
					"type" : $('#input_msgsend_type').val(),
					
					"sendtime" : $('#input_msgsend_sendtime').val(),
					
					"readtime" : $('#input_msgsend_readtime').val(),
					
					"status" : $('#input_msgsend_status').val()
					

	};
	
	return params;
}


function addMsgsendAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addMsgsendAjax.action",
		type: 'post',
		data:msgsendFormToJson(),
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

function updateMsgsendAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateMsgsendAjax.action",
		type: 'post',
		data:msgsendFormToJson(),
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


function deleteMsgsendAjax(){
	jQuery.ajax({
		url: "deleteMsgsendAjax.action",
		type: 'GET',
		data:$('#AddMsgsendForm').serialize(),
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
