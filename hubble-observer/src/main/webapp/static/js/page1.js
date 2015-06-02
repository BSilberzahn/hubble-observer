var emailArray = new Array(); 

$( document ).ready(function() {  
	projectName = "";
	emailArray = new Array();
});

/** bouton Email **/
$("#emailBtn").click(function() {
	var mail = $("#inputEmail").val();
	if(mail != "")
	{
		addToList( mail );
		emailArray[emailArray.length] = mail;
		$("#inputEmail").val("");
	}
});

/** bouton validation **/
$("#validBtn").click(function() {
	projectName = $("#projectName").val();
	if(emailArray.length != 0 && projectName != "")
	{
		Cookies.set('projectName', projectName);
		
		var jsonCookie ="";var i=1;
		$.each( emailArray, function( key, value ) {
			jsonCookie += "email."+i+"="+value+"&";
			i++;
		});
		jsonCookie = jsonCookie.substring(0,jsonCookie.length-1);
		Cookies.set('email', jsonCookie);
		Cookies.set('nameProject', "name="+projectName);
		
		window.location.href = "page2.jsp";
	}else{
		alert("Au moins une adresse mail et un nom de projet doivent être entré");
	}
});


function addToList( txt) {

	

	
	
	$('#emailList').append(
			$('<li/>', {
				"class": 'list-group-item',
				text: ''+txt
			})
	);
	
//	.html(
//			$('<div/>', {
//				text: ''+txt
//			})
//	).append(
//			$('<button/>', {
//				"type": "button",
//				"class": 'close',
//				"aria-label":"Close",
//				click: function () { 
//					$(this).parent().remove();
//				}
//			})
//	).html('<span aria-hidden="true">&times;</span>');
//	
	
	
//	.html('<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>');
	
	
//	$('.delete-id-'+id).parent().parent().remove();
	
//	<button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>
//	$('li.unique-id-'+id).html(
//			$('<div/>', {
//				"class": 'row unique-id-'+id,
//			})
//	);
//	
//	
//	$('.row.unique-id-'+id).html(
//			$('<div/>', {
//				text: ''+txt,
//				"class": 'col-lg-8',
//			})
//	);
//	
//	$('.row.unique-id-'+id).append(
//			$('<div/>', {
//				"class": 'col-lg-2 alter-id-'+id,
//			})
//	);
//	
//	$('.row.unique-id-'+id).append(
//			$('<div/>', {
//				"class": 'col-lg-2 delete-id-'+id,
//			})
//	);
//
//	
//	$('.alter-id-'+id).html(
//			$('<button/>', {
//				text: 'Modifier',
//				"class": 'btn btn-primary',
//				click: function () { 
//					reloadText(id);
//				}
//			})
//	);
//	
//
//	$('.delete-id-'+id).html(
//			$('<button/>', {
//				text: 'Supprimer',
//				"data-id": id,
//				"class": 'btn btn-danger',
//				click: function (){
//					ajaxDelete(id);
//				}
//			})
//	);
};