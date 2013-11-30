
function computerorderdetailFormToJson(){
	var params = {
	//chang this below
					"id" : $('#input_computerorderdetail_id').val(),
					
					"computerorderid" : $('#input_computerorderdetail_computerorderid').val(),
					
					"computerid" : $('#input_computerorderdetail_computerid').val(),
					
					"computernumber" : $('#input_computerorderdetail_computernumber').val(),
					
					"createtime" : $('#input_computerorderdetail_createtime').val(),
					
					"borrowday" : $('#input_computerorderdetail_borrowday').val(),
					
					"borrowperiod" : $('#input_computerorderdetail_borrowperiod').val(),
					
					"status" : $('#input_computerorderdetail_status').val()
					

	};
	
	return params;
}


function addComputerorderdetailAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/addComputerorderdetailAjax.action",
		type: 'post',
		data:computerorderdetailFormToJson(),
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

function updateComputerorderdetailAjax(){
	jQuery.ajax({
		url: "http://localhost:8080/sbgl/updateComputerorderdetailAjax.action",
		type: 'post',
		data:computerorderdetailFormToJson(),
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


function deleteComputerorderdetailAjax(){
	jQuery.ajax({
		url: "deleteComputerorderdetailAjax.action",
		type: 'GET',
		data:$('#AddComputerorderdetailForm').serialize(),
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
