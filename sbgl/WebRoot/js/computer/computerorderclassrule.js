
function computerorderclassruleFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerorderclassrule_id').val(),
					
					"name" : $('#input_computerorderclassrule_name').val(),
					
					"classname" : $('#input_computerorderclassrule_classname').val(),
					
					"classid" : $('#input_computerorderclassrule_classid').val(),
					
					"orderstarttime" : $('#input_computerorderclassrule_orderstarttime').val(),
					
					"orderendtime" : $('#input_computerorderclassrule_orderendtime').val(),
					
					"availableordertime" : $('#input_computerorderclassrule_availableordertime').val(),
					
					"createuserid" : $('#input_computerorderclassrule_createuserid').val(),
					
					"createtime" : $('#input_computerorderclassrule_createtime').val(),
					
					"status" : $('#input_computerorderclassrule_status').val()
					

	};
	
	return params;
}


function addComputerorderclassruleAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerorderclassruleAjax.action",
		type: 'post',
		data:computerorderclassruleFormToJson(),
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

function updateComputerorderclassruleAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerorderclassruleAjax.action",
		type: 'post',
		data:computerorderclassruleFormToJson(),
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


function deleteComputerorderclassruleAjax(){
	jQuery.ajax({
		url: "deleteComputerorderclassruleAjax.action",
		type: 'GET',
		data:$('#AddComputerorderclassruleForm').serialize(),
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
