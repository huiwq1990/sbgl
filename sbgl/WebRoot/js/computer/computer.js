
function computerFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computer_id').val(),
					
					"serialnumber" : $('#input_computer_serialnumber').val(),
					
					"computermodelid" : $('#input_computer_computermodelid').val(),
					
					"createtime" : $('#input_computer_createtime').val(),
					
					"createuserid" : $('#input_computer_createuserid').val(),
					
					"status" : $('#input_computer_status').val()
					

	};
	
	return params;
}


function addComputerAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerAjax.action",
		type: 'post',
		data:computerFormToJson(),
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

function updateComputerAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerAjax.action",
		type: 'post',
		data:computerFormToJson(),
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


function deleteComputerAjax(){
	jQuery.ajax({
		url: "deleteComputerAjax.action",
		type: 'GET',
		data:$('#AddComputerForm').serialize(),
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
