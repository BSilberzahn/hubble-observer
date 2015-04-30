/** bouton Email **/
$("#emailBtn").click(function() {
	var txt = $("#inputEmail").val();
	addToList( txt );
	$("#inputEmail").val("");
});

$("#validBtn").click(function() {
	var name = $("#projectName").val();
	alert("coucou "+name);
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