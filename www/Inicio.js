var API_BASE_URL = "http://localhost:8000/restaurapp-api/";

var login = false;
var usernameLogin;
var userpass;


idrestaurante = location.search.substring(1,location.search.length);

getLogin();
getRestaurantes();
getRestauranteDetail();

$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(usernameLogin+':'+userpass) }

});

function getLogin(){

	if (login==true){
	$("").appendTo($("#name_login"));
	$("<b>"+ usernameLogin +" </b>").appendTo($("#name_login"));	
	
	}
	if (login==false){
	$("").appendTo($("#name_login"));
	$("<b> No estás Logeado</b>").appendTo($("#name_login"));

	}	
}

$("#button_list_rest").click(function(e) {
   e.preventDefault();
   getRestaurantes();
});
$("#button_login").click(function(e) {
   e.preventDefault();
   
   var login = new Object();
   login.username=$('#id_nombre_login').val();
   login.password=$('#id_password_login').val();

   userpass = login.password;

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
		$("").appendTo($("#name_login"));
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

function getRestaurantes(){
   var url = API_BASE_URL + "restaurantes";

   $("#restaurantes_result").text('');
   
   $.ajax({
      url : url,
      type : 'GET',
      crossDomain : true,
      dataType : 'json',
   }).done(function(data, status, jqxhr) {
            var big = data;

            $.each(big, function(i, v) {

               var small = v;
               $.each(small, function(i, v) {
                  var restaurantes = v;
               console.log(restaurantes.nombre);

               if (restaurantes.nombre == null){
               }
               else {   
               var id= restaurantes.idrestaurante;
               $('<a href="detalles.html?'+id+'" <h4> Name: ' + restaurantes.nombre + '</h4></a>').appendTo($('#restaurantes_result'));
               $('<p>').appendTo($('#restaurantes_result'));   
               }
               
            });
            });

   }).fail(function() {
      $("#restaurantes_result").text("No hay restaurantes en la base de datos.");
   });
}
function getRestauranteDetail(){
   var url = API_BASE_URL + 'restaurantes/'+idrestaurante;

   $("#restaurante_result_detail").text('');
   
   $.ajax({
      url : url,
      type : 'GET',
      crossDomain : true,
      dataType : 'json',
   }).done(function(data, status, jqxhr) {
               var restaurante = data;
               $('<strong>' + restaurante.nombre +'</strong>').appendTo($('#restaurante_result_detail'));
               $('<p>').appendTo($('#restaurante_result_detail'));   
               $('<h6>' + restaurante.creador+ '</h6>').appendTo($('#restaurante_result_detail'));
               $('<h6>'+ restaurante.direccion + '</h6>').appendTo($('#restaurante_result_detail'));
               $('<h6>'+ restaurante.email + '</h6>').appendTo($('#restaurante_result_detail'));
               $('<h6>'+ restaurante.horario + '</h6>').appendTo($('#restaurante_result_detail'));
               $('<h6>'+ restaurante.provincia + '</h6>').appendTo($('#restaurante_result_detail'));
               $('<h6>'+ restaurante.telefono +'</h6>').appendTo($('#restaurante_result_detail'));
               
   }).fail(function() {
      $("#restaurante_result_detail").text("El restaurante no existe.");
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

		
		
		
	


