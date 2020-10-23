function enviarCadastro(){

    let usuario = document.getElementById("usuario");
    let endereco = document.getElementById("endereco");
    let dados = {};

    if(validar(usuario) && validar(endereco)){
        dados['usuario'] = formularioParaObjeto(usuario);
        dados['endereco'] = formularioParaObjeto(endereco);
        requisicao("../../cadastro", alertarResposta, JSON.stringify(dados));
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