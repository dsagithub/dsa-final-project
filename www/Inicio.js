var API_BASE_URL = "http://localhost:8000/restaurapp-api/";

var login = false;
var usernameLogin;

getLogin();

function getLogin(){

	if (login==true){

	$("<b>"+ usernameLogin +" </b>").appendTo($("#name_login"));	
	
	}
	if (login==false){
	
	$("<b> No estás Logeado</b>").appendTo($("#name_login"));

	}	
}


$("#button_login").click(function(e) {
   e.preventDefault();
   
   var login = new Object();
   login.username=$('#id_nombre_login').val();
   login.password=$('#id_password_login').val();

   Login(login);
});



$("#button_registrarse").click(function(e) {
   e.preventDefault();
   window.location.replace('RegistroUser.html');
});


function Login(login){
   var url= API_BASE_URL + 'users/login';
   var data = JSON.stringify(login);
   console.log(data);
   
   $.ajax({
      url:url,
      type:'POST',
      crossDomain: true,
      dataType:'json',
      contentType: 'application/vnd.restaurapp.api.user+json',
      data: data,
   }).done(function(data, status, jqxhr) {
            console.log(data);
            console.log(data.loginSuccesful);
            if (data.loginSuccesful == true){
		
		login = data.loginSuccesful;
		usernameLogin = data.username;
                console.log(login);
		$("<b>"+ data.username +" </b>").appendTo($("#name_login"));   
            }
            else {      
		alert('contraseña incorrecta'); 
                console.log(data.loginSuccesful);
                        
            
         }
       

   }).fail(function() {
       alert('Username o contraseña incorrectos');  
   });


}




$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });

		
		
		
	


