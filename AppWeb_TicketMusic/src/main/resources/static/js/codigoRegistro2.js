

    function checkInput(idInput, classError){
        if($(idInput).val() == ""){
            $(classError).show();
            return false;
        }
        else{
            $(classError).hide();
            return true;
        }
        
    }
    

    function checkEmail() {
        var emails = [];
        emails = /*[[${emails}]]*/ 'default';
        var email = $("#email2").val();
        var correcto = true;

        for(var i = 0; i < emails.length && correcto; i++){
            if(emails[i] === email){
                correcto = false;
                $(".error4").show();
            }
            else{
                $(".error4").hide();
            }
        }
        return correcto;
        
    }
   

    function comprobarContrasena(){
        var con1 = document.getElementById("pwd3");
        var con2 = document.getElementById("pwd4");

        if(con1.value.length < 8){
            $(".error5").show();
            return false;
        }
        else{
            $(".error5").hide();
        }
        
        if(con2.value.length == 0 || con1.value !== con2.value){
            $(".error6").show();
            return false;
        }
        
        $(".error6").hide();
        return true;
    }

    function comprobarEdad(){
        var hoy = new Date();
        
        var nac = document.getElementById("nacimiento");

        var fecha = new Date(nac.valueAsDate);
        var edad = hoy.getYear() - fecha.getYear();
        
        if( edad < 16){
            $(".error3").show();
            return false;
        }
        else if(edad == 16){
            if(hoy.getMonth() < fecha.getMonth()){
                $(".error3").show();
                return false;
            }
            else if(hoy.getMonth() == fecha.getMonth()){
                if(hoy.getDate() < fecha.getDate()){
                    $(".error3").show();
                    return false;
                }
            }

        }
        $(".error3").hide();
       

        return true;
    }


    function enableSubmit (idForm) {
      $(idForm + " button.submit").removeAttr("disabled");
    }
  
    function disableSubmit (idForm) {
      $(idForm + " button.submit").attr("disabled", "disabled");
    }

    function checkFormRegistro (idForm) {
      $(idForm + " *").on("change keydown", function() {
        if (checkInput("#nombre", ".error1") && 
        checkInput("#apellido", ".error2") && comprobarEdad() && 
        checkInput("#email2", ".error7") && checkEmail()&&
         comprobarContrasena()){
          enableSubmit(idForm);
        } 
        else {
          disableSubmit(idForm);
        }
      });
    }

    

   
    
    /*$(function(){
        $(".registrar").hide();

        $("a").eq(1).click(function(event) {
            $(".registrar").show();
            $(".entrar").hide();
            
        });
        $("a").eq(2).click(function(event) {
            $(".registrar").hide();
            $(".entrar").show();
            
        });

       
        $("body").on("click",".ojo",function (event) {
       
            $("span").toggleClass("ojo").toggleClass("abierto");
            $(":password").attr("type", "text");
            $("span").removeClass("fa-eye").addClass("fa-eye-slash");
        });
        
        $("body").on("click",".abierto",function (event){
            
            $("span").toggleClass("ojo").toggleClass("abierto");
            $("input").eq(1).attr("type", "password");
            $("span").removeClass("fa-eye-slash").addClass("fa-eye");
                
        });
        
        $(".error1").hide();
        $(".error2").hide();
        $(".error3").hide();
        $(".error4").hide();
        $(".error5").hide();
        $(".error6").hide();
      
        checkFormRegistro("#registro");
      

    });*/
    