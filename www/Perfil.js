var API_BASE_URL = "http://localhost:8080/restaurapp-api";

$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });

$("#button_listar_todos").click(function(e) {
	e.preventDefault();
	getRest();
});
/*

function getRest() {
	var url = API_BASE_URL + '/restaurantes';
	$("#listar_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var restaurantes = data;				
								
				$.each(restaurantes, function(i, v) {
					var restaurantes = v;

					$('<h4> Nombre Restaurante: ' + restaurantes.nombre + '</h4>').appendTo($('#listar_result'));
					$('<p>').appendTo($('#listar_result'));	
					$('<strong> Categoria: </strong> ' + restaurantes.categoria + '<br>').appendTo($('#listar_result'));
					$('<strong> Creador Restaurante: </strong> ' + restaurantes.creador + '<br>').appendTo($('#listar_result'));
					$('<strong> Horario: </strong> ' + restaurantes.horario + '<br>').appendTo($('#listar_result'));
					$('</p>').appendTo($('#listar_result'));
				});
				

	}).fail(function() {
		$("#listar_result").text("No hay restaurantes.");
	});

}

*/







/*



function getRest() {
	var url = API_BASE_URL + '/restaurantes';
	$("#listar_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var res = data;
				
				$.each(res, function(i, v) {
					var res1 = v;
					$.each(res1, function(i, v) {
					
					var restaurantes = v;

					
			$('<strong> Nombre Restaurante: </strong> ' + restaurantes.nombre + '<br>').appendTo($('#listar_result'));
					$('<strong> Categoria: </strong> ' + restaurantes.categoria + '<br>').appendTo($('#listar_result'));
					$('<strong> Creador Restaurante: </strong> ' + restaurantes.creador + '<br>').appendTo($('#listar_result')); 
					$.each(restaurantes, function(i, v)  {
						var list = v;
						
					$('<strong> muestro: </strong> ' + list[i] + '<br>').appendTo($('#listar_result'));
					});
					$('</p>').appendTo($('#listar_result'));
				});
				});

	}).fail(function() {
		$("#listar_result").text("No hay datos.");
	});
	
	}
	
	*/
	
	
	
	
function getRest() {
	var url = API_BASE_URL + '/restaurantes';
	$("#listar_result").text('');
	
	$.ajax({
		url : url,
		type : 'GET',
		crossDomain : true,
		dataType : 'json',
	}).done(function(data, status, jqxhr) {
				var res = data;
				
				$.each(res, function(i, v) {
					var res1 = v;
					$.each(res1, function(i, v) {
					var restaurantes = v;

					
					$('<strong> Nombre Restaurante: </strong> ' + restaurantes.nombre + '<br>').appendTo($('#listar_result'));
					$('<strong> Categoria: </strong> ' + restaurantes.categoria + '<br>').appendTo($('#listar_result'));
					$('<strong> Creador Restaurante: </strong> ' + restaurantes.creador + '<br>').appendTo($('#listar_result'));  
					$.each(restaurantes, function(i, v) {
						var list = v;
					$('<strong> listar: </strong> ' + list[i] + '<br>').appendTo($('#listar_result'));
					});
					$('</p>').appendTo($('#listar_result'));
				});
				});

	}).fail(function() {
		$("#listar_result").text("No hay datos.");
	});
	
	}

	

	


