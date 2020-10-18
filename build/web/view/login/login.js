function enviarLogin(){

    let usuario = document.getElementById("loginInput").value;
    let senha = document.getElementById("senhaInput").value;

    if(usuario && senha){

        let dados = {};

        dados['usuario'] = usuario;
        dados['senha'] = senha;

        
        requisicao("../../login", alertarResposta, JSON.stringify(dados));
    } else {
        alert("Digite as Informações!")
    }

}