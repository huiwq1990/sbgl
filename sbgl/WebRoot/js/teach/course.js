
function courseFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_course_id').val(),
					
					"name" : $('#input_course_name').val(),
					
					"description" : $('#input_course_description').val(),
					
					"type" : $('#input_course_type').val(),
					
					"adduserid" : $('#input_course_adduserid').val(),
					
					"teacherid" : $('#input_course_teacherid').val(),
					
					"addtime" : $('#input_course_addtime').val()
					

	};
	
	return params;
}


function addCourseAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCourseAjax.action",
		type: 'post',
		data:courseFormToJson(),
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

function updateCourseAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCourseAjax.action",
		type: 'post',
		data:courseFormToJson(),
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


function deleteCourseAjax(){
	jQuery.ajax({
		url: "deleteCourseAjax.action",
		type: 'GET',
		data:$('#AddCourseForm').serialize(),
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
