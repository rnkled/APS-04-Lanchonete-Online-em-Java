function salvarItem(){

    let form = document.getElementById("addItem");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../salvarIngrediente", alertarResposta, JSON.stringify(dados));
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
            if (field.value.toString().trim() === "") {
                alert("Você precisa preencher todos os campos para se Cadastrar! O Campo "+field.name+" Está Vazio!")
                sucesso = false;
                return;
            }
        }, {});
        return sucesso;
}