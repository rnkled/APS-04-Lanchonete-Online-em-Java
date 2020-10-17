
function requisicao(caminho, funcaoResposta, dados = null){
    try
    {   
        //Inicia o Objeto que faz o Request
        asyncRequest = new XMLHttpRequest();  

        //prepara a requisição pro servlet com o Caminho dele e o tipo de Request
        asyncRequest.open('POST', caminho, true);
        
        //Seta a função a ser chamada quando a comunicação for feita e a resposta chegar. A Função é passada
        //Pelo parametro funcaoResposta
        asyncRequest.onload = funcaoResposta; 

        //Manda os dados, se ouver algum, ou Null se nada for especificado
        asyncRequest.send(dados);
        
    }
    catch(exception)
    {
        alert("Request Falho!");
        console.log(exception);
    }
}

function printarResposta(resposta){

    //Fiz essa função aqui só pra printar os dados que forem recebidos de volta
    console.log(resposta);
}

function alertarResposta(resposta){

    //E essa pra mostrar com um alerta
    alert(resposta.srcElement.responseText);        
    console.log(resposta);
}

function enviarCadastro(){

    //Encontra o elemento do Formulário pra pegar os dados
    let formulario = document.getElementById("formTeste");

    //Pega os dados do Formulário e Transforma-os em um JSON
    let dados = Object.values(formulario).reduce((obj, field) => {
        obj[field.name] = field.value;
        return obj}, {});

    //Executa a requisição para o servlet, convertendo o JSON para o envio e colocando a função alertarResposta 
    //para quando a resposta chegar.
    requisicao("../cadastro", alertarResposta, JSON.stringify(dados));

}

function atualizarTabela(resposta){

    //Pega o Elemento html da Tabela e Coloca numa Variabel
    let tabela = document.getElementById("cadastrados");
    limparTabela(tabela);

    //Pega os dados passados pela resposta e transforma num JSON pra lidar
    dados = JSON.parse(resposta.srcElement.responseText);
    
    //Esse loop aqui parece meio complexo, mas é basicamente pra jogar os dados na Tabela, ele faz os classicos 
    //loop dentro de loop  pra criar cada row.
    Object.keys(dados).forEach(cadastro => {
        console.log(cadastro);
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
        }
    });
    
    
}

function limparTabela(tabela){
    let tableRows = tabela.getElementsByTagName('tr');

    for (let x=1 ; x<tableRows.length; x++) {
    tabela.deleteRow(tableRows[x]);
    }
}