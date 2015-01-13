var API_BASE_URL = "http://localhost:8080/restaurapp-api";
var USERNAME = "xurtasun";
var PASSWORD = "urtasun";

$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
});

$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });


$("#boton_eliminar_restaurante").click(function(e) {
	e.preventDefault();
	borra_res($("#rest_delete").val());

});

function borra_res(elimina_res) {  
	var url = API_BASE_URL + '/restaurantes/' + elimina_res;
	var data = JSON.stringify(elimina_res);
	$("#gists_result").text('');
	$.ajax({
		url : url,
		type : 'DELETE',
		crossDomain : true,
		dataType : 'json',	
		data : data,			
		}).done(function(data, status, jqxhr) {
		$('<div class="alert alert-success"> <strong>Ok!</strong> restaurante eliminado correctamente</div>').appendTo($("#gists_result"));				
  	}).fail(function() {
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Error!!!!! </div>').appendTo($("#gists_result"));
	});	
	
}


	

	


