
function coursecomputerFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_coursecomputer_id').val()
					

	};
	
	return params;
}


function addCoursecomputerAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCoursecomputerAjax.action",
		type: 'post',
		data:coursecomputerFormToJson(),
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

function updateCoursecomputerAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCoursecomputerAjax.action",
		type: 'post',
		data:coursecomputerFormToJson(),
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


function deleteCoursecomputerAjax(){
	jQuery.ajax({
		url: "deleteCoursecomputerAjax.action",
		type: 'GET',
		data:$('#AddCoursecomputerForm').serialize(),
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
