
$( document ).ready(function() 
{    
	$.ajax({
		url: 'webapi',
		type:'GET',
		dataType: 'json',
		success: function(json) 
		{
			var savHtml=null,dtechHtml=null;
			$.each(json,function()
			{
				if(this.name == "SAV")
				{
					savHtml = accordion(this.data);
				}
				else if(this.name == "DTECH")
				{
					dtechHtml = accordion(this.data);
				}
			});

			$("#savAccordion").append(savHtml);
			$("#dtechAccordion").append(dtechHtml);

			$("#savAccordion" ).accordion({
				heightStyle: "content",
				collapsible: true
			});

			$("#dtechAccordion" ).accordion({
				heightStyle: "content",
				collapsible: true
			});

			$( ".localisationAccordion" ).accordion({
				heightStyle: "content",
				collapsible: true
			});

			$( ".datepicker" ).datepicker({
				showOn: "button",
				buttonImage: "static/images/calendar2.png",
				buttonImageOnly: true,
				buttonText: "Select date",
				changeMonth: true,
				changeYear: true,
				dateFormat:"dd/mm/yy"
			});
		}
	});
});

function accordion(json)
{
	var html = "";
	// lot
	$.each(json,function()
			{
		html += "<h3>"+this.name+"</h3><div><div class=\"localisationAccordion\">";
		console.log(this);

		//localisation
		$.each(this.data,function()
				{
			html += "<h3>"+this.name+"</h3><div><div class=\"row\" \"form-group\">";
			console.log(this);

			//jalon
			$.each(this.data,function()
					{
				html += "<form><div class=\"row\"><div class=\"col-md-5 checkbox\"><label><input type=\"checkbox\" value=\"\">"+this.name+"</label></div><div class=\"col-md-7\"><input type=\"text\" placeholder=\"jj/mm/aaaa\" class=\"datepicker\"></div></div></form>";//<div class=\"col-md-6\">
				console.log(this);
					});
			html += "</div></div>";
				});
		html += "</div></div>";
			});
	return html;
}



function ajaxRequest()
{
	var yourObject = [{name:'sav',data:[{name:'lot1',data:[{name:'localisation1',data:[{name:'jalon1',id:'1'},{name:'jalon2',id:'2'}]},{name:'localisation2',data:[{name:'jalon2',id:'2'}]}]},{name:'lot2',data:[{name:'localisation2',data:[{name:'jalon2',id:2}]}]}]},{name:'dtech',data:[{name:'lot1',data:[{name:'localisation1',data:[{name:'jalon1',id:'1'},{name:'jalon2',id:'2'}]},{name:'localisation2',data:[{name:'jalon2',id:'2'}]}]},{name:'lot2',data:[{name:'localisation2',data:[{name:'jalon2',id:2}]}]}]}];

//	alert("!!! "+JSON.stringify(yourObject));

	return JSON.stringify(yourObject);
}

$("#validBtn").click(function() {

	window.location.href = "page3.jsp";
});




//$(function() {
//var dateDDMMYYYRegex = '^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)dd$';
//if($( ".datepicker" ).val().match(dateDDMMYYYRegex))
//{
//alert("ok");
//}
//});
