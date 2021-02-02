window.onload = function() {
	document.getElementById("email2").onblur = this.comprobarEmail;

}


function comprobarEmail(){
	var email1 = document.getElementById("email1");
	var email2 = document.getElementById("email2");


	if(email1.value !== email2.value){
		mandarError(email2);
		return false;
	}
	
	quitarError(email2);
	return true;
}


function mandarError(campo) {
	campo.style.border = "2px solid red";
	campo.parentNode.nextElementSibling.style.color = "red";
	campo.parentNode.nextElementSibling.style.maxHeight = "20px";
	campo.parentNode.nextElementSibling.style.overflow = "visible";
		
}
function quitarError(campo){
	campo.style.border = "1px solid #ced4da";
	campo.parentNode.nextElementSibling.style.maxHeight = "0px";
	campo.parentNode.nextElementSibling.style.overflow = "hidden";
}

