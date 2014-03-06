
function coursecomputerorderFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_coursecomputerorder_id').val(),
					
					"semesterid" : $('#input_coursecomputerorder_semesterid').val(),
					
					"courseid" : $('#input_coursecomputerorder_courseid').val(),
					
					"computerorderid" : $('#input_coursecomputerorder_computerorderid').val(),
					
					"status" : $('#input_coursecomputerorder_status').val()
					

	};
	
	return params;
}


function addCoursecomputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCoursecomputerorderAjax.action",
		type: 'post',
		data:coursecomputerorderFormToJson(),
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

function updateCoursecomputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCoursecomputerorderAjax.action",
		type: 'post',
		data:coursecomputerorderFormToJson(),
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


function deleteCoursecomputerorderAjax(){
	jQuery.ajax({
		url: "deleteCoursecomputerorderAjax.action",
		type: 'GET',
		data:$('#AddCoursecomputerorderForm').serialize(),
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
