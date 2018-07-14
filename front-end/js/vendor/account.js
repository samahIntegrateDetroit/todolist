
function validate_passwords(){
var password1 = document.getElementById("passwordOne");
var password2 = document.getElementById("passwordTwo");

// console.log(password1.value +'|'+ password2.value);
if(password1.value !== password2.value){
    document.getElementById("sign-up-button").disabled = true;
    $("#passwordTwo").foundation('show');
}else{
    document.getElementById("sign-up-button").disabled = false;
    $("#passwordTwo").foundation('hide');
}

}

