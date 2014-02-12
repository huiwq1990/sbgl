
function messagereceiverFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_messagereceiver_id').val(),
					
					"messageid" : $('#input_messagereceiver_messageid').val(),
					
					"receiverid" : $('#input_messagereceiver_receiverid').val(),
					
					"hasview" : $('#input_messagereceiver_hasview').val(),
					
					"viewdate" : $('#input_messagereceiver_viewdate').val(),
					
					"status" : $('#input_messagereceiver_status').val()
					

	};
	
	return params;
}


function addMessagereceiverAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addMessagereceiverAjax.action",
		type: 'post',
		data:messagereceiverFormToJson(),
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

function updateMessagereceiverAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateMessagereceiverAjax.action",
		type: 'post',
		data:messagereceiverFormToJson(),
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


function deleteMessagereceiverAjax(){
	jQuery.ajax({
		url: "deleteMessagereceiverAjax.action",
		type: 'GET',
		data:$('#AddMessagereceiverForm').serialize(),
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
