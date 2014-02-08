
function computerconfigFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerconfig_id').val(),
					
					"name" : $('#input_computerconfig_name').val(),
					
					"value" : $('#input_computerconfig_value').val()
					

	};
	
	return params;
}


function addComputerconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerconfigAjax.action",
		type: 'post',
		data:computerconfigFormToJson(),
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

function updateComputerconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerconfigAjax.action",
		type: 'post',
		data:computerconfigFormToJson(),
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


function deleteComputerconfigAjax(){
	jQuery.ajax({
		url: "deleteComputerconfigAjax.action",
		type: 'GET',
		data:$('#AddComputerconfigForm').serialize(),
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
