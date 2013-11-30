
function computermodelFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computermodel_id').val(),
					
					"name" : $('#input_computermodel_name').val(),
					
					"computercategoryid" : $('#input_computermodel_computercategoryid').val(),
					
					"picpath" : $('#input_computermodel_picpath').val(),
					
					"createtime" : $('#input_computermodel_createtime').val(),
					
					"createuserid" : $('#input_computermodel_createuserid').val(),
					
					"count" : $('#input_computermodel_count').val(),
					
					"status" : $('#input_computermodel_status').val()
					

	};
	
	return params;
}


function addComputermodelAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputermodelAjax.action",
		type: 'post',
		data:computermodelFormToJson(),
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

function updateComputermodelAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputermodelAjax.action",
		type: 'post',
		data:computermodelFormToJson(),
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


function deleteComputermodelAjax(){
	jQuery.ajax({
		url: "deleteComputermodelAjax.action",
		type: 'GET',
		data:$('#AddComputermodelForm').serialize(),
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
