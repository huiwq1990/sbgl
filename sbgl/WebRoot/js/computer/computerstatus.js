
function computerstatusFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerstatus_id').val(),
					
					"name" : $('#input_computerstatus_name').val()
					

	};
	
	return params;
}


function addComputerstatusAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerstatusAjax.action",
		type: 'post',
		data:computerstatusFormToJson(),
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

function updateComputerstatusAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerstatusAjax.action",
		type: 'post',
		data:computerstatusFormToJson(),
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


function deleteComputerstatusAjax(){
	jQuery.ajax({
		url: "deleteComputerstatusAjax.action",
		type: 'GET',
		data:$('#AddComputerstatusForm').serialize(),
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
