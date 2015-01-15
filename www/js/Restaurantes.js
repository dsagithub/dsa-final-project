var API_BASE_URL = "http://localhost:8000/restaurapp-api/";

var usernameLogin = getCookie("username");
var userpass = getCookie("userpass")

data = location.search.substring(1,location.search.length);

dataRest = data.split("@");
idrestaurante = dataRest[1];

dataFiltro = data.split("&");
provincia = dataFiltro[1];
categoria = dataFiltro[2];

dataOpinion = data.split("%");
idopinion = dataOpinion[1];




console.log(usernameLogin);
console.log(userpass);

$.ajaxSetup({
    headers: { 'Authorization': "Basic "+ btoa(usernameLogin+':'+userpass) }

});



$("#button_list_rest").click(function(e) {
   e.preventDefault();
   getRestaurantes();
});
$("#button_login").click(function(e) {
   e.preventDefault();
   
   var login = new Object();
   login.username=$('#id_nombre_login').val();
   login.password=$('#id_password_login').val();

   document.cookie = "userpass = " + login.password;

   Login(login);
});

$("#button_buscar").click(function(e) {
   e.preventDefault();

   var provincia = $('#provincia').val();
   var categoria = $('#categoria').val();
   window.location.replace('Listar.html?&'+provincia+'&'+categoria);
});


$("#button_registrarse").click(function(e) {
   e.preventDefault();
   window.location.replace('RegistroUser.html');
});


$("#boton_registrar_restaurante").click(function(e) {
   e.preventDefault();

   var restaurante = new Object();
   restaurante.nombre=$('#nombre_rest').val();
   restaurante.direccion=$('#direccion_rest').val();
   restaurante.email=$('#email_rest').val();
   restaurante.telefono=$('#telefono_rest').val();
   restaurante.horario=$('#horario_rest').val();
   restaurante.categoria=$('#categoria_rest').val();
   restaurante.provincia=$('#provincia_rest').val();

   registrarRestaurante(restaurante);

});

$("#button_nueva_opinion").click(function(e) {
   e.preventDefault();

   var opinion = new Object();

   opinion.titulo =$('#id_titulo_opinion').val();
   opinion.puntuacion =$('#id_puntuacion_opinion').val();
   opinion.username = getCookie("username");
   opinion.texto = $('#id_texto_opinion').val();
   opinion.fecha_estancia = $('#id_mes_estancia').val();

   createOpinion(opinion);
});

$("#button_registrarse").click(function(e) {
   e.preventDefault();
   window.location.replace('RegistroUser.html');
});

$("#boton_nuevo_user").click(function(e) {
  e.preventDefault();
    var nuevoUser = new Object();
    if($('#name_nuevo').val()=="" || $('#username_nuevo').val()=="" || $('#email_nuevo').val()=="" || $('#userpass_nuevo').val()=="" || $('#provincia_nuevo').val()==""){
        alert('Todos los campos son obligatorios!!')   
    }
    else{
      nuevoUser.nombre = $('#name_nuevo').val();
      nuevoUser.username = $('#username_nuevo').val();
      nuevoUser.email = $('#email_nuevo').val();
      nuevoUser.password = $('#userpass_nuevo').val();
      nuevoUser.provincia = $('#provincia_nuevo').val();


    }
    crearUser(nuevoUser);
  });
$("#logout").click(function(e) {
   e.preventDefault();
   Logout();
});


function getLogin(){
   console.log("LOGIN2");

   var Boologin = getCookie("BoolLogin");
   var username = getCookie("username");

   console.log ("USERNAME LOGGED==" + username);
   console.log("BoolLogin== "+ Boologin);

   if (Boologin=="true"){
   $("#name_login").text('');
   console.log ("WRITING==" + username);

   $("<b>"+ username +" </b>").appendTo($("#name_login"));
   $("#div_tohide").hide(50);   
   
   }
   else{
   $("#name_login").text('');
   $("<b> No estás Logeado</b>").appendTo($("#name_login"));
   }  
}

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
            console.log(data.loginSuccesful);
            if (data.loginSuccesful == true){
		
         		document.cookie="username = " + data.username;
               document.cookie="BoolLogin = " + data.loginSuccesful;

         		$("#name_login").text('');
         		$("<b>"+ getCookie("username") +" </b>").appendTo($("#name_login"));   
               $("#div_tohide").hide(50);   

            }
            else {      
		alert('Usuario o contraseña incorrectos'); 
                console.log(data.loginSuccesful);
                        
            
         }
       

   }).fail(function() {
       alert('Usuario o contraseña incorrectos');  
   });


}

function Logout(){

document.cookie="username = "+ "";
document.cookie="BoolLogin = " + "";

$("#name_login").text('');
               $("<b>"+ "No estas logeado" +" </b>").appendTo($("#name_login"));   
               $("#div_tohide").show(50); 
}

function getRestaurantes(){
   

   $("#restaurantes_result").text('');

   var url = API_BASE_URL + "restaurantes/search?provincia="+provincia+"&categoria="+categoria;
   
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
               $('<h4> Restaurante </h4>').appendTo($('#restaurantes_result'));
               $('<a href="detalles.html?@'+id+'" <h4>' + restaurantes.nombre + '</h4></a>').appendTo($('#restaurantes_result'));
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

               $('<a href="posicion.html?'+restaurante.direccion+', '+ restaurante.provincia + '">clica aqui</a>').appendTo($('#posicion_google'));
       
   }).fail(function() {
      $("#restaurante_result_detail").text("El restaurante no existe.");
   });

   if(idopinion!= null){
   getopinion();
   }
   else{
   getlistopinion();
   }
}


