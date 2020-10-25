function showCadIngredienteDiv(){
    
    
    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadIngredientes");
    let div2 = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'none'){
        tip.style.display = 'none';
        div.style.display = 'block';
    }

}

function showCadBebidaDiv(){

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadBebidas");
    let div2 = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'none'){
        tip.style.display = 'none';
        div.style.display = 'block';
    }
    
    

}

function showInicioDiv(){

    let tip = document.getElementById("Agrupado");
    
    let div = document.getElementById("CadIngredientes");
    
    if(div.style.display = 'block'){
        tip.style.display = 'block';
        div.style.display = 'none';
    }
    
    

}

function salvarIngrediente(){

    let form = document.getElementById("addIngrediente");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../salvarIngrediente", alertarResposta, JSON.stringify(dados));
    }

}

function salvarBebida(){

    let form = document.getElementById("addBebida");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../salvarBebida", alertarResposta, JSON.stringify(dados));
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
            if (field.value.toString().trim() === "" || field.value.toString().trim() === "Tipo") {
                alert("Você precisa preencher todos os campos para se Cadastrar! O Campo "+field.name+" Está Vazio!")
                sucesso = false;
                return;
            }
        }, {});
        return sucesso;
}