$('document').ready(function(){
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");
    const errorMessage = document.getElementById("errorMessage");

    function validatePassword(){
        if(password.value !== confirmPassword.value) {
            errorMessage.innerHTML="Passwords not match";
        } else {
            errorMessage.innerHTML="";
        }
    }
    password.onchange = validatePassword;
    confirmPassword.onkeyup = validatePassword;
});