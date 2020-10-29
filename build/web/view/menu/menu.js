function loadData(){
    requisicao("../../getLanchesCliente", getLanches);
    requisicao("../../getBebidasCliente", getBebidas);
}


function divConstructor(dados){

    let screen = document.getElementById("screen");
    
    let divLanche = document.createElement('div');
    divLanche.classList.add("divLanche");

    let row = document.createElement('div');
    row.classList.add("row");
    
    let divImagem = document.createElement('div');
    divImagem.classList.add("divImagem");

    let img = document.createElement('img');
    img.classList.add("lancheIMG");
    img.setAttribute("src", imagemAleatoriaLanche());
    
    let divConteudo = document.createElement('div');
    divConteudo.classList.add("divConteudo");

    let tituloLanche = document.createElement('h1');
    tituloLanche.classList.add("tituloLanche");
    tituloLanche.innerText = dados['nome'];

    let row2 = document.createElement('div');
    row2.classList.add("row");

    let descricaoLanche = document.createElement('div');
    descricaoLanche.classList.add("descricaoLanche");
    
    let p = document.createElement("p");
    p.classList.add("textoDescricao");
    p.innerText = dados['descricao'];

    let preco = document.createElement("p");
    preco.classList.add("preco");
    preco.innerText = "R$ "+dados['valor_venda'];

    let column = document.createElement('div');
    column.classList.add("column");
    
    let botaoLanche1 = document.createElement("button");
    botaoLanche1.classList.add("botaoLanche");
    botaoLanche1.innerText = "Veja os Ingredientes"
    botaoLanche1.onclick = () => {showIngredientes(dados['id_lanche']);
                                    campoSelecionado = p;};


    let botaoLanche2 = document.createElement("button");
    botaoLanche2.classList.add("botaoLanche");
    botaoLanche2.innerText = "Adicionar ao Carrinho"
    botaoLanche2.onclick = () => {lancheProCarrinho(dados['nome'], dados['valor_venda']);};

    screen.appendChild(divLanche);
    divLanche.appendChild(row);
    row.appendChild(divImagem);
    divImagem.appendChild(img);
    row.appendChild(divConteudo);
    divConteudo.appendChild(tituloLanche);
    divConteudo.appendChild(row2);
    row2.appendChild(descricaoLanche);
    descricaoLanche.appendChild(p);
    descricaoLanche.appendChild(preco);
    row2.appendChild(column);
    column.appendChild(botaoLanche1);
    column.appendChild(botaoLanche2);
}

function divConstructorBebidas(dados){

    let screen = document.getElementById("screenBebidas");
    
    let divLanche = document.createElement('div');
    divLanche.classList.add("divLanche");

    let row = document.createElement('div');
    row.classList.add("row");
    
    let divImagem = document.createElement('div');
    divImagem.classList.add("divImagem");

    let img = document.createElement('img');
    img.classList.add("lancheIMG");
    img.setAttribute("src", imagemAleatoriaBebida());
    
    let divConteudo = document.createElement('div');
    divConteudo.classList.add("divConteudo");

    let tituloLanche = document.createElement('h1');
    tituloLanche.classList.add("tituloLanche");
    tituloLanche.innerText = dados['nome'];

    let row2 = document.createElement('div');
    row2.classList.add("row");

    let descricaoLanche = document.createElement('div');
    descricaoLanche.classList.add("descricaoLanche");
    
    let p = document.createElement("p");
    p.classList.add("textoDescricao");
    p.innerText = dados['descricao'];

    let preco = document.createElement("p");
    preco.classList.add("preco");
    preco.innerText = "R$ "+dados['valor_venda'];

    let column = document.createElement('div');
    column.classList.add("column");
    
    let botaoLanche1 = document.createElement("button");
    botaoLanche1.classList.add("botaoLanche");
    botaoLanche1.innerText = "Tipo: "+dados['tipo'].capitalize();

    let botaoLanche2 = document.createElement("button");
    botaoLanche2.classList.add("botaoLanche");
    botaoLanche2.innerText = "Adicionar ao Carrinho"
    botaoLanche2.onclick = () => {bebidaProCarrinho(dados['nome'], dados['valor_venda']);};

    screen.appendChild(divLanche);
    divLanche.appendChild(row);
    row.appendChild(divImagem);
    divImagem.appendChild(img);
    row.appendChild(divConteudo);
    divConteudo.appendChild(tituloLanche);
    divConteudo.appendChild(row2);
    row2.appendChild(descricaoLanche);
    descricaoLanche.appendChild(p);
    descricaoLanche.appendChild(preco);
    row2.appendChild(column);
    column.appendChild(botaoLanche1);
    column.appendChild(botaoLanche2);
}

function getLanches(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        alert("Ocorreu um erro com nosso Sistema! Tente novamente mais tarde.")
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);

        Object.keys(dados).forEach( lanche => {
            divConstructor(dados[lanche])}
        )
    }

}

function getBebidas(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        alert("Ocorreu um erro com nosso Sistema! Tente novamente mais tarde.")
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);

        Object.keys(dados).forEach( lanche => {
            divConstructorBebidas(dados[lanche])}
        )
    }

}

function imagemAleatoriaLanche(){
    n = Math.floor(Math.random() * 4) + 1  
    return "../assets/lanches/" + n + ".jpg";
}

function imagemAleatoriaBebida(){
    n = Math.floor(Math.random() * 4) + 1  
    return "../assets/bebidas/" + n + ".jpg";
}

function showBebidas(){

    let lanches = document.getElementById("screen");
    let bebidas = document.getElementById("screenBebidas");
    let porcoes = document.getElementById("screenPorcoes");
    
    lanches.style.display = 'none';
    bebidas.style.display = 'block';
    porcoes.style.display = 'none';

}

function showLanches(){

    let lanches = document.getElementById("screen");
    let bebidas = document.getElementById("screenBebidas");
    let porcoes = document.getElementById("screenPorcoes");
    
    lanches.style.display = 'block';
    bebidas.style.display = 'none';
    porcoes.style.display = 'none';

}

function showPorcoes(){

    let lanches = document.getElementById("screen");
    let bebidas = document.getElementById("screenBebidas");
    let porcoes = document.getElementById("screenPorcoes");
    
    lanches.style.display = 'none';
    bebidas.style.display = 'none';
    porcoes.style.display = 'block';

}

String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}


function showIngredientes(id){
    getIngredientesLanche(id);
}

function getIngredientesLanche(id){
    dados = {}
    dados['id'] = id;
    requisicao("../../getIngredientesPorLancheCliente", mostrarIngredientes, JSON.stringify(dados));    
}

function mostrarIngredientes(resposta){
    dados = JSON.parse(resposta.srcElement.responseText);
    let string="";
    Object.keys(dados).forEach(ingrediente => {
        string += "-"+dados[ingrediente]['nome']+"\r\n"}
    )
    campoSelecionado.innerText = string;
}

function lancheProCarrinho(nome, preco){
    console.log("Ativado")
    sessionStorage.setItem(nome, preco+";lanche;1");
    alert("Lanche salvo! Faça login no Carrinho para Prosseguir ou Removê-lo");
}

function bebidaProCarrinho(nome, preco,){
    console.log("Ativado")
    sessionStorage.setItem(nome, preco+";bebida;1");
    alert("Bebida salva! Faça login no Carrinho para Prosseguir ou Removê-lo");
}