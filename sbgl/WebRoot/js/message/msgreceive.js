
function msgreceiveFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_msgreceive_id').val(),
					
					"senderid" : $('#input_msgreceive_senderid').val(),
					
					"receiverid" : $('#input_msgreceive_receiverid').val(),
					
					"title" : $('#input_msgreceive_title').val(),
					
					"content" : $('#input_msgreceive_content').val(),
					
					"type" : $('#input_msgreceive_type').val(),
					
					"sendtime" : $('#input_msgreceive_sendtime').val(),
					
					"readtime" : $('#input_msgreceive_readtime').val(),
					
					"status" : $('#input_msgreceive_status').val()
					

	};
	
	return params;
}


function addMsgreceiveAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addMsgreceiveAjax.action",
		type: 'post',
		data:msgreceiveFormToJson(),
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

function updateMsgreceiveAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateMsgreceiveAjax.action",
		type: 'post',
		data:msgreceiveFormToJson(),
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


function deleteMsgreceiveAjax(){
	jQuery.ajax({
		url: "deleteMsgreceiveAjax.action",
		type: 'GET',
		data:$('#AddMsgreceiveForm').serialize(),
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
