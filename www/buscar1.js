var API_BASE_URL = "http://localhost:8080/restaurapp-api/restaurantes";
var USERNAME = "admin";
var PASSWORD = "admin";


$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
});


$("#button_list_rest").click(function(e) {
	e.preventDefault();
	getlistrest();
});

//?page=3&per_page=5
function getlistrest() {

	var url = API_BASE_URL ;

	$("#restaurantes_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var gists = data;

				$.each(gists, function(i, v) {
					var res1 = v;
					$.each(res1, function(i, v) {
						var restaurantes = v;
							
					$('<h4> Name: ' + restaurantes.nombre + '</h4>').appendTo($('#restaurantes_result'));
					$('<p>').appendTo($('#restaurantes_result'));	
					
					
				});
				});

	}).fail(function() {
		$("#restaurantes_result").text("No gists.");
	});

}


