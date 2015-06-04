$( document ).ready(function() 
{ 
	var emailString = Cookies.get('emailString');

	$('#list').append(
			$('<p>', {
				text: ''+emailString
			})
	);
});