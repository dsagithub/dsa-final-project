/* var API_BASE_URL = "C:\Users\Javier\Desktop\web\registro_user";
*/



/*
Details about repository of GitHub API 
https://developer.github.com/v3/repos/
*/

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
    var nuevoGist;
    if($('#nombre_registro').val()==""){
    		$('<div class="alert alert-info"> Rellena el campo nombre </div>').appendTo
    ($("#nombre_registro"));
    }
				
			
		
		
	}
);

