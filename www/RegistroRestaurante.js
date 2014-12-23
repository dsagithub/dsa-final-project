var API_BASE_URL = "http://localhost:8080/restaurapp-api";
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




$("#button_registro").click(function(e) {
  e.preventDefault();
    var newRest;
    if($('#categoria_registro').val()=="" || $('#user_registro').val()=="" || $('#direccion').val()==""|| $('#email_registro').val()==""|| $('#direccion').val()==""|| $('#direccion').val()=="" || $('#telefono').val()=="" ){
        $('<div class="alert alert-info"> Rellena todos los campos </div>').appendTo
    ($("#create_result"));
    }else{
    newRest = {
      "categoria" : $('#categoria_registro').val(),
      "creador" : $('#user_registro').val(),
      "direccion" : $('#direccion').val(),
      "email" : $('#email_registro').val(),
      "horario" : $('#horario_registro').val(),
      "nombre" : $('#restaurante_registro').val(),
	  "provincia" : $('#provincia_registro').val(),
      "telefono" : $('#telefono').val(),
      
    }
    createRest(newRest);
  }
});



  
  
  function createRest(restaurante) {
  var url = API_BASE_URL  + '/restaurantes';
  var data = JSON.stringify(restaurante);
  $("#create_result").text('');
  
  $.ajax({
    url : url,
    type : 'POST',
    crossDomain : true,
    dataType : 'application/vnd.restaurapp.api.restaurante+json',  
    contentType : 'application/vnd.restaurapp.api.restaurante+json',
    data : data,      
    }).done(function(data, status, jqxhr) {
    $('<div class="alert alert-success"> <strong>Ok!</strong> Restaurante Creado!</div>').appendTo($("#create_result"));        
    }).fail(function() {
    $('<div class="alert alert-danger"> <strong>Oh!</strong> Error, restaurante no creado</div>').appendTo($("#create_result"));
  });   


};
  
  