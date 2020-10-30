function validarToken(){
    requisicao("../../validarToken", check)
}

function check(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login.html?Action=TokenError");
    }
}

function itemConstructor(nome, preco){
    let containerPedido = document.getElementById("containerPedido");

    let ItemPedido = document.createElement('div');
    ItemPedido.classList.add("ItemPedido");

    let legendaNome = document.createElement("p");
    legendaNome.classList.add('legendaNome');
    legendaNome.innerText = nome;

    let legendaPreco = document.createElement("p");
    legendaPreco.classList.add('legendaPreco');
    legendaPreco.innerText = "R$ "+parseFloat(preco);

    let contador = document.createElement('div');
    contador.classList.add("contador");

    let buttonMinus = document.createElement("button");
    buttonMinus.classList.add("buttonRemove")
    buttonMinus.innerText = "–"
    buttonMinus.onclick = ()=>{minusItem(quantidade, nome, ItemPedido);};

    let quantidade = document.createElement("p");
    quantidade.classList.add('quantidade');
    quantidade.innerText = 1;

    let buttonPlus = document.createElement("button");
    buttonPlus.classList.add("buttonRemove")
    buttonPlus.innerText = "+";
    buttonPlus.onclick = ()=>{plusItem(quantidade, nome);};

    containerPedido.appendChild(ItemPedido);
    ItemPedido.appendChild(legendaNome);
    ItemPedido.appendChild(legendaPreco);
    ItemPedido.appendChild(contador);
    contador.appendChild(buttonMinus);
    contador.appendChild(quantidade);
    contador.appendChild(buttonPlus);
}

function pegarPedidos(){
    dados = {};
    valor = 0;
    pegarCustomizado();
    Object.keys(sessionStorage).forEach(
        (key) => {
            preco = sessionStorage.getItem(key).split(";");
            itemConstructor(key, preco[0]);
            dados[key] = preco;
            valor += parseFloat(preco[0]);
        }
    )
    atualizarValor();
}

function atualizarValor(){
    document.getElementById("valor1").innerText = "R$ "+valor.toFixed(2);
    document.getElementById("valor2").innerText = "R$ "+valor.toFixed(2);
}

function plusItem(p, nome){
        n = parseInt(p.innerText) + 1;
        p.innerText = n;
        dados[nome][2] = n;
        valor += parseFloat(dados[nome][0]);
        atualizarValor();
}

function minusItem(p, nome, div){
    if(parseInt(p.innerText) > 1){
        n = parseInt(p.innerText) - 1;
        p.innerText = n;
        dados[nome][2] = n;
        valor -= parseFloat(dados[nome][0]);
        atualizarValor();
    } else {
        valor -= parseFloat(dados[nome][0]);
        sessionStorage.removeItem(nome);
        delete dados[nome];
        div.remove();
        atualizarValor();
    }
}

function showConteudo1(){
    cont1 = document.getElementById("conteudo1");
    cont2 = document.getElementById("conteudo2");

    cont1.style.display = "flex";
    cont2.style.display = "none";
}

function showConteudo2(){
    cont1 = document.getElementById("conteudo1");
    cont2 = document.getElementById("conteudo2");

    cont1.style.display = "none";
    cont2.style.display = "flex";

    textoResumo();
}

function getUsuario(){
    requisicao("../../getCliente", setUsuarioInfo);
}

function setUsuarioInfo(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login.html?Action=TokenError");
    } 
    else {
        let dadosCliente = JSON.parse(resposta.srcElement.responseText);
        let nome = dadosCliente[0]['nome']+" "+dadosCliente[0]['sobrenome'];
        let endereco = "";

        Object.keys(dadosCliente[1]).forEach( key => {
            if(dadosCliente[1][key] && !key.includes("id_")){
            endereco += dadosCliente[1][key]
            if(!key.includes("estado")){
                endereco +=", ";
            }
        }
        })
        id = dadosCliente[0]['id_cliente'];
        document.getElementById("nome").innerText = nome;
        document.getElementById("endereco").innerText = endereco;
        document.getElementById("nome2").innerText = nome;
        document.getElementById("endereco2").innerText = endereco;

        dados['id'] = id;
    }
}

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

function textoResumo() {
    let string = "";
    Object.keys(dados).forEach( key => {
        let pontinhos = "."
        if(key != "id"){
        string += "x"+dados[key][2]+pontinhos.repeat(30)+key+pontinhos.repeat(58-key.length)+"R$ "+dados[key][0]+"\n";}
    })
    document.getElementById("textResumo").innerText = string;
}

function realizarCompra(){
    console.log(dados);
    requisicao("../../comprar", resolver, JSON.stringify(dados));
}

function resolver(resposta){
    if(resposta.srcElement.responseText.includes("erro")){
        alert("Ocorreu um Erro com seu Pedido! tente logar Novamente.")
        window.location.replace("../login/login.html");
    } else {
        alert(resposta.srcElement.responseText);
        sessionStorage.clear();
        window.location.replace("../carrinho/carrinho.html");
    }
}

///Teste não consigo entender

function pegarCustomizado(){
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    nome = urlParams.get('nome');
    preco = [urlParams.get('preco'), "lanche", "1"];
    if(nome){
    itemConstructor(nome, preco[0]);
    dados[nome] = preco;
    valor += parseFloat(preco[0]);}
}