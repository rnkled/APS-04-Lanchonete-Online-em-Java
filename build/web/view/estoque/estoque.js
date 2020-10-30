function getInfo(){
    requisicao("../../getIngredientes", getIngredientes);
    requisicao("../../getBebidas", getBebidas);
    requisicao("../../getLanches", getLanches);
}


function getIngredientes(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        atualizarIngredientes(dados);
    }

}

function getLanches(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        atualizarLanches(dados);
    }

}

function getBebidas(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        atualizarBebidas(dados);
    }
}

function atualizarIngredientes(dados){

    let tabela = document.getElementById("tabelaIngredientes");

    
    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
            row.onclick = () => pegarIngrediente(dados[cadastro]);
        }
    });
}

function atualizarLanches(dados){

    let tabela = document.getElementById("tabelaLanches");   
    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
            row.onclick = () => pegarLanche(dados[cadastro]);
        }
    });
}

function pegarIngrediente(dados) {
    document.getElementById("ingredientesID").value = dados["id_ingrediente"];
    document.getElementById("ingredientesNome").value = dados["nome"];
    document.getElementById("ingredientesDescricao").value = dados["descricao"];
    document.getElementById("ingredientesQuantidade").value = dados["quantidade"];
    document.getElementById("ingredientesPrecoCompra").value = dados["valor_compra"];
    document.getElementById("ingredientesPrecoVenda").value = dados["valor_venda"];
    document.getElementById("ingredientesTipo").value = dados["tipo"];
    showIngrediente();
};

function pegarLanche(dados) {
    document.getElementById("lanchesID").value = dados["id_lanche"];
    document.getElementById("lanchesNome").value = dados["nome"];
    document.getElementById("lanchesDescricao").value = dados["descricao"];;
    document.getElementById("lanchesPrecoVenda").value = dados["valor_venda"];
    getIngredientesLanche(dados["id_lanche"])
    showLanche();
};

function getIngredientesLanche(id){
    dados = {}
    dados['id'] = id;
    requisicao("../../getIngredientesPorLanche", setarIngredientes, JSON.stringify(dados));
    
}

function setarIngredientes(resposta){
    let tabela = document.getElementById("ingredientesLanche");
    limparTabela(tabela);

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        
        Object.keys(dados).forEach(cadastro => {
            let row = tabela.insertRow(1);
            for (let key in dados[cadastro]) {
                row.insertCell().innerHTML = dados[cadastro][key];
            }
    });
    }
}

function limparTabela(tabela){
    //let tableRows = tabela.getElementsByTagName('tr');

    while(tabela.rows.length > 1) {
        tabela.deleteRow(1);
      }
}

function showLanche(){
    let ingredientes = document.getElementById("editarIngredientes");
    let bebidas = document.getElementById("editarBebidas");
    let lanches = document.getElementById("editarLanches");
    lanches.style.display = 'block';
    ingredientes.style.display = 'none';
    bebidas.style.display = 'none';
}


function showIngrediente(){
    let ingredientes = document.getElementById("editarIngredientes");
    let bebidas = document.getElementById("editarBebidas");
    let lanches = document.getElementById("editarLanches");
    lanches.style.display = 'none';
    ingredientes.style.display = 'block';
    bebidas.style.display = 'none';
}

function atualizarBebidas(dados){
    let tabela = document.getElementById("tabelaBebidas");

    
    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
            row.onclick = () => pegarBebida(dados[cadastro]);
        }
    });

}

function pegarBebida(dados){
    document.getElementById("bebidasID").value = dados["id_bebida"];
    document.getElementById("bebidasNome").value = dados["nome"];
    document.getElementById("bebidasDescricao").value = dados["descricao"];
    document.getElementById("bebidasQuantidade").value = dados["quantidade"];
    document.getElementById("bebidasPrecoCompra").value = dados["valor_compra"];
    document.getElementById("bebidasPrecoVenda").value = dados["valor_venda"];
    document.getElementById("bebidasTipo").value = dados["tipo"];
    showBebida();
}

function showBebida(){
    let ingredientes = document.getElementById("editarIngredientes");
    let bebidas = document.getElementById("editarBebidas");
    let lanches = document.getElementById("editarLanches");
    lanches.style.display = 'none';
    ingredientes.style.display = 'none';
    bebidas.style.display = 'block';
}

function alterarIngrediente(){

    let form = document.getElementById("editarIngredientes");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../alterarIngrediente", resolver, JSON.stringify(dados));
    }

}

function alterarBebida(){

    let form = document.getElementById("editarBebidas");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../alterarBebida", resolver, JSON.stringify(dados));
    }

}

function removerIngrediente(){

    let form = document.getElementById("editarIngredientes");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../removerIngrediente", resolver, JSON.stringify(dados));
    }

}

function removerBebida(){

    let form = document.getElementById("editarBebidas");
    let dados = {};

    if(validar(form)){
        dados = formularioParaObjeto(form);
        requisicao("../../removerBebida", resolver, JSON.stringify(dados));
    }

}


function resolver(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } else {
        alert(resposta.srcElement.responseText);
        window.location.reload();
    }
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

function formularioParaObjeto(formulario){
    let dados = Object.values(formulario).reduce(
        (obj, field) => {obj[field.name] = field.value; return obj}, {});
        return dados;
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