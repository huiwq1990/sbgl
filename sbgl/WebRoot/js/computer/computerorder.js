
function computerorderFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerorder_id').val(),
					
					"serialnumber" : $('#input_computerorder_serialnumber').val(),
					
					"userid" : $('#input_computerorder_userid').val(),
					
					"createtime" : $('#input_computerorder_createtime').val(),
					
					"status" : $('#input_computerorder_status').val()
					

	};
	
	return params;
}


function addComputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerorderAjax.action",
		type: 'post',
		data:computerorderFormToJson(),
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

function updateComputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerorderAjax.action",
		type: 'post',
		data:computerorderFormToJson(),
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


function deleteComputerorderAjax(){
	jQuery.ajax({
		url: "deleteComputerorderAjax.action",
		type: 'GET',
		data:$('#AddComputerorderForm').serialize(),
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
