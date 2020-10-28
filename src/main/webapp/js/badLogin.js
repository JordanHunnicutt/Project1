let loginMatch = true;

function checkLoginJS(){
    if(loginMatch === false){
        const message = document.getElementById('incorrectCredentials');
        message.innerHTML = "Incorrect username/password. Please try again";
        const u = document.getElementById('userText');
        const p = document.getElementById('pwText');
        u.value='';
        p.value='';
    }
}