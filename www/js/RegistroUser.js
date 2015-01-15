 var API_BASE_URL = "http://localhost:8080/restaurapp-api/users";



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
    var nuevoUser;
    if($('#nombre_registro').val()=="" || $('#email_registro').val()=="" || $('#nombre_user_registro').val()=="" || $('#password_registro').val()=="" ){
        $('<div class="alert alert-info"> Rellena los campos descripcion y contenido</div>').appendTo
    ($("#update_result"));
    }else{
    nuevoUser = {
      "username" : $('#nombre_user_registro').val(),
      "password" : $('#password_registro').val(),
      "nombre" : $('#nombre_registro').val(),
      "email" : $('#email_registro').val(),
      "provincia" : $('#email_registro').val(),
      "ubic_foto" : $('#email_registro').val(),
      
    }
    crearUser(nuevoUser);
  }
});



function crearUser(user) {
  var url = API_BASE_URL ;
  var data = JSON.stringify(user);
  $("#update_result").text('');
  
  $.ajax({
    url : url,
    type : 'POST',
    crossDomain : true,
    dataType : 'application/vnd.restaurapp.api.user+json',  
    contentType : 'application/vnd.restaurapp.api.user+json',
    data : data,      
    }).done(function(data, status, jqxhr) {
    $('<div class="alert alert-success"> <strong>Ok!</strong> Usuario Creado!</div>').appendTo($("#update_result"));        
    }).fail(function() {
    $('<div class="alert alert-danger"> <strong>Oh!</strong> Error, usuario no creado</div>').appendTo($("#update_result"));
  });   


};

