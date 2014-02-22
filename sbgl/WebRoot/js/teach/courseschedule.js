
function coursescheduleFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_courseschedule_id').val()
					

	};
	
	return params;
}


function addCoursescheduleAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCoursescheduleAjax.action",
		type: 'post',
		data:coursescheduleFormToJson(),
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

function updateCoursescheduleAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCoursescheduleAjax.action",
		type: 'post',
		data:coursescheduleFormToJson(),
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


function deleteCoursescheduleAjax(){
	jQuery.ajax({
		url: "deleteCoursescheduleAjax.action",
		type: 'GET',
		data:$('#AddCoursescheduleForm').serialize(),
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
