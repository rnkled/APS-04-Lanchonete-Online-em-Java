function logout(){
    requisicao("../../logout", deslogar)
    deleteAllCookies();
    sessionStorage.clear();
}

function deslogar(resposta){
    alert(resposta.srcElement.responseText);
    window.location.replace("../home/home.html");
}

function deleteAllCookies() {

    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++){
        console.log(cookies[i].split("=")[0].trim());
        document.cookie = cookies[i].split("=")[0].trim()+"=; expires=Thu, 01 jan 1970 00:00:01 GTM;";}
}