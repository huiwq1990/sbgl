
function courseconfigFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_courseconfig_id').val(),
					
					"schoolyear" : $('#input_courseconfig_schoolyear').val(),
					
					"semester" : $('#input_courseconfig_semester').val(),
					
					"firstday" : $('#input_courseconfig_firstday').val(),
					
					"lastday" : $('#input_courseconfig_lastday').val(),
					
					"firstweekfirstday" : $('#input_courseconfig_firstweekfirstday').val(),
					
					"status" : $('#input_courseconfig_status').val()
					

	};
	
	return params;
}


function addCourseconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCourseconfigAjax.action",
		type: 'post',
		data:courseconfigFormToJson(),
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

function updateCourseconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCourseconfigAjax.action",
		type: 'post',
		data:courseconfigFormToJson(),
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


function deleteCourseconfigAjax(){
	jQuery.ajax({
		url: "deleteCourseconfigAjax.action",
		type: 'GET',
		data:$('#AddCourseconfigForm').serialize(),
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
