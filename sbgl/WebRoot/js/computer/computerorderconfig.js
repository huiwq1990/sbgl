
function computerorderconfigFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerorderconfig_id').val(),
					
					"openorder" : $('#input_computerorderconfig_openorder').val(),
					
					"orderperiod" : $('#input_computerorderconfig_orderperiod').val(),
					
					"orderperiodnum" : $('#input_computerorderconfig_orderperiodnum').val(),
					
					"maxorderday" : $('#input_computerorderconfig_maxorderday').val(),
					
					"orderintroduction" : $('#input_computerorderconfig_orderintroduction').val(),
					
					"createuserid" : $('#input_computerorderconfig_createuserid').val(),
					
					"createtime" : $('#input_computerorderconfig_createtime').val(),
					
					"currentconfig" : $('#input_computerorderconfig_currentconfig').val(),
					
					"status" : $('#input_computerorderconfig_status').val()
					

	};
	
	return params;
}


function addComputerorderconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerorderconfigAjax.action",
		type: 'post',
		data:computerorderconfigFormToJson(),
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

function updateComputerorderconfigAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerorderconfigAjax.action",
		type: 'post',
		data:computerorderconfigFormToJson(),
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


function deleteComputerorderconfigAjax(){
	jQuery.ajax({
		url: "deleteComputerorderconfigAjax.action",
		type: 'GET',
		data:$('#AddComputerorderconfigForm').serialize(),
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
