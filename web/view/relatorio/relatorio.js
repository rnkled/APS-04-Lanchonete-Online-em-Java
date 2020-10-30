function getInfo(){
    requisicao("../../getRelatorioLanches", getRelLanches);
    requisicao("../../getRelatorioBebidas", getRelBebidas);
    requisicao("../../getRelatorioGastos", getRelGastos);
}

function getRelLanches(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        attRelatorioLanches(dados);
    }

}

function attRelatorioLanches(dados){

    let tabela = document.getElementById("tbRelatorioLanches");

    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
        }
    });
}

function getRelBebidas(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        attRelatorioBebidas(dados);
    }

}

function attRelatorioBebidas(dados){

    let tabela = document.getElementById("tbRelatorioBebidas");

    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
        }
    });
}

function getRelGastos(resposta){

    if(resposta.srcElement.responseText.includes("erro")){
        window.location.replace("../login/login_Funcionario.html?Action=TokenError");
    } 
    else {
        dados = JSON.parse(resposta.srcElement.responseText);
        attRelatorioGastos(dados);
    }

}

function attRelatorioGastos(dados){

    let tabela = document.getElementById("tbRelatorioGastos");

    Object.keys(dados).forEach(cadastro => {
        let row = tabela.insertRow(1);
        for (let key in dados[cadastro]) {
            row.insertCell().innerHTML = dados[cadastro][key];
        }
    });
}
