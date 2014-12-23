var API_BASE_URL = "http://localhost:8080/restaurapp-api/restaurantes/opinion";
var USERNAME = "xurtasun";
var PASSWORD = "urtasun";


$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(USERNAME+':'+PASSWORD) }
});
		/*	Botones desplegables	*/


$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });




$("#boton_comentario").click(function(e) {
  e.preventDefault();
    var newRest;
    if($('#nombre_restaurante').val()=="" || $('#titulo_opinion').val()=="" || $('#nombre_user').val()==""|| $('#fecha_estancia').val()==""|| $('#horario').val()==""|| $('#telefono_restaurante').val()=="" || $('#provincia_restaurante').val()=="" ){
        $('<div class="alert alert-info"> Rellena todos los campos </div>').appendTo
    ($("#create_result"));
    }else{
    newRest = {
      "titulo" : $('#nombre_restaurante').val(),
      "puntuacion" : $('#puntuacion_restaurante').val(),
      "username" : $('#nombre_user').val(),
      "fecha_estancia" : $('#fecha_estancia').val(),
      "horario" : $('#horario').val(),
      "nombre" : $('#nombre_restaurante').val(),
	  "provincia" : $('#provincia_restaurante').val(),
      "telefono" : $('#telefono_restaurante').val(),
	  "texto" : $('#opinion_restaurante').val(),
      
    }
    createOpinion(newRest);
  }
});


	/* var url = API_BASE_URL + '/' + id_restaurante; */
	
  
  function createOpinion(restaurante) {
  var url = API_BASE_URL  + '/20';
  var data = JSON.stringify(restaurante);
  $("#create_result").text('');
  
  $.ajax({
    url : url,
    type : 'POST',
    crossDomain : true,
    dataType : 'application/vnd.restaurapp.api.opinion+json',  
    contentType : 'application/vnd.restaurapp.api.opinion+json',
    data : data,      
    }).done(function(data, status, jqxhr) {
    $('<div class="alert alert-success"> <strong>Ok!</strong> Restaurante Creado!</div>').appendTo($("#create_result"));        
    }).fail(function() {
    $('<div class="alert alert-danger"> <strong>Oh!</strong> Error, restaurante no creado</div>').appendTo($("#create_result"));
  });   


};
  
  