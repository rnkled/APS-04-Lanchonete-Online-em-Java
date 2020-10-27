
function requisicao(caminho, funcaoResposta, dados = null){
    try
    {   
        //Inicia o Objeto que faz o Request
        asyncRequest = new XMLHttpRequest();  
        asyncRequest.withCredentials = true;
        //prepara a requisição pro servlet com o Caminho dele e o tipo de Request
        asyncRequest.open('POST', caminho, true);
        asyncRequest.withCredentials = true;
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


/////////////////
function get_cookie(name){
    return document.cookie.split(';').some(c => {
        return c.trim().startsWith(name + '=');
    });
}

