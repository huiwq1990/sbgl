
function computerhomeworkreceiverFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerhomeworkreceiver_id').val(),
					
					"computerhomeworkid" : $('#input_computerhomeworkreceiver_computerhomeworkid').val(),
					
					"userid" : $('#input_computerhomeworkreceiver_userid').val()
					

	};
	
	return params;
}


function addComputerhomeworkreceiverAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerhomeworkreceiverAjax.action",
		type: 'post',
		data:computerhomeworkreceiverFormToJson(),
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

function updateComputerhomeworkreceiverAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerhomeworkreceiverAjax.action",
		type: 'post',
		data:computerhomeworkreceiverFormToJson(),
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


function deleteComputerhomeworkreceiverAjax(){
	jQuery.ajax({
		url: "deleteComputerhomeworkreceiverAjax.action",
		type: 'GET',
		data:$('#AddComputerhomeworkreceiverForm').serialize(),
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
