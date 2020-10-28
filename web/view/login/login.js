function enviarLogin(){

    let usuario = document.getElementById("loginInput").value;
    let senha = document.getElementById("senhaInput").value;

    if(usuario && senha){

        let dados = {};

        dados['usuario'] = usuario;
        dados['senha'] = senha;

        
        requisicao("../../login", resolver, JSON.stringify(dados));
    } else {
        alert("Digite as Informações!");
    }

}


function resolver(resposta){
    if(resposta.srcElement.responseText.localeCompare("erro") == -1){
        window.location.replace(resposta.srcElement.responseText);
    } else {
        alert("Erro ao Logar! Tente novamente. Se Cadastre se não possuir uma conta!");
    }
}


function validarToken(){
    requisicao("../../validarToken", check)
}

function check(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        console.log("Token Inválido");
    } else {
        window.location.replace("../resumo/resumo.html");
    }
}