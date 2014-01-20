
function computerorderclassruledetailFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerorderclassruledetail_id').val(),
					
					"computerorderclassruleid" : $('#input_computerorderclassruledetail_computerorderclassruleid').val(),
					
					"allowedcomputermodelid" : $('#input_computerorderclassruledetail_allowedcomputermodelid').val()
					

	};
	
	return params;
}


function addComputerorderclassruledetailAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerorderclassruledetailAjax.action",
		type: 'post',
		data:computerorderclassruledetailFormToJson(),
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

function updateComputerorderclassruledetailAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerorderclassruledetailAjax.action",
		type: 'post',
		data:computerorderclassruledetailFormToJson(),
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


function deleteComputerorderclassruledetailAjax(){
	jQuery.ajax({
		url: "deleteComputerorderclassruledetailAjax.action",
		type: 'GET',
		data:$('#AddComputerorderclassruledetailForm').serialize(),
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