function getlistopinion() {

   var url = API_BASE_URL +'restaurantes/opinion/'+idrestaurante;

   $("#restaurante_result_opinion").text('');
   
   $.ajax({
      url : url,
      type : 'GET',
      crossDomain : true,
      dataType : 'json',
   }).done(function(data, status, jqxhr) {
            var big = data;

            $.each(big, function(i, v) {

               var opiniones = v;
                  $.each(opiniones, function(i, v) {
                     var opinion = v;
                     console.log(opinion.titulo);

                     if (opinion.titulo == null){
                     }
                     else{
                        $('<a href="detalles.html?@'+opinion.idrestaurante+'@%'+opinion.idopinion+'%"><strong>' + opinion.titulo + '</strong> </a>').appendTo($('#restaurante_result_opinion'));
                        $('<p>').appendTo($('#restaurante_result_opinion')); 
                        $('<h6> Usuario: ' + opinion.username + '</h6>').appendTo($('#restaurante_result_opinion'));
                        $('<h6> Puntuacion: '+ opinion.puntuacion + '</h6>').appendTo($('#restaurante_result_opinion'));
                        $('<button> <FONT color="orange" size="1" id="button_like"> LIKE </FONT></button>  <button> <FONT color="red" size="1" id="button_unlike"> UNLIKE </FONT></button>').appendTo($('#restaurante_result_opinion'));
                        $('<p>').appendTo($('#restaurante_result_opinion')); 

                     }
               
                });
            });

   }).fail(function() {
      $("#restaurante_result_opinion").text("No hay opiniones.");
   });

}


function getopinion() {

   var url = API_BASE_URL +'restaurantes/opinion/'+idrestaurante+'/'+idopinion;

   $("#restaurante_result_opinion").text('');
   
   $.ajax({
      url : url,
      type : 'GET',
      crossDomain : true,
      dataType : 'json',
   }).done(function(data, status, jqxhr) {

               var opinion = data;

                     console.log(opinion.titulo);

                     if (opinion.titulo == null){
                     }
                     else{

                         var date = new Date(opinion.creation_timestamp*1000);
                         var _mes=date.getMonth()+1; //getMonth devuelve el mes empezando por 0
                         var _dia=date.getDate(); //getDate devuelve el dia del mes
                         var _anyo=date.getFullYear();

                        $('<strong>' + opinion.titulo + '</strong>').appendTo($('#restaurante_result_opinion'));
                        $('<p>').appendTo($('#restaurante_result_opinion')); 
                        $('<h6><strong> Usuario: </strong>' + opinion.username + '</h6>').appendTo($('#restaurante_result_opinion'));
                        $('<h6><strong> Puntuacion: </strong>'+ opinion.puntuacion + '</h6>').appendTo($('#restaurante_result_opinion'));
                        $('<h6><strong> Mes estancia: </strong>' + opinion.fecha_estancia + '</h6>').appendTo($('#restaurante_result_opinion'));
                        $('<h6><strong> Comentario: </strong>' + opinion.texto + '</h6>').appendTo($('#restaurante_result_opinion'));
                     }
   }).fail(function() {
      $("#restaurante_result_opinion").text("No hay opiniones.");
   });

}

function registrarRestaurante(restaurante){

   var url= API_BASE_URL + 'restaurantes';
   var data = JSON.stringify(restaurante);
   console.log(data);
   
   $.ajax({
      url:url,
      type:'POST',
      crossDomain: true,
      dataType:'json',
      contentType: 'application/vnd.restaurapp.api.restaurante+json',
      data: data,
   }).done(function(data, status, jqxhr) {

               alert('El restaurante se ha registrado correctamente, para verlo entre en http://localhost/detalles.html?@'+data.idrestaurante); 
                 

   }).fail(function() {
       alert('Necesitas estar logeado');  
   });


}

  function createOpinion(opinion) {
  var url = API_BASE_URL  + 'restaurantes/opinion/'+idrestaurante;
  var data = JSON.stringify(opinion);
  
  if(getCookie("username")== ""){

   alert('No estas logeado');
         
    }
    else{
      $.ajax({
       url : url,
       type : 'POST',
       crossDomain : true,
       dataType : 'json',  
       contentType : 'application/vnd.restaurapp.api.opinion+json',
       data : data,      
       }).done(function(data, status, jqxhr) {

         getlistopinion();

       }).fail(function() {
         alert('No estas autorizado');
     }); 

    }


};


function crearUser(user) {
  var url = API_BASE_URL + 'users';
  var data = JSON.stringify(user);
  
    $.ajax({
    url : url,
    type : 'POST',
    crossDomain : true,
    dataType : 'json',  
    contentType : 'application/vnd.restaurapp.api.user+json',
    data : data,      
    }).done(function(data, status, jqxhr) {
         alert('Listo!! Ya te puedes logear!')    
    }).fail(function() {
         alert('Registro de usuaria fallido, pongase en contacto con los administradores!')
  });   


};






function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
    }
    return "";
}



$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {

      var $target = $( event.currentTarget );

      $target.closest( '.btn-group' )
         .find( '[data-bind="label"]' ).text( $target.text() )
            .end()
         .children( '.dropdown-toggle' ).dropdown( 'toggle' );

      return false;

   });

$(document).ready(function(){
   getLogin();
})

		
		
		
	


