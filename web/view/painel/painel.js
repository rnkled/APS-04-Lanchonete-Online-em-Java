function validarToken(){
    sessionStorage.clear();
    requisicao("../../validarTokenFunc", check)
}

function check(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    }
}

function showCadIngredienteDiv(){
    
    
    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadIngredientes");
    let div2 = document.getElementById("CadBebidas");
    let div3 = document.getElementById("CadFuncionarios");
    let div4 = document.getElementById("CadLanches");
    
        tip.style.display = 'none';
        div.style.display = 'block';
        div2.style.display = 'none';
        div3.style.display = 'none';
        div4.style.display = 'none';
}

function showCadBebidaDiv(){

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadBebidas");
    let div2 = document.getElementById("CadIngredientes");
    let div3 = document.getElementById("CadFuncionarios");
    let div4 = document.getElementById("CadLanches");

    
        tip.style.display = 'none';
        div.style.display = 'block';
        div2.style.display = 'none';
        div3.style.display = 'none';
        div4.style.display = 'none';
    
}

function showInicioDiv(){

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadBebidas");
    let div2 = document.getElementById("CadIngredientes");
    let div3 = document.getElementById("CadFuncionarios");
    let div4 = document.getElementById("CadLanches");

    
        tip.style.display = 'block';
        div.style.display = 'none';
        div2.style.display = 'none';
        div3.style.display = 'none';
        div4.style.display = 'none';
}

function salvarIngrediente(){

    let form = document.getElementById("addIngrediente");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../salvarIngrediente", resolver, JSON.stringify(dados));
        
    }

}

function salvarBebida(){

    let form = document.getElementById("addBebida");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../salvarBebida", resolver, JSON.stringify(dados));
        
    }

}

function salvarFuncionario(){

    let form = document.getElementById("addFuncionario");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        console.log(dados);
        requisicao("../../salvarFuncionario", resolver, JSON.stringify(dados));
        
    }
    
}

function showCadLanches(){
    //CadLanches

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadLanches");
    let div2 = document.getElementById("CadBebidas");
    let div3 = document.getElementById("CadFuncionarios");
    let div4 = document.getElementById("CadIngredientes");
    //let divStatus = document.getElementById("statusId")
    //let divStatus2 = document.getElementById("statusId2")
    //let divcenter = document.getElementById("footerId");
  
    
        tip.style.display = 'none';
        div.style.display = 'block';
        div2.style.display = 'none';
        div3.style.display = 'none';
        div4.style.display = 'none';
        //divStatus.style.display = 'flex';
        //divStatus2.style.display = 'flex';
        //divcenter.style.justifyContent = 'space-around';

    
    requisicao("../../getIngredientes", getIngredientes);

}

function resolver(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } else {
        alert(resposta.srcElement.responseText);
        window.location.reload();
    }
}

function logout(){
    deleteAllCookies();
    deleteAllSession();
    sessionStorage.clear();
    requisicao("../../logout", deslogar)
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

function deleteAllSession() {
    
    console.log("Ué");
    Object.keys(sessionStorage).forEach(
        (key) => {
            sessionStorage.removeItem(key);
        }
    )
}

function showCadFuncionario(){

    let tip = document.getElementById("Agrupado");
    let div = document.getElementById("CadLanches");
    let div2 = document.getElementById("CadBebidas");
    let div3 = document.getElementById("CadFuncionarios")
    
    
        tip.style.display = 'none';
        div.style.display = 'none';
        div2.style.display = 'none';
        div3.style.display = 'block';
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

function getIngredientes(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        Object.keys(dados).forEach( ingrediente => {
            if(dados[ingrediente]['tipo'] == 'pao'){
                option = document.createElement('option');
                option.innerText=dados[ingrediente]['nome'];
                document.getElementById('selectPao').add(option);
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
    buttonplus.onclick = ()=>{plusItem(p, dados['nome']);};

    let plus = document.createElement('p');
    plus.classList.add('icon');
    plus.innerText = " +";

    let buttonminus = document.createElement('button');
    buttonminus.classList.add('buttonIcons');
    buttonminus.type = "button";
    buttonminus.onclick = ()=>{minusItem(p, dados['nome']);};

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

function plusItem(p, nome){
    n = parseInt(p.innerText) + 1;
    p.innerText = n;

    sessionStorage.setItem(nome, p.innerText);
    
}

function minusItem(p, nome){
    if(parseInt(p.innerText) > 0){
        n = parseInt(p.innerText) - 1;
        p.innerText = n;
    
        sessionStorage.setItem(nome, p.innerText);
    }
}

function salvarLanche(){

    let dados = {};

    if(validarLanche()){
        dados = dadosDoLanche();
        console.log(dados);
        sessionStorage.clear()
        requisicao("../../salvarLanche", resolver, JSON.stringify(dados));
        window.location.reload();
    }

}


function validarLanche(){
    let nome = document.getElementById("nomeLanche");
    let descricao = document.getElementById("textArea3");
    let pao = document.getElementById("selectPao");
    let valor = document.getElementById("ValorLanche");
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
    if(valor.value == 0){
        alert("Campo Valor Vazio!")
        resultado = false;
    }
    return resultado;
}

function dadosDoLanche(){
    let dados = {};
    let ingredientes = {};

    let nome = document.getElementById("nomeLanche");
    let descricao = document.getElementById("textArea3");
    let pao = document.getElementById("selectPao");
    let valor = document.getElementById("ValorLanche");

    dados['nome'] = nome.value;
    dados['descricao'] = descricao.value;
    dados['ValorVenda'] = parseFloat(valor.value);

    ingredientes[pao.value] = "1";
    Object.keys(sessionStorage).forEach(
        (key) => {
            ingredientes[key] = sessionStorage.getItem(key);
        }
    )
    dados['ingredientes'] = ingredientes;
    return dados
}