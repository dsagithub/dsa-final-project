var API_BASE_URL = "http://localhost:8080/restaurapp-api/";
var USERNAME = "xxx";
var PASSWORD = "xxxx";












$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });

		
		
		
	


