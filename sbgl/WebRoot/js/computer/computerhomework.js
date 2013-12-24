
function computerhomeworkFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerhomework_id').val(),
					
					"name" : $('#input_computerhomework_name').val(),
					
					"computerorderclassruleid" : $('#input_computerhomework_computerorderclassruleid').val(),
					
					"content" : $('#input_computerhomework_content').val(),
					
					"createuserid" : $('#input_computerhomework_createuserid').val(),
					
					"attachment" : $('#input_computerhomework_attachment').val(),
					
					"status" : $('#input_computerhomework_status').val(),
					
					"createtime" : $('#input_computerhomework_createtime').val()
					

	};
	
	return params;
}


function addComputerhomeworkAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerhomeworkAjax.action",
		type: 'post',
		data:computerhomeworkFormToJson(),
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

function updateComputerhomeworkAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerhomeworkAjax.action",
		type: 'post',
		data:computerhomeworkFormToJson(),
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


function deleteComputerhomeworkAjax(){
	jQuery.ajax({
		url: "deleteComputerhomeworkAjax.action",
		type: 'GET',
		data:$('#AddComputerhomeworkForm').serialize(),
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
