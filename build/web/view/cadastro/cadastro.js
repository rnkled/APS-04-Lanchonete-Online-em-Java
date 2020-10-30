function enviarCadastro(){

    let usuario = document.getElementById("usuario");
    let endereco = document.getElementById("endereco");
    let dados = {};

    if(validar(usuario) && validar(endereco)){
        dados['usuario'] = formularioParaObjeto(usuario);
        dados['endereco'] = formularioParaObjeto(endereco);
        requisicao("../../cadastro", resolver, JSON.stringify(dados));
    }

}

function formularioParaObjeto(formulario){
    let dados = Object.values(formulario).reduce(
        (obj, field) => {obj[field.name] = field.value; return obj}, {});
        return dados;
}

function validar(formulario){
    let sucesso = true;
    Object.values(formulario).reduce(
        (obj, field) => {
            if (field.value.toString().trim() === ""  && (!field.name == 'complemento')) {
                alert("Você precisa preencher todos os campos para se Cadastrar! O Campo "+field.name+" Está Vazio!")
                sucesso = false;
                return;
            }
        }, {});
        return sucesso;
}

function resolver(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        alert("Ops... Ocorreu um erro no Cadastro, Tente novamente mais Tarde!");
    } else {
        alert(resposta.srcElement.responseText);
        window.location.replace("../login/login.html");
    }
}