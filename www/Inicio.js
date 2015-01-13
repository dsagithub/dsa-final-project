var API_BASE_URL = "http://localhost:8080/restaurapp-api/";
var USERNAME ; 


$("#button_login").click(function(e) {
   e.preventDefault();
   
   var login = new Object();
   login.username=$('#label_id_nombre').val();
   login.userpass=$('#id_password_login').val();

   Login(login);
});



$("#button_registrarse").click(function(e) {
   e.preventDefault();
   window.location.replace('RegistroUser.html');
});


function Login(login){
   var url= API_BASE_URL + '/users/login';
   var data = JSON.stringify(login);
   console.log(data);
   
   $.ajax({
      url:url,
      type:'POST',
      crossDomain: true,
      dataType:'json',
      contentType: '',
      data: data,
   }).done(function(data, status, jqxhr) {
            var info= data;
            console.log(info);
            console.log(info.loginSuccessful);
            if (info.loginSuccessful == true){
               $.cookie('username', info.username);
               $.cookie('username');
                     console.log(info.loginSuccessful);
   
            window.location.replace('Inicio.html');

            }
            else {      alert('contraseña incorrecta'); 
               console.log(info.loginSuccessful);
                        
            
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

		
		
		
	


