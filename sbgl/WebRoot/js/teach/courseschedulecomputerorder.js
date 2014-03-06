
function courseschedulecomputerorderFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_courseschedulecomputerorder_id').val(),
					
					"computercoursescheduleid" : $('#input_courseschedulecomputerorder_computercoursescheduleid').val(),
					
					"computerorderid" : $('#input_courseschedulecomputerorder_computerorderid').val(),
					
					"status" : $('#input_courseschedulecomputerorder_status').val()
					

	};
	
	return params;
}


function addCourseschedulecomputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addCourseschedulecomputerorderAjax.action",
		type: 'post',
		data:courseschedulecomputerorderFormToJson(),
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

function updateCourseschedulecomputerorderAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateCourseschedulecomputerorderAjax.action",
		type: 'post',
		data:courseschedulecomputerorderFormToJson(),
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


function deleteCourseschedulecomputerorderAjax(){
	jQuery.ajax({
		url: "deleteCourseschedulecomputerorderAjax.action",
		type: 'GET',
		data:$('#AddCourseschedulecomputerorderForm').serialize(),
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
