
function getInfo(){
requisicao("../../getIngredientesCliente", getIngredientes);
}

function getIngredientes(resposta){

    dadosLanche = {};
    dadosLanche['ingredientes'] = {};
    valor = 0;

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        Object.keys(dados).forEach( ingrediente => {
            if(dados[ingrediente]['tipo'] == 'pao'){
                option = document.createElement('option');
                option.innerText=dados[ingrediente]['nome'];
                document.getElementById('SelectPao').add(option);
            } else {
            createIngredienteDiv(dados[ingrediente])}
        })
    }

}

function createIngredienteDiv(dados){
    
    let ingredientes = document.getElementById("ingredientes");

    let opcIngredientes = document.createElement('div');
    opcIngredientes.classList.add("opcIngredientes");

    let nameValue = document.createElement('div');
    nameValue.classList.add('nameValue');

    let legendIngrediente = document.createElement('p');
    legendIngrediente.classList.add('legendIngrediente');
    legendIngrediente.innerHTML = dados['nome']+"<br> R$ "+dados['valor_venda'];

    let containerIncremento = document.createElement('div');
    containerIncremento.classList.add('containerIncremento');

    let contador = document.createElement('div');
    contador.classList.add('contador');

    let p = document.createElement('p');
    p.classList.add('legendIngrediente');
    p.innerText = 0;

    let buttonplus = document.createElement('button');
    buttonplus.classList.add('buttonIcons');
    buttonplus.type = "button";
    buttonplus.onclick = ()=>{plusItem(p, dados['nome'], dados['valor_venda']);};

    let plus = document.createElement('p');
    plus.classList.add('icon');
    plus.innerText = " +";

    let buttonminus = document.createElement('button');
    buttonminus.classList.add('buttonIcons');
    buttonminus.type = "button";
    buttonminus.onclick = ()=>{minusItem(p, dados['nome'], dados['valor_venda']);};

    let minus = document.createElement('p');
    minus.classList.add('icon');
    minus.innerText = "– ";

    
    
    ingredientes.appendChild(opcIngredientes);
    opcIngredientes.appendChild(nameValue);
    nameValue.appendChild(legendIngrediente);
    opcIngredientes.appendChild(containerIncremento);
    containerIncremento.appendChild(contador);
    contador.appendChild(buttonminus);
    buttonminus.appendChild(minus);
    contador.appendChild(p);
    contador.appendChild(buttonplus);
    buttonplus.appendChild(plus);

}   

function plusItem(p, nome, valorI){
    n = parseInt(p.innerText) + 1;
    p.innerText = n;

    dadosLanche['ingredientes'][nome] = p.innerText;
    valor += parseFloat(valorI);
    atualizarValor();
}

function minusItem(p, nome, valorI){
    if(parseInt(p.innerText) > 0){
        n = parseInt(p.innerText) - 1;
        p.innerText = n;
        
        dadosLanche[nome] = p.innerText;
        
        valor -= parseFloat(valorI);
        atualizarValor();
        if(p.innerText == "0"){
            delete dadosLanche['ingredientes'][nome];
        }
    }
}

function atualizarValor(){
    document.getElementById("valor1").innerText = "R$ "+valor.toFixed(2);
}

function salvarLanche(){

    let dados = {};

    if(validarLanche()){
        console.log(dadosLanche);
        requisicao("../../salvarLancheCliente", resolver, JSON.stringify(dadosLanche));
    }

}

function validarLanche(){
    let nome = document.getElementById("nomeLanche");
    let descricao = document.getElementById("textArea3");
    let pao = document.getElementById("SelectPao");
    let resultado = true;
    if(nome.value == ""){
        alert("Campo Nome Vazio!")
        resultado = false;
    }
    if(descricao.value == ""){
        alert("Campo Descrição Vazio!")
        resultado = false;
    }
    if(pao.selectedIndex == 0){
        alert("Campo Pão Vazio!")
        resultado = false;
    }
    if(resultado){
        dadosLanche['nome'] = nome.value;
        dadosLanche['descricao'] = descricao.value;
        dadosLanche['ingredientes'][pao.value] = 1 ;
    }
    return resultado;
}

function resolver(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login.html");
    } else {
        window.location.replace(resposta.srcElement.responseText);
        
    }
}
