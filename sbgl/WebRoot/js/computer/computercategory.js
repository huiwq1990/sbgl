
function computercategoryFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computercategory_id').val(),
					
					"name" : $('#input_computercategory_name').val(),
					
					"createtime" : $('#input_computercategory_createtime').val(),
					
					"createuserid" : $('#input_computercategory_createuserid').val(),
					
					"status" : $('#input_computercategory_status').val()
					

	};
	
	return params;
}


function addComputercategoryAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputercategoryAjax.action",
		type: 'post',
		data:computercategoryFormToJson(),
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

function updateComputercategoryAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputercategoryAjax.action",
		type: 'post',
		data:computercategoryFormToJson(),
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


function deleteComputercategoryAjax(){
	jQuery.ajax({
		url: "deleteComputercategoryAjax.action",
		type: 'GET',
		data:$('#AddComputercategoryForm').serialize(),
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
